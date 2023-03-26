package com.persoanltoy.backend.dummy;

import com.persoanltoy.backend.domains.common.model.Money;
import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.order.domain.entity.Order;
import com.persoanltoy.backend.domains.order.domain.entity.value.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class OrderDummyGenerate {

    @Autowired
    protected EntityManager entityManager;

    public List<String> generate(int i, String memberId) {
        Member member = entityManager.createQuery("select m from Member m where m.id = ?1", Member.class)
                .setParameter(1, memberId)
                .getSingleResult();

        List<Order> collect = IntStream.range(0, i)
                .mapToObj(data -> {
                    Orderer orderer = new Orderer(member.getId(), member.getNickName());
                    Address address = new Address("zipcode", "address1", "address2");
                    Receiver receiver = new Receiver("무명인" + data, "01000000000");
                    ShippingInfo shippingInfo = new ShippingInfo(address, "sample message", receiver);
                    List<OrderLine> orderLines = List.of(
                            new OrderLine("1", new Money(1000), 5),
                            new OrderLine("2", new Money(2000), 4),
                            new OrderLine("3", new Money(3000), 3),
                            new OrderLine("4", new Money(4000), 2),
                            new OrderLine("5", new Money(5000), 1)
                    );
                    return Order.create(orderer, orderLines, shippingInfo);
                })
                .collect(Collectors.toList());

        collect.forEach(data -> {
            entityManager.persist(data);
        });

        return collect.stream().map(Order::getId).collect(Collectors.toList());
    }

}
