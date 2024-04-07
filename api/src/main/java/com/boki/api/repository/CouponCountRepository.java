package com.boki.api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CouponCountRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public CouponCountRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cleanUp() {
        redisTemplate
                .delete("coupon_count");
    }


    public Long increment() {
        return redisTemplate
                .opsForValue()
                .increment("coupon_count");
    }
}
