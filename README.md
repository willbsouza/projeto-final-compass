## Projeto Final - Compass.UOL (ONG - Projeto Doação e Solicitação de Roupas)
<ul>
  <li>API REST com Spring Boot</li>
  <li>Microserviços</li>
  <li>Testes Unitários dos Services com JUnit5</li>
  <li>Banco de Dados MySQL</li>
  <li>Spring Security</li>
  <li>Tratamento de Exceções</li>
  <li>Documentação dos Microserviços com Swagger pode ser acessada pelo endpoint: /swagger-ui.html</li>
  <li>Eureka Server - Porta 8761</li>
  <li>Config Server - Porta 8888 - Arquivo application.yml contém as configurações do Microserviço de transporte que está hospedado
  em https://github.com/willbsouza/microservice-repo</li>
</ul>

### Microserviço (ONG - Projeto Doação e Solicitação de Roupas) - Porta 8080
#### Principais Entidades e Endpoints

#### Entidade Doador

<ul>
  <li>id : Integer</li>
  <li>nome : String </li>
  <li>cpf : String</li>
  <li>senha : String - Criptografada ao inserir no banco</li>
  <li>doacoes : List< Doacao ></li>
  <li>enderecos : List< Endereco ></li>
  <li>telefones : List< Telefone ></li>
</ul>

#### Endpoints Doador:

<ul>
  <li>GET - /doadores</li>
  <li>GET - /doadores/{id}</li>
  <li>POST - /doadores</li>
  <li>PUT - /doadores/{id}</li>
  <li>PUT - /doadores/{id}/enderecos</li>
  <li>PUT - /doadores/{id}/telefones</li>
  <li>DELETE - /doadores/{id}</li>
</ul>

#### Entidade Donatário

<ul>
  <li>id : Integer</li>
  <li>nome : String </li>
  <li>cpf : String</li>
  <li>senha : String - Criptografada ao inserir no banco</li>
  <li>doacoes : List< Doacao ></li>
  <li>enderecos : List< Endereco ></li>
  <li>telefones : List< Telefone ></li>
</ul>

#### Endpoints Donatário:

<ul>
  <li>GET - /donatarios</li>
  <li>GET - /donatarios/{id}</li>
  <li>POST - /donatarios</li>
  <li>PUT - /donatarios/{id}</li>
  <li>PUT - /donatarios/{id}/enderecos</li>
  <li>PUT - /donatarios/{id}/telefones</li>
  <li>DELETE - /donatarios/{id}</li>
</ul>

#### Entidade ONG
<ul>
  <li>id : Integer</li>
  <li>filial : String </li>
  <li>cnpj : String</li>
  <li>senha : String - Criptografada ao inserir no banco</li>
  <li>doacoes : List< Doacao ></li>
  <li>solicitacoes : List< Solicitacao ></li>
  <li>enderecos : List< Endereco ></li>
  <li>telefones : List< Telefone ></li>
</ul>

#### Endpoints ONG:

<ul>
  <li>GET - /ongs</li>
  <li>GET - /ongs/{id}</li>
  <li>POST - /ongs</li>
  <li>PUT - /ongs/{id}</li>
  <li>DELETE - /ongs/{id}</li>
</ul>

#### Entidade Doação

<ul>
  <li>id : Integer</li>
  <li>dataCadastro : LocalDate </li>
  <li>item : Item</li>
  <li>quantidade : Integer</li>
  <li>modalidade : Modalidade-Enum - Ao escolher DELIVERY no POST ou PUT - Microserviço de Transporte é chamado.</li>
  <li>doador : Doador</li>
  <li>ong : ONG</li>
</ul>

#### Endpoints Doação:

<ul>
  <li>GET - /doacoes</li>
  <li>GET - /doacoes/{id}</li>
  <li>POST - /doacoes</li>
  <li>PUT - /doacoes/{id}</li>
  <li>DELETE - /doacoes/{id}</li>
</ul>

#### Entidade Solicitação:

<ul>
  <li>id : Integer</li>
  <li>dataCadastro : LocalDate </li>
  <li>item : Item</li>
  <li>quantidade : Integer</li>
  <li>donatario : Donatario</li>
  <li>ong : ONG</li>
</ul>

#### Endpoints Solicitação:

