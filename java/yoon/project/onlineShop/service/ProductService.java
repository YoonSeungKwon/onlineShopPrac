package yoon.project.onlineShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yoon.project.onlineShop.domain.Members;
import yoon.project.onlineShop.domain.Products;
import yoon.project.onlineShop.dto.request.ProductRegisterDto;
import yoon.project.onlineShop.dto.request.ProductUpdateDto;
import yoon.project.onlineShop.dto.response.ProductResponse;
import yoon.project.onlineShop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private ProductResponse toResponse(Products product){
        return new ProductResponse(product.getName(), product.getSeller().getName(), product.getPrice(), product.getRegdate());
    }

    public List<ProductResponse> getList(){
        List<Products> list = productRepository.findAll();
        List<ProductResponse> result = new ArrayList<>();
        for(Products p: list){
            result.add(toResponse(p));
        }
        return result;
    }

    public ProductResponse getDetail(String idx){
        return toResponse(productRepository.findProductsByIdx(Long.parseLong(idx)));
    }

    public ProductResponse register(ProductRegisterDto dto){
        Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(member == null)
            return null; // ExceptionHandle
        Products products = Products.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .seller(member)
                .img(dto.getImg())
                .build();
        return toResponse(productRepository.save(products));
    }

    public ProductResponse update(String idx, ProductUpdateDto dto){
        Products products = productRepository.findProductsByIdx(Long.parseLong(idx));
        Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(products == null || member != products.getSeller())
            return null; // ExceptionHandle
        products.setName(dto.getName());
        products.setImg(dto.getImg());
        products.setPrice(dto.getPrice());
        return toResponse(productRepository.save(products));
    }

    public ProductResponse delete(String idx){
        Products product = productRepository.findProductsByIdx(Long.parseLong(idx));
        Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(product.getSeller() != member)
            return null; // ExceptionHandle
        ProductResponse result = toResponse(product);
        productRepository.delete(product);
        return result;
    }
}
