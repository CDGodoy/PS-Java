package br.com.supera.gamestore.service;

import br.com.supera.gamestore.model.entities.Checkout;
import br.com.supera.gamestore.model.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;

public class CheckoutService {

    /**
     * Este método efetua o cálculo do checkout.
     * @param carrinho Carrinho com os produtos adicionados.
     * @return ResponseEntity com
     */
    public static ResponseEntity<Checkout> obterCheckout(List<Product> carrinho){
        BigDecimal subTotal = new BigDecimal("0"); //Criando valor zerado de um subtotal
        @Digits(integer = 1, fraction = 2)
        BigDecimal frete = new BigDecimal("0"); //Criando um valor zerado do frete
        BigDecimal totalCompra;
        boolean freteGratis = false;

        /* Este ciclo for percorre todos os itens do carrinho e a cada produto, acrescenta 10.0
         * ao frete e adiciona o valor do produto ao subTotal.
         */
        for (Product p:
                carrinho) {
            frete = frete.add(BigDecimal.TEN);
            subTotal = subTotal.add(new BigDecimal(String.valueOf(p.getPrice())));
        }

        //Caso o subtotal seja maior que 250, o frete será multiplicado por 0, para ser grátis
        if (subTotal.compareTo(new BigDecimal("250")) > 0) {
            frete = frete.multiply(BigDecimal.ZERO);
            freteGratis = true;
        }

        totalCompra = subTotal.add(frete);

        Checkout checkout = new Checkout(subTotal, frete, totalCompra, freteGratis);

        return new ResponseEntity<>(checkout, HttpStatus.ACCEPTED);
    }

}
