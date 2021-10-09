package br.com.supera.gamestore.DTO;

import br.com.supera.gamestore.model.entities.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class ProductResponseDTO {

    public long id;

    public String name;

    public BigDecimal price;

    public short score;

    public String image;

    public static ProductResponseDTO transformaEmDTO(Product product){
        return new ProductResponseDTO(product.getId(), product.getName(),
                                      product.getPrice(), product.getScore(),
                                      product.getImage());
    }

    public static List<ProductResponseDTO> transformaListEmDTO(List<Product> products){
        List<ProductResponseDTO> dtos = new ArrayList<>();

        for (Product product:
             products) {
            dtos.add(transformaEmDTO(product));
        }
        return dtos;
    }

}
