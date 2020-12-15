package shop.example.shop.service;

import org.springframework.stereotype.Service;
import shop.example.shop.dao.ProductRepository;
import shop.example.shop.domain.Cart;
import shop.example.shop.domain.User;
import shop.example.shop.dto.ProductDto;
import shop.example.shop.mapper.ProductMapper;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CartService cartService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CartService cartService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public List<ProductDto> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserCart(Long productId, String username) {
        User user = userService.findByName(username);
        if(user == null){
            throw new RuntimeException("User not found. " + username);
        }
        Cart cart = user.getCart();
        if(cart == null){
            user.setCart(cartService.createCart(user, Collections.singletonList(productId)));
            userService.save(user);
        }
        else {
            cartService.addProducts(cart, Collections.singletonList(productId));
        }
    }
}
