package br.com.supera.gamestore.DTO;

import br.com.supera.gamestore.model.entities.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

}
