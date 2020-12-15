package shop.example.shop.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import shop.example.shop.domain.Product;
import shop.example.shop.dto.ProductDto;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
    Product toProduct(ProductDto dto);

    @InheritInverseConfiguration
    ProductDto fromProduct(Product product);

    List<Product> toProductList(List<ProductDto> productDtos);

    List<ProductDto> fromProductList(List<Product> products);
}
