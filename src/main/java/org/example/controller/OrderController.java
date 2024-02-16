package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.OrderDto;
import org.example.dto.request.OrderCreateRequest;
import org.example.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.message.InfoMessage.ORDER_CREATED;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(OrderCreateRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.ok(ORDER_CREATED.getMessage());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String id) {
        OrderDto order = orderService.getOrderDtoById(id);
        return ResponseEntity.ok(order);
    }
}
