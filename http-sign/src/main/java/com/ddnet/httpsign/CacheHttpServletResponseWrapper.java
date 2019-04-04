package com.ddnet.httpsign;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import static com.ddnet.httpsign.HttpSignUtil.SIGN_HEADER_NAME;
/**
*  缓存HTTP响应结果
* @author: Vinson.Ding
* @create: 2019/4/4
**/
public class CacheHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer ;
    private ServletOutputStream out ;
    private PrintWriter writer;

    public CacheHttpServletResponseWrapper(HttpServletResponse resp) throws IOException {
        super(resp);
        buffer = new ByteArrayOutputStream();
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if(out != null) {
            return out;
        }
        return super.getOutputStream();
    }

    public void setRespSign(String salt) throws NoSuchAlgorithmException {
        String sign = HttpSignUtil.sign(buffer.toByteArray(), salt);
        this.setHeader(SIGN_HEADER_NAME, sign);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if(writer != null) {
            return writer;
        }
        return super.getWriter();
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    public byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    private class WapperedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos;

        public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
            bos = stream;
        }

        @Override
        public void write(int b) throws IOException {
            bos.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            bos.write(b, 0, b.length);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }
}
