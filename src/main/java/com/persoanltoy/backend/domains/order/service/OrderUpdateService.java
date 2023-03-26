package com.persoanltoy.backend.domains.order.service;

import com.persoanltoy.backend.domains.order.domain.entity.Order;
import com.persoanltoy.backend.domains.order.infra.OrderRepository;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderCancelDto;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderStateUpdateDto;
import com.persoanltoy.backend.domains.order.dto.reqeust.OrderUpdateDto;
import com.persoanltoy.backend.exception.VersionConflictException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderUpdateService {

    private final OrderRepository orderRepository;

    /**
     * 선점잠금(비관적 락) -> 화면에 대한 이슈처리에 대해서 적합하지 못함. 사용자가 배송지변경 API 요청 후 정상실행 되었을때, 관리자가 바라보고 있는 화면에서의 배송지정보 데이터로 배송상태를 변경한다면 변경이 되는 이슈가 있다.
     * 비선전잠금(낙관적 락) -> 화면에 대한 이슈처리에 대해서 적합하지 못함. 사용자가 배송지변경 API 요청 후 정상실행 되었을때, 관리자가 바라보고 있는 화면에서의 배송지정보 데이터로 배송상태를 변경한다면 변경이 되는 이슈가 있다.
     * 비전전잠금 확장기능
     * - 사용자가 배송지변경 API 요청 후 커밋 직전에 슬립을 잠시 걸어두고, 관리자가 배송상태변경 API 요청하였더니, 관리자꺼 커밋 후 사용자꺼 처리시점에 ObjectOptimisticLockingFailureException 발생.
     * - 여기서 알 수 있는건, 트랜잭션 커밋 시점에 업데이트 쿼리에 최종 버전으로 조회한다는 것.
     * - 사용자 조회당시 버전 0, 관리자 조회당시 버전 0, 관리자 업데이트 커밋 후 버전1, 사용자 업데이트 커밋 시점에 버전 0으로 처리하기에 오류발생.
     * 오프라인 선점 잠금 -> 처리 가능
     */
    @Transactional
    public void update(String id, OrderUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        this.versionCheck(order, orderUpdateDto.getVersion());

        order.update(orderUpdateDto);
    }

    @Transactional
    public void update(String id, OrderStateUpdateDto orderUpdateDto) {
        Order order = orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        this.versionCheck(order, orderUpdateDto.getVersion());

        order.update(orderUpdateDto);
    }

    @Transactional
    public void cancel(String id, @Valid OrderCancelDto orderCancelDto) {
        Order order = orderRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        this.versionCheck(order, orderCancelDto.getVersion());

        order.cancel();
    }

    private void versionCheck(Order order, Long version) {
        if (!order.matchVersion(version)) {
            throw new VersionConflictException("Version Conflict!!");
        }
    }

}
