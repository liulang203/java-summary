package com.ddnet.httpsign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 用于生成Http请求签名
 */
@Slf4j
public class HttpSignUtil {

    public static final String SIGN_HEADER_NAME = "sign";

    /**
     * 校验请求签名的有效性
     *
     * @return
     */
    public static boolean checkSign(byte[] body, String salt, String sign) {
        try {
            if (StringUtils.isEmpty(salt)) {
                return false;
            }
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(body);
            byte[] sha1 = messageDigest.digest(salt.getBytes(UTF_8));
            return Arrays.equals(sha1, Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            log.error("check sign error", e);
        }
        return false;
    }

    public static String sign(byte[] msg, String salt) {

        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        messageDigest.update(msg);
        byte[] sha1 = messageDigest.digest(salt.getBytes(UTF_8));
        return Base64.getEncoder().encodeToString(sha1);
    }


}
