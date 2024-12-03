package com.ratolla.twitter_simplified.configuration;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAKeyConverter {

    public static RSAPrivateKey convertStringToPrivateKey(String privateKeyPem) throws Exception {
        // Remova os cabeçalhos e rodapés do PEM
        String privateKeyContent = privateKeyPem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", ""); // Remove espaços em branco e novas linhas

        // Decodifique o conteúdo Base64
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyContent);

        // Converta para um objeto RSAPrivateKey
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}

