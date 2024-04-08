package com.boki.api.service;

import com.boki.api.producer.CouponCreateProducer;
import com.boki.api.repository.CouponCountRepository;
import com.boki.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void apply(Long userId) {
        // couponCountRepository.cleanUp();
        Long couponCnt = couponCountRepository.increment();

        if (couponCnt > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
