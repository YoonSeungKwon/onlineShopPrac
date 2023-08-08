package yoon.project.onlineShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.project.onlineShop.domain.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

}
