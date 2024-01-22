# language: pt
Funcionalidade: Produto Controller

Cenário: Cadastrar um produto
Dado que eu tenho um produto com os seguintes detalhes
| nome      | categoria | preco |
| Produto A | Lanche    | 10.0 |
Quando eu faço uma requisição para o endpoint cadastrarProduto
Então a resposta deve ter o status CREATED
E o corpo da resposta deve conter os detalhes do produto

Cenário: Buscar produtos por categoria
Dado que existem produtos com os seguintes detalhes
| nome       | categoria | preco |
| Produto A  | Lanche    | 10.0 |
| Produto B  | Lanche    | 15.0 |
Quando eu faço uma requisição para o endpoint buscarProdutosPorCategoria com a categoria "Lanche"
Então a resposta deve ter o status OK
E o corpo da resposta deve conter produtos com a categoria "Lanche"


