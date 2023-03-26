package com.persoanltoy.backend.domains.order.controller;

import com.persoanltoy.backend.common.BaseTest;
import com.persoanltoy.backend.domains.common.payment.PaymentType;
import com.persoanltoy.backend.domains.order.domain.entity.value.Address;
import com.persoanltoy.backend.domains.order.domain.entity.value.Receiver;
import com.persoanltoy.backend.domains.order.domain.entity.value.ShippingInfo;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderCreateDto;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RecordApplicationEvents
class OrderCreateControllerTest extends BaseTest {

    //    @Autowired
//    ApplicationEvents events;
    private String memberId;

    private String accessToken;


    @BeforeEach
    void baseProcess() throws Exception {
        this.memberId = super.memberDummyGenerate.generate();
        this.accessToken = super.memberDummyGenerate.getAccessToken();
    }

    @Test
    @DisplayName("create success")
    void createSuccess() throws Exception {
        //given
        String url = "/order";
        OrderCreateDto orderCreateDto = OrderCreateDto.builder()
                .orderProducts(
                        List.of(
                                new OrderProduct("1", 5),
                                new OrderProduct("2", 4),
                                new OrderProduct("3", 3),
                                new OrderProduct("4", 2),
                                new OrderProduct("5", 1)
                        )
                )
                .shippingInfo(
                        new ShippingInfo(
                                new Address("151-870", "12378912", "123213"),
                                "배송시 연락부탁드립니다.",
                                new Receiver("홍길동", "01000000000")
                        )
                )
                .paymentType(PaymentType.CARD)
                .build();

        //when - then
        super.mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(super.objectMapper.writeValueAsString(orderCreateDto))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(
                        document(
                                "order-create",
                                requestFields(
                                        fieldWithPath("orderProducts").description("주문상품목록"),
                                        fieldWithPath("orderProducts[].productId").description("상품아이디"),
                                        fieldWithPath("orderProducts[].quantity").description("수량"),
                                        fieldWithPath("shippingInfo").description("배송정보"),
                                        fieldWithPath("shippingInfo.address").description("주소"),
                                        fieldWithPath("shippingInfo.address.zipCode").description("우편번호"),
                                        fieldWithPath("shippingInfo.address.address1").description("주소1"),
                                        fieldWithPath("shippingInfo.address.address2").description("주소2"),
                                        fieldWithPath("shippingInfo.message").description("배송메시지"),
                                        fieldWithPath("shippingInfo.receiver").description("받는사람"),
                                        fieldWithPath("shippingInfo.receiver.receiverName").description("수령인이름"),
                                        fieldWithPath("shippingInfo.receiver.receiverPhone").description("수령인번호"),
                                        fieldWithPath("paymentType").description("결제유형")
                                )
                        )
                )
        ;
//        int count = (int) events.stream(OrderCreateEvent.class).count();
//        assertEquals(1, count);
    }

    @Test
    @DisplayName("create fail -> external api connection error")
    void createFail() throws Exception {
        //given
        String url = "/order";
        OrderCreateDto orderCreateDto = OrderCreateDto.builder()
                .orderProducts(
                        List.of(
                                new OrderProduct("1", 5),
                                new OrderProduct("2", 4),
                                new OrderProduct("3", 3),
                                new OrderProduct("4", 2),
                                new OrderProduct("5", 1)
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
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                        .content(super.objectMapper.writeValueAsString(orderCreateDto))
                )
                .andDo(print())
                .andExpect(status().isInternalServerError())
        ;
    }

}