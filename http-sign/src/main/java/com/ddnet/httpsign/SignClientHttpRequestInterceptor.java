package com.ddnet.httpsign;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static com.ddnet.httpsign.HttpSignUtil.SIGN_HEADER_NAME;

/**
 * @author: Vinson.Ding
 * @create: 2019-04-03
 **/
@Slf4j
public class SignClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("sign", HttpSignUtil.sign(body, "salt"));
        ClientHttpResponse resp = execution.execute(request, body);
        byte[] respBody = IOUtils.toByteArray(resp.getBody());
        if (HttpSignUtil.checkSign(respBody, "salt", resp.getHeaders().getFirst(SIGN_HEADER_NAME))) {
            return resp;
        }
        throw new IOException("check sign error");
    }
}
