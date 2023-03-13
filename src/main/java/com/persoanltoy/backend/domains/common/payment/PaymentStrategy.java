package com.persoanltoy.backend.domains.common.payment;

public interface PaymentStrategy {

    void pay(int amount) throws Exception;

}
