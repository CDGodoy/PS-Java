# PS-Java
<p align="center">  
<a href="## Descrição">Descrição</a>  
<a href="## Como utilizar a API">Como utilizar a API</a>  
<a href="## Autor">Autor</a>  
<a href="### Sobre os testes">Sobre os testes</a>
</p>  

<p>NOTA: Acabei me esquecendo de colocar um @DeleteMapping("/deletar/{id}") na linha 132 do ProductController.java. Como notei o erro após o prazo, não alterei o código, este </p>

## Descrição

<p align="center">Construção de uma camada de serviço de um pseudo ecommerce de games mobile utilizando Java</p>  
<p align="center">  

## Porque SpringBoot
O Framework springboot foi escolhido principalmente pela familiaridade que possuo com o mesmo, escolhi estudar ele pois o mesmo é bastante completo, possui uma boa documentação e diversos guias e tutoriais existentes na internet, facilitando e muito o aprendizado.

## Como utilizar a API
### Na web
Utilize este link(http://spring-test-supera.herokuapp.com/) no seu RESTClient (Postman, Insomia, etc...) e siga as instruções do tópico <a href="###Rotas de uso">Rotas de uso</a>

### Na sua máquina
Clone este repositório git e na raiz do projeto, execute o comando:  `java -jar gameStore.jar` e siga as instruções do tópico <a href="###Rotas de uso">Rotas de uso</a>  
Caso deseje gerar o .jar por conta própria: `mvn package`, e use o mesmo comando `java -jar ...` substituindo o '...' pelo nome do .jar gerado que estará na pasta "target"


### Rotas de uso

- Para cadastrar um produto no banco, faça uma requisição POST para `/produto` com um JSON no formato:
```json  
{  
"id": 501,  
"name": "Horizon Zero Dawn",  
"price": 115.8,  
"score": 290,  
"image": "horizon-zero-dawn.png"  
}  
```  

- Para obter todos os produtos que estão cadastrados no banco, faça uma requisição GET para `/produto`.
- Para obter um determinado produto que está cadastrado no banco, faça uma requisição GET para `/produto/{id}`(substitua o `{id}` por um número).
- Para obter todos os produtos do banco em ordem crescente de determinado atributo, faça uma requisição GET para `/produto/sort?atributo=id`. Você pode trocar o id pelos outros atributos que o produto possui (name, price, score, image, id).
- Para obter os produtos por página, faça uma requisição GET para `/produto/pagina/{numeroPagina}?atributo=&itens=`. Por exemplo, para obter a 1° página de produtos, ordenado pelo nome com 5 itens por página: `/produto/pagina/0?atributo=name&itens=5`.
- Para adicionar um produto ao carrinho, faça uma requisição POST para `/produto/add?id={id}`, substitua o `{id}` por um número.
- Para remover um produto do carrinho, faça uma requisição DELETE para `/carrinho/delete/{id}`, substitua o `{id}` por um número.
- Para consultar os produtos no carrinho, faça uma requisição GET para `/carrinho`.
- Para acessar o checkout, faça uma requisição GET para `/checkout`

## Autor

| Nome | GitHub |LinkedIn|  
| :---: | :---: |:---: |  
| Carlos Daniel de Godoy Barros Nascimento  | CDGodoy |https://www.linkedin.com/in/carlosdgodoy/|

### Sobre os testes
Infelizmente não consegui gerar os testes automatizados da aplicação, apesar de já ter criado outros testes para aplicações mais simples, não consegui "encaixar" os testes nesse PS.

