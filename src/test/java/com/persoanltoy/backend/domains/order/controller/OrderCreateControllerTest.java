package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.common.BaseTest;
import com.persoanltoy.backend.domains.common.payment.PaymentType;
import com.persoanltoy.backend.domains.order.domain.entity.value.Address;
import com.persoanltoy.backend.domains.order.domain.entity.value.Receiver;
import com.persoanltoy.backend.domains.order.domain.entity.value.ShippingInfo;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderCreateDto;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RecordApplicationEvents
class OrderCreateControllerTest extends BaseTest {

//    @Autowired
//    ApplicationEvents events;

    @Test
    @DisplayName("create success")
    void createSuccess() throws Exception {
        //given
        String generate = super.memberDummyGenerate.generate();
        String accessToken = super.memberDummyGenerate.getAccessToken();
        String url = "/order";
        OrderCreateDto orderCreateDto = OrderCreateDto.builder()
                .orderProducts(
                        List.of(
                                new OrderProduct("1", 1),
                                new OrderProduct("2", 2),
                                new OrderProduct("3", 3),
                                new OrderProduct("4", 4),
                                new OrderProduct("5", 5)
                        )
                )
                .shippingInfo(
                        new ShippingInfo(
                                new Address("151-870", "12378912", "8129038"),
                                "배송시 연락부탁드립니다.",
                                new Receiver("홍길동", "01000000000")
                        )
                )
                .paymentType(PaymentType.KAKAO)
                .build();

        //when - then
        super.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION.toLowerCase(), accessToken)
                        .content(super.objectMapper.writeValueAsString(orderCreateDto))
                )
                .andDo(print())
                .andExpect(status().isCreated())
        ;
//        int count = (int) events.stream(OrderCreateEvent.class).count();
//        assertEquals(1, count);
    }

}