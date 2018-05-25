package io.ddnet.vuesimple.utils;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 生成密码
 * Created by Vinson.Ding on 2018/5/24.
 */
public class PasswordUtils {
    public static int PWD_HASH_NUMS = 37;
    private static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static String getSecretPassword(String userName, String password) {
        ByteSource osalt = ByteSource.Util.bytes(userName.getBytes(DEFAULT_CHARSET));
        return new Sha256Hash(password, osalt, PWD_HASH_NUMS).toBase64();
    }
}
