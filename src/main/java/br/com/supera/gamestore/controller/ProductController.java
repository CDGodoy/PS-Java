package br.com.supera.gamestore.controller;

import br.com.supera.gamestore.DTO.ProductDTO;
import br.com.supera.gamestore.DTO.ProductResponseDTO;
import br.com.supera.gamestore.model.entities.Checkout;
import br.com.supera.gamestore.model.entities.Product;
import br.com.supera.gamestore.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping(path = "/produto")
    public List < Product > obterProdutos() {
        return productRepository.findAll();
    }

    @PostMapping("/produto")
    public ResponseEntity<ProductResponseDTO> salvarProduto(@RequestBody @Validated ProductDTO dto){
        Product product = productRepository.save(dto.transformaParaObjeto());
        return new ResponseEntity<>(ProductResponseDTO.transformaEmDTO(product), HttpStatus.CREATED);
    }

    @GetMapping(path = "/produto/sort/{ordem}")
    public List < Product > obterProdutos(@PathVariable String ordem) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, ordem));
    }

    @GetMapping(path = "/produto/pagina/{page}/{ordem}")
    public Iterable < Product > obterProdutosPorPagina(@PathVariable String ordem, @PathVariable int page) {
        Pageable pagina = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, ordem));

        return productRepository.findAll(pagina);

    }

    @GetMapping(path = "/produto/{id}")
    public Product obterProdutoId(@PathVariable Long id) {

        if(!productRepository.existsById(id)){
            return new Product();
        }

        return productRepository.findById(id).get();
    }

    ArrayList < Product > carrinho = new ArrayList < > ();

    @PostMapping(path = "produto/add/{id}")
    public Product addCarrinho(@PathVariable Long id) {
        Product produto = obterProdutoId(id);

        carrinho.add(produto);

        return produto;
    }

    @DeleteMapping(path = "/carrinho/{id}")
    public ArrayList < Product > delItemId(@PathVariable Long id) {
        Product produto = obterProdutoId(id);
        carrinho.remove(produto);

        return carrinho;
    }

    @GetMapping(path = "/carrinho")
    public ArrayList < Product > getCarrinho() {
        return carrinho;
    }

    @GetMapping(path = "/checkout")
    public Checkout finalizarCompra() {

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