package yoon.project.onlineShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoon.project.onlineShop.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


}
