package shop.example.shop.service;

import shop.example.shop.dto.ProductDto;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    void addToUserCart(Long productId, String username);
}
