package yoon.project.onlineShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yoon.project.onlineShop.domain.Members;
import yoon.project.onlineShop.domain.Orders;
import yoon.project.onlineShop.domain.Products;
import yoon.project.onlineShop.dto.request.OrderDto;
import yoon.project.onlineShop.dto.response.OrderResponse;
import yoon.project.onlineShop.repository.OrderRepository;
import yoon.project.onlineShop.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private OrderResponse toResponse(Orders order){
        return new OrderResponse(order.getBuyer().getName(), order.getProduct().getName(), order.getAddress(), order.getCount(), order.getCount() * order.getProduct().getPrice(), order.getDate());
    }

    public OrderResponse makeOrder(String productIdx, OrderDto dto){
        Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Products product = productRepository.findProductsByIdx(Long.parseLong(productIdx));
        if(member == null)
            throw new UsernameNotFoundException(null);
        if(product == null)
            throw new UsernameNotFoundException(null);

        Orders order = Orders.builder()
                .buyer(member)
                .product(product)
                .address(dto.getAddress())
                .count(dto.getCount())
                .build();

        return toResponse(orderRepository.save(order));
    }

    public OrderResponse updateOrder(String productIdx, OrderDto dto){
        Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Products product = productRepository.findProductsByIdx(Long.parseLong(productIdx));
        Orders prevOrder = orderRepository.findOrdersByBuyerAndProduct(member, product);
        if(prevOrder == null)
            throw new UsernameNotFoundException(null);

        prevOrder.setAddress(dto.getAddress());
        prevOrder.setCount(dto.getCount());
        return toResponse(orderRepository.save(prevOrder));
    }

}
