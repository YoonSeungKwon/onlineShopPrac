package yoon.project.onlineShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoon.project.onlineShop.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

}
