package shop.example.shop.service;

import org.springframework.stereotype.Service;
import shop.example.shop.dao.CartRepository;
import shop.example.shop.dao.ProductRepository;
import shop.example.shop.domain.Cart;
import shop.example.shop.domain.Product;
import shop.example.shop.domain.User;
import shop.example.shop.dto.CartDetailDto;
import shop.example.shop.dto.CartDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Cart createCart(User user, List<Long> productIds) {
        Cart cart = new Cart();
        cart.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        cart.setProducts(productList);
        return cartRepository.save(cart);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addProducts(Cart cart, List<Long> productIds) {
        List<Product> products = cart.getProducts();
        List<Product> newProductsList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductsList.addAll(getCollectRefProductsByIds(productIds));
        cart.setProducts(newProductsList);
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartByUser(String name) {
        User user = userService.findByName(name);
        if (user == null || user.getCart() == null) {
            return new CartDto();
        }

        CartDto cartDto = new CartDto();
        Map<Long, CartDetailDto> mapByProductId = new HashMap<>();

        List<Product> products = user.getCart().getProducts();
        for (Product product : products) {
            CartDetailDto detail = mapByProductId.get(product.getId());
            if (detail == null) {
                mapByProductId.put(product.getId(), new CartDetailDto(product));
            } else {
                detail.setAmount(detail.getAmount() + 1.0);
                detail.setSum(detail.getSum() + product.getPrice());
            }
        }

        cartDto.setCartDetails(new ArrayList<>(mapByProductId.values()));
        cartDto.aggregate();

        return cartDto;
    }
}
