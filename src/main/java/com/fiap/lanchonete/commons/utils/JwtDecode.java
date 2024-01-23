package com.fiap.lanchonete.commons.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtDecode {
    public static String getCPFFromJWT(String jwt) {
        try {
            java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
            String[] parts = jwt.split("\\.");
            String payloadJson = new String(decoder.decode(parts[1]));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payloadJson);

            return jsonNode.get("id").asText();
        } catch (Exception e) {
            return null;
        }
    }
}
