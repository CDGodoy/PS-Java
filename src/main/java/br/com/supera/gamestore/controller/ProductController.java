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
    public Product obterProdutoId(@PathVariable Long id){
        return productRepository.findById(id).get();
    }

    ArrayList<Product> carrinho = new ArrayList<>();

    @PostMapping(path = "/add/{id}")
    public Product addCarrinho(@PathVariable Long id){
        Product produto = obterProdutoId(id);

        carrinho.add(produto);

        return produto;
    }

    /*
    @PostMapping(path = "/delete/index/{index}")
    public ArrayList<Optional<Product>> delItemIndex(@PathVariable Long index){
        carrinho.remove(index);
        return carrinho;
    }
     */

    @PostMapping(path = "/delete/{id}")
    public ArrayList<Product> delItemId(@PathVariable Long id){
        Product produto = obterProdutoId(id);
        carrinho.remove(produto);

        System.out.println(produto.toString());

        return carrinho;
    }

    @GetMapping(path = "/carrinho")
    public ArrayList<Product> getCarrinho() {
        return carrinho;
    }

    @GetMapping(path = "/precoCarrinho")
    public String somar(){
        BigDecimal precoTotal = new BigDecimal("0");
        BigDecimal frete = new BigDecimal("0");
        BigDecimal dez = new BigDecimal("10");
        for (Product p:
             carrinho) {
            frete = frete.add(dez);
            BigDecimal preco = new BigDecimal(String.valueOf(p.getPrice()));
            precoTotal = precoTotal.add(preco);
        }
        return precoTotal.toString();
    }

}