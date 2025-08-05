package com.xiuxiu.microservices.order.service;

import com.xiuxiu.microservices.order.client.InventoryClient;
import com.xiuxiu.microservices.order.dto.OrderRequest;
import com.xiuxiu.microservices.order.event.OrderPlacedEvent;
import com.xiuxiu.microservices.order.model.Order;
import com.xiuxiu.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest) {

        //1.using mockito
        //2.use wiremock
        var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setSkuCode(orderRequest.skuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);

            //send the message to kafka topic
            //orderNumber, email
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(),orderRequest.userDetails().email());
            log.info("Start - Sending OrderPlacedEvent {} to kafka topic order-placed",orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to kafka topic order-placed",orderPlacedEvent);

        }else{
            throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
        }

    }
}
