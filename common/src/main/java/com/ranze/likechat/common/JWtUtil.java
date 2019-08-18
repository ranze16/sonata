package com.ranze.likechat.common;

import io.jsonwebtoken.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class JWtUtil {
    private static RSAPrivateKey privateKey = RSAUtils.getPrivateKey(RSAUtils.modulus, RSAUtils.private_exponent);
    private static RSAPublicKey publicKey = RSAUtils.getPublicKey(RSAUtils.modulus, RSAUtils.public_exponent);

    public static String getToken(String uid, String privateKey, int expireTimeInHour) {
        long endTime = System.currentTimeMillis() + expireTimeInHour * 3600 * 1000;
        return Jwts.builder()
                .setSubject(uid).setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS256, privateKey).compact();
    }

    public static JWTResult checkToken(String token, String pubKey) {
        try {
            Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody();
            String sub = claims.get("sub", String.class);
            return new JWTResult(true, sub, JWTResult.OK, "ok");
        } catch (ExpiredJwtException e) {
            return new JWTResult(false, null, JWTResult.EXPIRED, "token expired");
        } catch (Exception e) {
            return new JWTResult(false, null, JWTResult.NO_AUTH, "invalid");
        }
    }


}
