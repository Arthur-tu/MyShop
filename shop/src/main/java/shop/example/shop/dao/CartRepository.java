package shop.example.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.example.shop.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
