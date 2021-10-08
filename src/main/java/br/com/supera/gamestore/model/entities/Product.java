package br.com.supera.gamestore.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "produtos")
public class Product {

    @Id
    public long id;

    @Column(name = "prod_nome", nullable = false)
    public String name;

    @Column(name = "prod_preco", nullable = false)
    public BigDecimal price;

    @Column(name = "prod_score", nullable = false)
    public short score;

    @Column(name = "prod_image", nullable = false)
    public String image;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", score=" + score +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && score == product.score && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, score, image);
    }
}