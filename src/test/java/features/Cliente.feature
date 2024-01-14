# language: pt


Funcionalidade: Operações do Controlador de Clientes

  Cenário: Obter informações do cliente por ID
    Dado um cliente com ID 4a8bc9e8-ab42-49eb-8eb3-8bcdb969a378
    Quando o cliente solicita informações para esse ID
    Então o código de status da resposta deve ser 200
    E o corpo da resposta deve conter o nome do cliente John Doe

  Cenário: Criar um novo cliente
    Dado um cliente com os seguintes detalhes:
      | nome       | cpf            | telefone       |
      | Jane Smith | 123.456.789-09 | (555) 123-4567 |
    Então o código de status da resposta deve ser 201
