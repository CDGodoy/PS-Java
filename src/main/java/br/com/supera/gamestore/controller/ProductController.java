package br.com.supera.gamestore.controller;

import br.com.supera.gamestore.model.entities.Product;
import br.com.supera.gamestore.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product novoProduto(@RequestParam Long id,
                               @RequestParam String nome,
                               @RequestParam BigDecimal valor,
                               @RequestParam short score,
                               @RequestParam String imagem){

        Product produto = new Product(id, nome, valor, score, imagem);

        //produtos.add(produto);

        productRepository.save(produto);
        //System.out.println(produtos);
        return produto;
    }

    @GetMapping
    public Iterable<Product> obterProdutos(){
        return productRepository.findAll();
    }
    @GetMapping(path = "/pagina/{page}")
    public Iterable<Product> obterProdutosPorPagina(@PathVariable int page){
        Pageable pagina = PageRequest.of(page,5);

        return productRepository.findAll(pagina);

    }

    @GetMapping(path="/{id}")
    public Optional<Product> obterProdutoId(@PathVariable Long id){
        return productRepository.findById(id);
    }



}
