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

/**
 * Controller da aplicação
 */
@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //Lista que conterá os produtos que foram adicionados ao carrinho.
    ArrayList < Product > carrinho = new ArrayList < > ();

    /**
     * Este método salva o produto no banco de dados.
     * @param dto Produto e JSON.
     * @return ResponseEntity retornando o objeto do produto inserido em JSON.
     */
    @PostMapping("/produto")
    public ResponseEntity<ProductResponseDTO> salvarProduto(@RequestBody @Validated ProductDTO dto){
        Product product = productRepository.save(dto.transformaParaObjeto());
        return new ResponseEntity<>(ProductResponseDTO.transformaEmDTO(product), HttpStatus.CREATED);
    }

    /**
     * Este método obtém o todos os produtos do banco.
     * @return ResponseEntity com todos os objetos do banco em JSON.
     */
    @GetMapping(path = "/produto")
    public ResponseEntity<List<ProductResponseDTO>> obterTodosProdutos(){
        List<Product> products = productRepository.findAll();

        List<ProductResponseDTO> productsDTO = ProductResponseDTO.transformaListEmDTO(products);

        return new ResponseEntity<>(productsDTO, HttpStatus.ACCEPTED);
    }

    /**
     * Este método obtém todos os produtos do banco com uma ordem definida.
     * @param atributo Atributo que será utilizado como referência para ordenar em ordem crescente.
     * @return ResponseEntity com todos os objetos do banco em ordem em JSON.
     */
    @GetMapping(path = "/produto/sort")
    public ResponseEntity<List<ProductResponseDTO>> obterProdutos (@RequestParam String atributo){
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, atributo));

        List<ProductResponseDTO> productsDTO = ProductResponseDTO.transformaListEmDTO(products);

        return new ResponseEntity<>(productsDTO, HttpStatus.ACCEPTED);
    }

    /**
     * Este método obtém todos os produtos do banco com uma ordem definida e separado por páginas com tamanho definido.
     * @param atributo Atributo que será utilizado como referência para ordenar em ordem crescente.
     * @param page Número da página (começando em 0).
     * @param itens Quantidade de itens a serem exibidos por página.
     * @return Página solicitada.
     */
    @GetMapping(path = "/produto/pagina/{page}")
    public Iterable<Product> obterProdutosPorPagina(@RequestParam String atributo,
                                                    @PathVariable int page,
                                                    @RequestParam int itens) {
        Pageable pagina = PageRequest.of(page, itens, Sort.by(Sort.Direction.ASC, atributo));

        return productRepository.findAll(pagina);
    }

    /**
     * Este método obtém o produto por um determinado ID. Caso o id não exista, retorna um produto vazio.
     * @param id do produto a ser pesquisado.
     * @return produto.
     */
    public Product obterProdutoId(Long id){
        if(!productRepository.existsById(id)){
            return new Product();
        }
        return productRepository.findById(id).get();
    }

    /**
     * Este método obtém o produto por um determinado ID. Chamado através de uma requisição GET.
     * @param id id do produto a ser pesquisado.
     * @return ResponseEntity com o objeto encontrado.
     */
    @GetMapping(path = "/produto/{id}")
    public ResponseEntity<ProductResponseDTO> obterProdutoIdResp(@PathVariable Long id){

        ProductResponseDTO produto = ProductResponseDTO.transformaEmDTO(obterProdutoId(id));

        return new ResponseEntity<>(produto, HttpStatus.ACCEPTED);
    }


    /**
     * Este método adiciona um produto ao carrinho a partir do seu id.
     * @param id id do produto a ser pesquisado e inserido no carrinho.
     * @return ResponseEntity com o produto adicionado no carrinho.
     */
    @PostMapping(path = "produto/add")
    public ResponseEntity<ProductResponseDTO> addCarrinho(@RequestParam Long id){

        Product produto = obterProdutoId(id);
        carrinho.add(produto);

        return new ResponseEntity<>(ProductResponseDTO.transformaEmDTO(produto), HttpStatus.CREATED);
    }

    /**
     * Este método deleta um ítem do carrinho a partir do seu id.
     * @param id id do produto a ser pesquisado e deletado do carrinho.
     * @return ResponseEntity com o produto que foi deletado do carrinho.
     */
    public ResponseEntity<ProductResponseDTO> delItemCarrinho(@PathVariable Long id){
        Product produto = obterProdutoId(id);
        carrinho.remove(produto);

        return new ResponseEntity<>(ProductResponseDTO.transformaEmDTO(produto), HttpStatus.CREATED);
    }

    /**
     * Este método obtém os itens que foram adicionados ao carrinho.
     * @return ResponseEntity com os itens do carrinho.
     */
    @GetMapping(path = "/carrinho")
    public ResponseEntity<List<Product>> obterCarrinho(){
        return new ResponseEntity<>(carrinho, HttpStatus.ACCEPTED);
    }

    /**
     * Este método calcula e exibe os valores do pedido no checkout.
     * @return CheckoutService.
     */
    @GetMapping(path = "/checkout")
    public ResponseEntity<Checkout> Checkout(){
        return CheckoutService.obterCheckout(carrinho);
    }


}