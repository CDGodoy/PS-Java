package br.com.supera.gamestore.model.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Checkout {
    private BigDecimal subTotal;
    private BigDecimal frete;
    private BigDecimal total;

    public Checkout(BigDecimal subTotal, BigDecimal frete, BigDecimal total) {
        this.subTotal = subTotal;
        this.frete = frete;
        this.total = total;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "subTotal=" + subTotal +
                ", frete=" + frete +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkout checkout = (Checkout) o;
        return Objects.equals(subTotal, checkout.subTotal) && Objects.equals(frete, checkout.frete) && Objects.equals(total, checkout.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subTotal, frete, total);
    }
}
