package com.persoanltoy.backend.domains.common.payment;

import com.persoanltoy.backend.domains.common.payment.exception.KakaoStrategyException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        try {
            Thread.sleep(5000);
            throw new RuntimeException("카카오 연계 실패");
        } catch (Exception e) {
            throw new KakaoStrategyException(e.getMessage());
        }
    }

    @Override
    public void refund(int amount) {
        try {
            Thread.sleep(5000);
            throw new RuntimeException("카카오 연계 실패");
        } catch (Exception e) {
            throw new KakaoStrategyException(e.getMessage());
        }
    }

}
