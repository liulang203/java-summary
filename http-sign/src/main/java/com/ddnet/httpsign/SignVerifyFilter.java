package com.ddnet.httpsign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ddnet.httpsign.HttpSignUtil.SIGN_HEADER_NAME;

/**
 * 签名验证拦截器、对所有请求url进行签名验证
 */
@Component
@Slf4j
public class SignVerifyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 签名验证,所有请求都应按照报文格式{reqBody:'',sign:''}发起请求
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.debug("call sign filter");
        CacheHttpServletRequestWrapper requestWrapper;
        CacheHttpServletResponseWrapper responseWrapper;
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        String salt = "salt";
        try {
            requestWrapper = new CacheHttpServletRequestWrapper(httpReq);
            responseWrapper = new CacheHttpServletResponseWrapper(httpResp);
            if (HttpSignUtil.checkSign(requestWrapper.getBody(), salt, httpReq.getHeader(SIGN_HEADER_NAME))) {
                //继续请求
                chain.doFilter(requestWrapper, responseWrapper);
            } else {
                log.warn("check sign fail for on request");
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

            responseWrapper.setRespSign(salt);
            writeRespMsg(httpResp, responseWrapper.getResponseData());
        } catch (Exception e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error("request exception", e);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 使用原请求
     *
     * @param response
     * @param respMsg
     * @throws IOException
     */
    private void writeRespMsg(HttpServletResponse response, byte[] respMsg) throws IOException {
        ServletOutputStream output = response.getOutputStream();
        output.write(respMsg);
        output.flush();
        output.close();
    }
}
