package br.com.supera.gamestore.service;

import br.com.supera.gamestore.model.entities.Checkout;
import br.com.supera.gamestore.model.entities.Product;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;

public class CheckoutService {

    public static Checkout obterCheckout(List<Product> carrinho){
        BigDecimal subTotal = new BigDecimal("0");
        @Digits(integer = 1, fraction = 2)
        BigDecimal frete = new BigDecimal("0");
        BigDecimal freeFrete = new BigDecimal("250");
        BigDecimal total;

        for (Product p:
                carrinho) {
            frete = frete.add(BigDecimal.TEN);
            subTotal = subTotal.add(new BigDecimal(String.valueOf(p.getPrice())));
        }

        if (subTotal.compareTo(freeFrete) > 0) {
            frete = frete.multiply(BigDecimal.ZERO);
        }

        total = subTotal.add(frete);

        Checkout checkout = new Checkout(subTotal, frete, total);

        return checkout;

    }

}
