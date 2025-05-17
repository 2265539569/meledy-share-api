package com.cpujazz.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    /**
     * 令牌的过期时间，单位为秒。默认设置为7天。
     */
    public static final int ACCESS_EXPIRE = 60 * 60 * 24 * 7;

    /**
     * 加密算法类型，使用 HS256（HMAC SHA-256）算法。
     */
    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

    /**
     * 用于生成 JWT 签名的秘钥。建议秘钥长度大于等于 256 位，并且不可外泄。
     */
    private final static String SECRET = "melody-share-secret-key||melody-share-secret-key||melody-share-secret-key";

    /**
     * 使用秘钥生成的 SecretKey 实例。
     */
    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * 生成访问令牌（Access Token）。
     *
     * @param username 用户名字
     * @return 生成的 JWT 访问令牌
     */
    public static String genAccessToken(String username) {
        // 生成唯一的令牌ID
        String uuid = UUID.randomUUID().toString();

        // 设置令牌过期时间
        Date exprireDate = Date.from(Instant.now().plusSeconds(ACCESS_EXPIRE));

        return Jwts.builder()
                // 设置头部信息
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                // 设置自定义负载信息
                .claim("username", username)
                // 设置令牌ID
                .id(uuid)
                // 设置过期时间
                .expiration(exprireDate)
                // 设置签发时间
                .issuedAt(new Date())
                // 签名
                .signWith(KEY, ALGORITHM)
                .compact();
    }

    /**
     * 解析 JWT 令牌并返回其中的声明（Claims）。
     *
     * @param token JWT 令牌
     * @return 解析后的 Jws<Claims> 对象
     */
    public static Jws<Claims> parseClaim(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
    }

    /**
     * 解析并返回 JWT 令牌中的头部信息（JwsHeader）。
     *
     * @param token JWT 令牌
     * @return JWT 头部信息
     */
    public static JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }

    /**
     * 解析并返回 JWT 令牌中的负载信息（Claims）。
     *
     * @param token JWT 令牌
     * @return JWT 负载信息
     */
    public static Claims parsePayload(String token) {
        return parseClaim(token).getPayload();
    }
}
