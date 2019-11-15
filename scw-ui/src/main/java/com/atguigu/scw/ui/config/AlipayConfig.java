package com.atguigu.scw.ui.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    public static String app_id = "2016101500691640";
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDLBQqbEvGHTy7ahZwkAT/sl2v2NahX3FhBCPdTJlvx5TKvQRtlGDzf01uORaIENK1NcxYv21J68O2q3V/t1PLnUtP+Q1FvqdEavGZyYgkkaSmgzCeqHIpKLUlIDod+cJZoUHni0ro27gSu9DID03C9H8rM9tJKX1BkoWsvygM7n0RN0G/VGJgBjwfyZtJOK91JSsDqoqD0WMxDMRvJF05tyJ/Vq1ExwgjPeLBF9H/t1/M1tXkzYINxm/oflL5ebBu9vKhBwCnlaBgIOg67Wo+b3mTEkuLK0oDez0aAUFboNJT2iRk/ItGONkj1iIrTOzNuCfl2Bu3aWB+yyDvL1TWdAgMBAAECggEAeV/ZOF758ShA3boVYF0y53KbxAXOUEDWroL6wkZjn9mkFKy7ARSdiD8nNIai1xrbJTuR3yijRgb2QDm7WUf/tJaLUS7T6AtBjVdndCjySEdq8Mw9RnFrp+8tKZwuG0SaV2ENjbLoS2C/gg1SPYFSnG7+Ys2JaZdUS+VJXLfMeP6rhax9XzFiKGx4v/MzzE+8U5Tgj0vompzo54OWHDyHR3kmsADQRvREq+n7YRQ/yoihzRVj/ZANJa9qHdI+GwQKajgt3b+6TWGRbDrF+Y8xcR5VNUwPBi35iuZ1vEgiyYFw2JujbealQmbV6f2Un3XnhVV9MqKAAAIZm0ErroRIDQKBgQD/uXXfN6gsNY3uhM9nn7GNVsUbYcGpClzr3yUZyb4g46giupC4aArWt/Jn0vS6cQTIzlbkSjOopGSDxxI70qepaFXgY9w0+/MIlKAgcCo0UtMIQyhc61dw03ptzoqFzTLnyGC4JYxPaz5oILJBbi68kOMnvDHnxbCJIZId3FDE4wKBgQDLPQr1AIaHcviNUKJSwGIlbhMRwKXnWPNC+epf1jUfAif5sOMznSiAGPUsIaSWPJoBhhcMpISa8TN3BGXrKcQpsHjR5zJ8YEZfCnVcJNqnKqaVC3QZb9MxLYkm2L2zj+T1Tm5ygkqPA61pI+YqkxoTBQb4WlEYmBZlo9WI962jfwKBgQD1zDzJFoU2IS/QcKWus6eE9D83J4puwcA5E95kYC65DDmjT/Ik3hR0+JAuTW3f+xwfK34Hpm7rDUB0DZewGrX/NoM80J9r6jRbLgiPxWA7tNSVH4AfeDIvB4apHxq0q1zfvxGATs8D01BD5BGSpKaVpypAGjtZzQH3fWYl/4s/AwKBgF2ZIUCwG0Sp+DnslrURkKG4TePxGvauimmf+wZj+lbrgUmG4zvT9uD3nYHThJ8EDdEB1f+mjMNmrdL0HZPtyj+A3EOe/Cs3k3EVufLTrjcqd0lQ2wkT7OlLFUzkqIjXjhRDKK5nvrqacPRo6glVcb6spAcgYRS4higNCbxohHk1AoGAb5zgAcLktvJWAqxkuSmMucLL9Jr98qMap32YfF5pE5KFA+mg2bosfrysJ1ZzNekXyW87cVdQpRwu0eVKGztkvDeQ7Uvr3zKdzU9VFSQ7ZBWpU99rR7/MX/DHXc4YhH8jgNAZrKNFsNkofhnqfkXp0Ea4bfpIO1GLXroeD6lazBY=";
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAigxEAMPQ6xTYXhQbt1OxBKwXPO0+rH0EOVvbPNGrFS7HPAbHPMm58wOA/7BPn2C+a5aXQMNlWwMHtdR499j029lLtOhjZxHji7dKAjX8d22jYCI5ymBmLmHtXLrCd59yJTHbONnj18/vkZjypLLwUqFX1/z9jfgRndG/ftOfHi+10vNblTafSTt26W/vH+aZ9Jp8Cva7QGhKNrfCsKCXJSzR37PgO4b3cDaBMp4cBN4l6k8tdNQSqwWA+vgVylmY2bMMKaPvabqLBtx/Davg18pGfk+GiNbK9fYN4I8JK/2ksu2V8APrpsU4QdELkhM4VDfEJWCblwd9+5F7AZL8TQIDAQAB";
    public static String notify_url = "http://47.103.83.98/order/notify_url";
    public static String return_url = "http://47.103.83.98/order/return_url";
    public static String sign_type = "RSA2";
    public static String charset = "utf-8";
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    public static String log_path = "D:\\";
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}