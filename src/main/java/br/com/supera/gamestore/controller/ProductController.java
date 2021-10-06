package br.com.supera.gamestore.controller;

import br.com.supera.gamestore.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @GetMapping(path = "/qualquer")
    public Product obterProduto(){

        BigDecimal valor = new BigDecimal("20.00");
        short score = 200;
        Product produto = new Product("Jogo", valor, score, "imagem");

        return produto;
    }

    public Product obterProdutos(){

    }

}
