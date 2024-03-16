# FIAP Pedido Service

O FIAP pedido service é um projeto escrito em Java que simula pedidos de uma lanchonete. Ele oferece funcionalidades como criação de pedidos, cadastro de clientes, alteraçao de pedidos, cadastros de produtos, alteração de produtos.

Funcionalidades

O projeto possui as seguintes funcionalidades:

  -  Criação de Pedidos: Registra novos pedidos dos clientes, incluindo produtos, quantidade e detalhes de pagamento e envio.

  -  Cadastro de Clientes: Armazena informações dos clientes, como nome, endereço e histórico de compras.

  -  Alteração de Pedidos: Permite modificar pedidos existentes, como adicionar ou remover itens.

  -  Cadastro de Produtos: Registra novos produtos com detalhes como nome, preço e quantidade em estoque.

  -  Alteração de Produtos: Permite editar informações dos produtos, como preço ou descrição.
   
# Requisitos

Para executar o FIAP Lanchonete Service, você precisará ter instalado em seu ambiente de desenvolvimento:

 - Java Development Kit (JDK) 17 ou superior
 - Maven
 - Docker

# Configuração do ambiente

 - Clone este repositório para o seu ambiente local.
 - Certifique-se de rodar o documento de docker-compose.yml 
 - Abra o terminal e navegue até o diretório raiz do projeto.

# Executando o projeto

 No diretório raiz do projeto, execute o seguinte comando para compilar o código:
    
    mvn compile

 Após a compilação, execute o seguinte comando para iniciar a aplicação:

    mvn spring-boot:run

# Contribuindo

Se você quiser contribuir para o desenvolvimento deste projeto, siga as etapas abaixo:

   - Faça um fork deste repositório.
   - Crie uma branch para suas alterações:

    git checkout -b minha-feature 

   - Faça suas alterações e faça o commit:

    git commit -m "Minha contribuição"

   - Envie para o repositório original:

    git push origin minha-feature

   - Crie um pull request, descrevendo suas alterações.
 
# Licença

Este projeto está licenciado sob a MIT License.

# Arquitetura
![FIAP](https://github.com/RichardDallaqua/fiap-pedido-service/assets/134017102/7a5e0689-849b-43e8-ad2f-ec891a55e39d)

# Padrão SAGA Coreografada:

O padrão SAGA escolhido para o fluxo de pagamento foi o Coreografado, com as seguintes justificativas:

1) Menor complexidade para desenvolvimento, já que cada microsserviço fica responsável por uma parte da transação não havendo, portanto, a necessidade de adicionar um componente central;
2) Como eles atuam de forma indepente isso gera maior flexibilidade para futuras evoluções nos microsserviços;
3) Maior distribuição de carga durante a transação dos serviços porque a transação é processada em paralelo.

