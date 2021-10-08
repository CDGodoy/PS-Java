package br.com.supera.gamestore.DTO;

import br.com.supera.gamestore.model.entities.Product;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class ProductDTO {

    @NotNull(message = "{id.validation}")
    public long id;

    @NotBlank(message = "{name.validation}")
    public String name;

    @DecimalMin(value = "0.0", message = "{min.decimal.validation}", inclusive = false)
    @Digits(integer = 3, fraction = 2, message = "{required.validation}")
    public BigDecimal price;

    @NotNull(message = "{score.validation}")
    public short score;

    @NotBlank(message = "{image.validation}")
    public String image;


    public Product transformaParaObjeto(){
        return new Product(id, name, price, score, image);
    }

}