<ul>
  <li>GET - /solicitacoes</li>
  <li>GET - /solicitacoes/{id}</li>
  <li>POST - /solicitacoes</li>
  <li>PUT - /solicitacoes/{id}</li>
  <li>DELETE - /solicitacoes/{id}</li>
</ul>

#### Entidade Item:

<ul>
  <li>id : Integer</li>
  <li>tipo : Tipo-Enum</li>
  <li>quantidade : Integer</li>
  <li>categoria : Categoria</li>
</ul>

#### Endpoints Item:

<ul>
  <li>GET - /itens</li>
  <li>GET - /itens/{id}</li>
  <li>PUT - /itens/{id}</li>
</ul>

#### Entidade Endereço:

<ul>
  <li>id : Integer</li>
  <li>logradouro : String</li>
  <li>numero : String </li>
  <li>complemento : String</li>
  <li>bairro : String</li>
  <li>cidade : String</li>
  <li>estado : String</li>
  <li>cep : String</li>
</ul>

#### Endpoints Endereço:

<ul>
  <li>GET - /enderecos</li>
  <li>GET - /enderecos/{id}</li>
  <li>POST - /enderecos</li>
  <li>PUT - /enderecos/{id}</li>
  <li>DELETE - /enderecos/{id}</li>
</ul>

#### Entidade Telefone:

<ul>
  <li>id : Integer</li>
  <li>numero : String </li>
</ul>

#### Endpoints Telefone:

<ul>
  <li>GET - /telefones</li>
  <li>GET - /telefones/{id}</li>
  <li>POST - /telefones</li>
  <li>PUT - /telefones/{id}</li>
  <li>DELETE - /telefones/{id}</li>
</ul>

#### Entidade Categoria:

<ul>
  <li>id : Integer</li>
  <li>nome : String </li>
  <li>itens : List< Item ></li>
</ul>

#### Endpoints Categoria:

<ul>
  <li>GET - /categorias</li>
  <li>GET - /categorias/{id}</li>
  <li>POST - /categorias</li>
  <li>PUT - /categorias/{id}</li>
  <li>DELETE - /categorias/{id}</li>
</ul>

<div>
  <img src="https://user-images.githubusercontent.com/76399078/163861938-b2031666-a762-4a3c-8395-d6d310e11115.PNG" width: 720px />
</div>

### Microserviço (Transporte de Doações) - Porta 8081

#### Entidade Transporte

<ul>
  <li>id : Integer</li>
  <li>enderedoOrigem : String </li>
  <li>enderedoDestino : String</li>
  <li>item : String</li>
  <li>quantidade : Integer</li>
  <li>idDoacao : Integer</li>
  <li>dataPedido : LocalDate</li>
  <li>dataPrevisaoServico : LocalDate</li>
</ul>

#### Endpoints Transporte:

<ul>
  <li>GET - /transportes</li>
  <li>GET - /partidos/{idDoacao}</li>
  <li>POST - /transportes</li>
  <li>PUT - /partidos/{idDoacao}</li>
  <li>DELETE - /partidos/{idDoacao}</li>
</ul>

<div>
  <img src="https://user-images.githubusercontent.com/76399078/163861521-6f48c2a3-551f-4162-bb82-815530f7899c.PNG" width: 720px />
</div>

##### Observações e validações: 
##### 1. Um mesmo donatário só pode solicitar de 1 a 3 unidades por solicitação. E no máximo 3 unidades em um período de 30 dias. 
##### 2. Quantidade solicitada não podem ser inferior a 1 nem superior a 3.
##### 3. Não é possível solicitar uma quantidade superior a quantidade do item disponível no estoque, ou solicitar um item que ainda não existe nos registros.
##### 4. Quantidade doada não pode ser menor que 1.
##### 5. Ao cancelar doações onde possuía modalidade DELIVERY, o serviço de Transporte também é chamado e cancelado a coleta.
##### 6. Ao atualizar doações fazendo alterações na donalidade (PRESENCIAL/DELIVERY), o serviço de Transporte também é chamado e cancelado ou solicitado a coleta.
##### 7. Todos os campos são obrigatórios, não podendo ser vazio ou nulo, com exceção do complemento da entidade endereço.
##### 8. A tabela item funciona como um estoque de roupas, onde itens já existentes são incrementados ou decrementados. E para novos itens, são realizados novos registros.
