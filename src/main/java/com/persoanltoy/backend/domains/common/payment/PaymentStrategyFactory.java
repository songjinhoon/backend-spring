package com.persoanltoy.backend.domains.common.payment;

public class PaymentStrategyFactory {

    public static PaymentStrategy getPaymentStrategy(PaymentType paymentType) {
        if (paymentType == PaymentType.KAKAO) {
            return new KakaoStrategy();
        }
        return new CardStrategy();
    }

}
