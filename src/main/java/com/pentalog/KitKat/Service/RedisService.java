package com.pentalog.KitKat.Service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeVerificationCode(String email, String verificationCode){
        redisTemplate.opsForValue().set(email, verificationCode, 5, TimeUnit.MINUTES);
    }

    public String getStoredCode(String email){
        return redisTemplate.opsForValue().get(email);
    }
}
