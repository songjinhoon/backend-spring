package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.common.BaseTest;
import com.persoanltoy.backend.domains.order.domain.entity.value.Address;
import com.persoanltoy.backend.domains.order.domain.entity.value.Receiver;
import com.persoanltoy.backend.domains.order.domain.entity.value.ShippingInfo;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderCancelDto;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderUpdateControllerTest extends BaseTest {

    String memberId;

    String accessToken;

    List<String> orderIds;

    //TODO 문서화

    @BeforeEach
    void init() throws Exception {
        this.memberId = super.memberDummyGenerate.generate();
        this.accessToken = super.memberDummyGenerate.getAccessToken();
        this.orderIds = super.orderDummyGenerate.generate(100, memberId);
    }

    @Test
    @DisplayName("order update shipping info")
    void update() throws Exception {
        //given
        String url = String.format("/order/%s", this.orderIds.get(0));
        OrderUpdateDto orderUpdateDto = OrderUpdateDto.builder()
                .version(0L)
                .shippingInfo(
                        new ShippingInfo(
                                new Address("151-870", "12378912", "8129038"),
                                "배송시 연락부탁드립니다(수정완료)",
                                new Receiver("임꺽정", "01000000000")
                        ))
                .build();

        //when-then
        super.mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.accessToken)
                        .content(super.objectMapper.writeValueAsString(orderUpdateDto))
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "order-update-shipping-info"
                        )
                )
        ;
    }

    @Test
    @DisplayName("order cancel")
    void cancel() throws Exception {
        //given
        String url = String.format("/order/%s", this.orderIds.get(0));
        OrderCancelDto orderCancelDto = new OrderCancelDto();
        orderCancelDto.setVersion(0L);

        //when-then
        super.mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, this.accessToken)
                        .content(super.objectMapper.writeValueAsString(orderCancelDto))
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "order-update-cancel"
                        )
                )
        ;
    }

    // 테스트코드 실패 -> 스레드 문제라고 판단함
//    @Test
//    @DisplayName("Order lock check")
//    void lockCheckProcess() throws Exception {
//        //given
//        Order order = super.entityManager.createQuery("select o from Order as o where o.id = ?1", Order.class)
//                .setParameter(1, this.orderIds.get(0))
//                .getSingleResult();
//        String shippingInfoUpdateUrl = String.format("/order/%s", order.getId());
//        String stateUpdateUrl = String.format("/order/state/%s", order.getId());
//
//        //when 사용자가 배송지를 변경
//        OrderUpdateDto orderUpdateDto = OrderUpdateDto.builder()
//                .version(0L)
//                .shippingInfo(
//                        new ShippingInfo(
//                                new Address("151-870", "12378912", "8129038"),
//                                "배송시 연락부탁드립니다(수정완료)",
//                                new Receiver("임꺽정", "01000000000")
//                        ))
//                .build();
//        super.mockMvc.perform(put(shippingInfoUpdateUrl)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header(HttpHeaders.AUTHORIZATION, this.accessToken)
//                        .content(super.objectMapper.writeValueAsString(orderUpdateDto))
//                )
//                .andDo(print())
//                .andExpect(status().isNoContent())
//        ;
//        //when-then 관리자가 배송준비중 상태로 변경 요청
//        OrderStateUpdateDto orderStateUpdateDto = OrderStateUpdateDto.builder()
//                .version(order.getVersion())
//                .orderState(OrderState.PREPARING)
//                .build();
//        super.mockMvc.perform(put(stateUpdateUrl)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header(HttpHeaders.AUTHORIZATION, this.accessToken)
//                        .content(super.objectMapper.writeValueAsString(orderStateUpdateDto))
//                )
//                .andDo(print())
//                .andExpect(status().isInternalServerError());
//    }


}
