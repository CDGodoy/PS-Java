package br.com.supera.gamestore.controller;

import br.com.supera.gamestore.DTO.ProductDTO;
import br.com.supera.gamestore.DTO.ProductResponseDTO;
import br.com.supera.gamestore.model.entities.Checkout;
import br.com.supera.gamestore.model.entities.Product;
import br.com.supera.gamestore.model.repositories.ProductRepository;
import br.com.supera.gamestore.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /*
    public List < Product > obterProdutos() {
        return productRepository.findAll();
    }
     */

    @PostMapping("/produto")
    public ResponseEntity<ProductResponseDTO> salvarProduto(@RequestBody @Validated ProductDTO dto){
        Product product = productRepository.save(dto.transformaParaObjeto());
        return new ResponseEntity<>(ProductResponseDTO.transformaEmDTO(product), HttpStatus.CREATED);
    }

    @GetMapping(path = "/produto")
    public ResponseEntity<List<ProductResponseDTO>> obterTodosProdutos(){
        List<Product> products = productRepository.findAll();

        List<ProductResponseDTO> productsDTO = ProductResponseDTO.transformaListEmDTO(products);

        return new ResponseEntity<>(productsDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/produto/sort")
    public ResponseEntity<List<ProductResponseDTO>> obterProdutos (@RequestParam String atributo){
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, atributo));

        List<ProductResponseDTO> productsDTO = ProductResponseDTO.transformaListEmDTO(products);

        return new ResponseEntity<>(productsDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/produto/pagina/{page}")
    public Iterable<Product> obterProdutosPorPagina(@RequestParam String atributo,
                                                    @PathVariable int page,
                                                    @RequestParam int itens) {
        Pageable pagina = PageRequest.of(page, itens, Sort.by(Sort.Direction.ASC, atributo));

        return productRepository.findAll(pagina);
    }

    public Product obterProdutoId(Long id){
        if(!productRepository.existsById(id)){
            return new Product();
        }
        return productRepository.findById(id).get();
    }

    @GetMapping(path = "/produto/{id}")
    public ResponseEntity<ProductResponseDTO> obterProdutoIdResp(@PathVariable Long id){

        ProductResponseDTO produto = ProductResponseDTO.transformaEmDTO(obterProdutoId(id));

        return new ResponseEntity<>(produto, HttpStatus.ACCEPTED);
    }

    ArrayList < Product > carrinho = new ArrayList < > ();

    @PostMapping(path = "produto/add")
    public ResponseEntity<ProductResponseDTO> addCarrinho(@RequestParam Long id){

        Product produto = obterProdutoId(id);
        carrinho.add(produto);

        return new ResponseEntity<>(ProductResponseDTO.transformaEmDTO(produto), HttpStatus.CREATED);
    }

    public ResponseEntity<ProductResponseDTO> delItemCarrinho(@PathVariable Long id){
        Product produto = obterProdutoId(id);
        carrinho.remove(produto);

        return new ResponseEntity<>(ProductResponseDTO.transformaEmDTO(produto), HttpStatus.CREATED);
    }
    
    @GetMapping(path = "/carrinho")
    public ResponseEntity<List<Product>> obterCarrinho(){
        return new ResponseEntity<>(carrinho, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/checkout")
    public ResponseEntity<Checkout> Checkout(){

        Checkout checkout = CheckoutService.obterCheckout(carrinho);

        return new ResponseEntity<>(checkout, HttpStatus.ACCEPTED);
    }


}