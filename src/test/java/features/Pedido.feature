# language: pt
Funcionalidade: Funcionalidades do Pedido Controller

  Contexto:
    Dado um usuário autorizado

  Cenário: Iniciar Pedido
    Quando o usuário inicia um novo pedido
    Então o pedido é criado com sucesso

  Cenário: Adicionar Produtos
    Dado um pedido existente
    Quando o usuário adiciona um produto ao pedido
    Então o produto é adicionado com sucesso

  Cenário: Remover Produtos
    Dado um pedido existente com produtos
    Quando o usuário remove um produto do pedido
    Então o produto é removido com sucesso

  Cenário: Listar Pedidos
    Dado pedidos existentes
    Quando o usuário solicita a listagem de todos os pedidos
    Então a lista de pedidos é retornada com sucesso

  Cenário: Buscar Status Pagamento
    Dado um pedido existente
    Quando o usuário solicita a busca do status de pagamento do pedido
    Então o status de pagamento é retornado com sucesso

  Cenário: Listar Dados do Pedido
    Dado um pedido existente
    Quando o usuário solicita a listagem dos detalhes do pedido
    Então os detalhes do pedido são retornados com sucesso

  Cenário: Alterar Status do Pedido
    Dado um pedido existente
    Quando o usuário solicita a alteração do status do pedido
    Então o status do pedido é alterado com sucesso


