package com.boki.api.service;

import com.boki.api.domain.Coupon;
import com.boki.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    public ApplyService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public void apply(Long userId) {
        long couponCnt = couponRepository.count();

        if (couponCnt > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
