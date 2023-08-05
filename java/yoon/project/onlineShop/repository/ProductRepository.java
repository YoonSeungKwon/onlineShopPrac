package yoon.project.onlineShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.project.onlineShop.domain.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    Products findProductsByIdx(long idx);

}
