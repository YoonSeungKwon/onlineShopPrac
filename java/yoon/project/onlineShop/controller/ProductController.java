package yoon.project.onlineShop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yoon.project.onlineShop.dto.request.ProductRegisterDto;
import yoon.project.onlineShop.dto.request.ProductUpdateDto;
import yoon.project.onlineShop.dto.response.ProductResponse;
import yoon.project.onlineShop.dto.response.ResponseMessage;
import yoon.project.onlineShop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<ResponseMessage> productInfo(){

        List<ProductResponse> result = productService.getList();

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("상품 목록");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<ResponseMessage> productDetail(@PathVariable String idx){

        ProductResponse result = productService.getDetail(idx);

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage(result.getName() + " 상품 정보");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ResponseMessage> register(@RequestBody ProductRegisterDto dto){

        ProductResponse result = productService.register(dto);

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("상품 등록 완료");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{idx}")
    public ResponseEntity<ResponseMessage> update(@PathVariable String idx, @RequestBody ProductUpdateDto dto){

        ProductResponse result = productService.update(idx, dto);

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("상품 변경 완료");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable String idx){

        ProductResponse result = productService.delete(idx);

        ResponseMessage message = new ResponseMessage();
        message.setCode(HttpStatus.OK);
        message.setMessage("상품 삭제 완료");
        message.setData(result);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }



}
