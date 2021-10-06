package br.com.supera.gamestore.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "prod_nome")
    public String name;

    @Column(name = "prod_preco")
    public BigDecimal price;

    @Column(name = "prod_score")
    public short score;

    @Column(name = "prod_image")
    public String image;

    public Product(String name, BigDecimal price, short score, String image) {
        this.name = name;
        this.price = price;
        this.score = score;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}