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

import javax.validation.Valid;
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


    /*
    @PostMapping(path = "/produto")
    public Product novoProduto(@RequestParam Long id,
                               @RequestParam String nome,
                               @RequestParam BigDecimal valor,
                               @RequestParam short score,
                               @RequestParam String imagem) {

        Product produto = new Product(id, nome, valor, score, imagem);

        productRepository.save(produto);
        return produto;
    }*/

    /*
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},produces = MediaType.APPLICATION_JSON_VALUE, path = "/produto")
    public ResponseEntity<RequestResponse> novoProdutoJson(@RequestBody @Valid Product produto){

        RequestResponse resposta = new RequestResponse();

        if(produto.id < 0l){
            resposta.setErro(true);
            resposta.setMensagem("O id do produto deve ser maior ou igual a zero");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }

        /*
        if(produto.name.isEmpty()){
            resposta.setErro(true);
            resposta.setMensagem("Nome do produto é obrigatório");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
        if(produto.price.toString().isEmpty() || produto.price.compareTo(BigDecimal.ZERO)<0){
            resposta.setErro(true);
            resposta.setMensagem("Preço do produto é obrigatório e deve ser maior ou igual a 0");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
        if(produto.score < 0){
            resposta.setErro(true);
            resposta.setMensagem("O score do produto deve ser maior ou igual a zero");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }
        if(produto.image.isEmpty()){
            resposta.setErro(true);
            resposta.setMensagem("A imagem do produto é obrigatória");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
        }

        resposta.setErro(false);
        resposta.setMensagem("Produto inserido com sucesso!");

        productRepository.save(produto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resposta);

    }
    */

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

    /*
    @PostMapping(path = "/delete/index/{index}")
    public ArrayList<Optional<Product>> delItemIndex(@PathVariable Long index){
        carrinho.remove(index);
        return carrinho;
    }
     */

    @DeleteMapping(path = "/carrinho/delete/{id}")
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
        BigDecimal frete = new BigDecimal("0");
        BigDecimal freeFrete = new BigDecimal("250");
        BigDecimal total;

        for (Product p:
                carrinho) {
            frete = frete.add(BigDecimal.TEN);
            BigDecimal preco = new BigDecimal(String.valueOf(p.getPrice()));
            subTotal = subTotal.add(preco);
        }

        if (subTotal.compareTo(freeFrete) > 0) {
            frete = frete.multiply(BigDecimal.ZERO);
        }

        total = subTotal.add(frete);

        Checkout checkout = new Checkout(subTotal, frete, total);

        return checkout;

    }

}