package yoon.project.onlineShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.project.onlineShop.domain.Members;
import yoon.project.onlineShop.domain.Orders;
import yoon.project.onlineShop.domain.Products;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findOrdersByBuyerAndProduct(Members members, Products products);
}
