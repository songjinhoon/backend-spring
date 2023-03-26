package com.persoanltoy.backend.domains.order.domain.entity.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderEventHandler {

    /*
     * 포인트
     * 1. 비동기로 실행되어야한다. 외부 연계서비스의 성능으로 인해 우리 서비스가 지장있으면 안되기 떄문.
     * 2. 주문이 저장되었을 경우에만 실행한다.
     * 3. 주문 저장 실패 시 실행 할 필요가 없다.
     */
//    @Async
//    @TransactionalEventListener(classes = OrderCreateEvent.class, phase = TransactionPhase.AFTER_COMMIT)
//    public void handle(final OrderCreateEvent orderCreateEvent) {
//        log.info("order create event start!");
//        try {
//            orderCreateEvent.getPaymentStrategy().pay(orderCreateEvent.getTotalAmounts().getValue());
//            log.info("KAKAO payment request success");
//        } catch (Exception e) {
//            log.error("KAKAO payment request error");
//        }
//        log.info("order create event end!");
//    }



}
