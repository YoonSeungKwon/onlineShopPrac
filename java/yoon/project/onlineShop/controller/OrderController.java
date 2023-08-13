package yoon.project.onlineShop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoon.project.onlineShop.dto.request.OrderDto;
import yoon.project.onlineShop.dto.response.OrderResponse;
import yoon.project.onlineShop.dto.response.ResponseMessage;
import yoon.project.onlineShop.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{productIdx}")
    public ResponseEntity<ResponseMessage> orderProduct(@PathVariable String productIdx, @RequestBody OrderDto dto){

        OrderResponse result = orderService.makeOrder(productIdx, dto);

        ResponseMessage message = new ResponseMessage();

        message.setCode(HttpStatus.OK);
        message.setMessage("주문 성공");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{productIdx}")
    public ResponseEntity<ResponseMessage> updateProduct(@PathVariable String productIdx, @RequestBody OrderDto dto){

        OrderResponse result = orderService.updateOrder(productIdx, dto);

        ResponseMessage message = new ResponseMessage();

        message.setCode(HttpStatus.OK);
        message.setMessage("주문 변경 완료");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
