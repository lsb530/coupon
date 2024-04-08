package com.boki.api.service;

import com.boki.api.producer.CouponCreateProducer;
import com.boki.api.repository.AppliedUserRepository;
import com.boki.api.repository.CouponCountRepository;
import com.boki.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    private final AppliedUserRepository appliedUserRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer, AppliedUserRepository appliedUserRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
    }

    public void apply(Long userId) {
        Long apply = appliedUserRepository.add(userId);

        if (apply != 1) {
            return;
        }

        Long couponCnt = couponCountRepository.increment();

        if (couponCnt > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
