

# PS-Java
## Descrição
<p align="center">Construção de uma camada de serviço de um pseudo ecommerce de games mobile utilizando Java</p>
<p align="center">

## Como utilizar a API
### Na web
Utilize este link(http://spring-test-supera.herokuapp.com/) no seu RESTClient (Postman, Insomia, etc...) e siga as instruções do tópico XXXXXX

### Na sua máquina
Clone este repositório git e na raiz do projeto, execute o comando:  `java -jar game store`  e siga as instruções do tópico XXXXXX

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
 - Para adicionar um produto ao carrinho, faça uma requisição POST para `/produto/add?id={id}`, substitua o {id} por um número.
 - Para remover um produto do carrinho, faça uma requisição DELETE para `/carrinho/delete/{id}`, substitua o {id} por um número.
 

## Autor

| Nome | GitHub |LinkedIn|
| :---: | :---: |:---: |
| Carlos Daniel de Godoy Barros Nascimento  | CDGodoy |https://www.linkedin.com/in/carlosdgodoy/|

