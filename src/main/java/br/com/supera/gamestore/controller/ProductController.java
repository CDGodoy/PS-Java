package br.com.supera.gamestore.controller;

import br.com.supera.gamestore.model.entities.Product;
import br.com.supera.gamestore.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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

        productRepository.save(produto);
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

    ArrayList<Optional<Product>> carrinho = new ArrayList<>();

    @PostMapping(path = "/add/{id}")
    public Optional<Product> addCarrinho(@PathVariable Long id){
        Optional<Product> produto = obterProdutoId(id);

        carrinho.add(produto);

        return produto;
    }

    @GetMapping(path = "carrinho")
    public ArrayList<Optional<Product>> getCarrinho() {
        return carrinho;
    }
}
