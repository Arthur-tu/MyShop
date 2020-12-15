package shop.example.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.example.shop.dto.CartDto;
import shop.example.shop.service.CartService;
import java.security.Principal;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String cartInfo(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("cart", new CartDto());
        } else {
            CartDto cartDto = cartService.getCartByUser(principal.getName());
            model.addAttribute("cart", cartDto);
        }
        return "cart";
    }
}