package br.com.supera.gamestore.controller;

import br.com.supera.gamestore.model.entities.Checkout;
import br.com.supera.gamestore.model.entities.Product;
import br.com.supera.gamestore.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping(path = "/produto")
    public Product novoProduto(@RequestParam Long id,
                               @RequestParam String nome,
                               @RequestParam BigDecimal valor,
                               @RequestParam short score,
                               @RequestParam String imagem){

        Product produto = new Product(id, nome, valor, score, imagem);

        productRepository.save(produto);
        return produto;
    }

    @GetMapping(path = "/produto")
    public List<Product> obterProdutos(){
        return productRepository.findAll();
    }

    @GetMapping(path = "/produto/sort/{ordem}")
    public List<Product> obterProdutos(@PathVariable String ordem){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,ordem));
    }

    @GetMapping(path = "/produto/pagina/{page}/{ordem}")
    public Iterable<Product> obterProdutosPorPagina(@PathVariable String ordem, @PathVariable int page){
        Pageable pagina = PageRequest.of(page,5, Sort.by(Sort.Direction.ASC,ordem));

        return productRepository.findAll(pagina);

    }

    @GetMapping(path="/produto/{id}")
    public Product obterProdutoId(@PathVariable Long id){
        return productRepository.findById(id).get();
    }

    ArrayList<Product> carrinho = new ArrayList<>();

    @PostMapping(path = "produto/add/{id}")
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

    @PostMapping(path = "/produto/delete/{id}")
    public ArrayList<Product> delItemId(@PathVariable Long id){
        Product produto = obterProdutoId(id);
        carrinho.remove(produto);

        return carrinho;
    }

    @GetMapping(path = "/carrinho")
    public ArrayList<Product> getCarrinho() {
        return carrinho;
    }

    @GetMapping(path = "/checkout")
    public Checkout finalizarCompra(){


        BigDecimal subTotal = new BigDecimal("0");
        BigDecimal frete = new BigDecimal("0");
        BigDecimal dez = new BigDecimal("10");
        BigDecimal freeFrete = new BigDecimal("250");
        BigDecimal total = new BigDecimal("0");

        for (Product p:
            carrinho){
            frete = frete.add(dez);
            BigDecimal preco = new BigDecimal(String.valueOf(p.getPrice()));
            subTotal = subTotal.add(preco);
        }

        if (subTotal.compareTo(freeFrete) == 1){
            frete = frete.multiply(new BigDecimal("0"));
        }

        total = subTotal.add(frete);

        Checkout checkout = new Checkout(subTotal, frete, total);

        return checkout;

    }

}