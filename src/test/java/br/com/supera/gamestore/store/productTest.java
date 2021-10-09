package br.com.supera.gamestore.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import br.com.supera.gamestore.DTO.ProductDTO;
import br.com.supera.gamestore.model.entities.Product;
import br.com.supera.gamestore.model.repositories.ProductRepository;
import com.github.dbunit.rules.DBUnitRule;
import com.github.dbunit.rules.api.configuration.DBUnit;
import com.github.dbunit.rules.api.dataset.DataSet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class productTest {
/*
    Logger log;

    @Rule
    @Autowired
    private ProductRepository productRepository;

    @Before
    public void initializeTestClass() {
        log = LoggerFactory.getLogger(this.getClass());
    }

    @Test
    @DBUnit(allowEmptyFields = false)
    @DataSet("products.yml")
    public void readTest(){

        try{
            log.info("Testando leitura do banco");

            Product produto1 = new Product(obterProdutoId(312L));
            assertNotNull(produto1);

            Product produto2 = new Product(obterProdutoId(201L));
            assertNotNull(produto2);

            ArrayList< Product > produtos = new ArrayList < > ();
            produtos.addAll(productRepository.findAll());
            assertNotNull(produtos);

            log.info("Mostrando o primeiro produto da lista");
            log.info(produtos.get(0).toString());
            log.info("Mostrando o último produto da lista");
            log.info(produtos.get(produtos.size()-1).toString());

        }
        catch (Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    public Product obterProdutoId(Long id) {

        if(!productRepository.existsById(id)){
            return new Product();
        }

        return productRepository.findById(id).get();
    }


*/
}
