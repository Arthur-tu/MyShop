package shop.example.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.example.shop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
