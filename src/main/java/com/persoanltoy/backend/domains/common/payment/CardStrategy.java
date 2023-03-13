package com.persoanltoy.backend.domains.common.payment;

import com.persoanltoy.backend.domains.common.payment.exception.KakaoStrategyException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            throw new KakaoStrategyException(e.getMessage());
        }
    }

}
