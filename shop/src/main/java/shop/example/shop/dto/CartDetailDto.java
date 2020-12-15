package shop.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.example.shop.domain.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDetailDto {
    private String title;
    private Long productId;
    private Double price;
    private Double amount;
    private Double sum;

    public CartDetailDto(Product product){
        this.title = product.getTitle();
        this.productId = product.getId();
        this.price = product.getPrice();
        this.amount = 1.0;
        this.sum = product.getPrice();
    }

}
