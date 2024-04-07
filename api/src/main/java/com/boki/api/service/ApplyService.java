package com.boki.api.service;

import com.boki.api.domain.Coupon;
import com.boki.api.repository.CouponCountRepository;
import com.boki.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void apply(Long userId) {
        Long couponCnt = couponCountRepository.increment();

        if (couponCnt > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
