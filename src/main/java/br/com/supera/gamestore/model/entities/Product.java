package br.com.supera.gamestore.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "produtos")
public class Product {

    @Id
    public long id;

    @Column(name = "prod_nome")
    public String name;

    @Column(name = "prod_preco")
    public BigDecimal price;

    @Column(name = "prod_score")
    public short score;

    @Column(name = "prod_image")
    public String image;
    
    public Product(){

    }

    public Product(Long id, String name, BigDecimal price, short score, String image) {
        this.id = id;
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