package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.UserVerificationCode;
import com.pentalog.KitKat.Repository.UserVerificationCodeRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserVerificationCodeRepository userVerificationCodeRepository;
    public RedisService(RedisTemplate<String, String> redisTemplate, UserVerificationCodeRepository userVerificationCodeRepository) {
        this.redisTemplate = redisTemplate;
        this.userVerificationCodeRepository = userVerificationCodeRepository;
    }

    public void storeVerificationCode(String email, String verificationCode){
        redisTemplate.opsForValue().set(email, verificationCode, 5, TimeUnit.MINUTES);
        //userVerificationCodeRepository.save(new UserVerificationCode(email, verificationCode));
    }

    public String getStoredCode(String email){
        return redisTemplate.opsForValue().get(email);
    }
}
