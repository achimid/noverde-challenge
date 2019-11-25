# noverde-challenge-api

Este é um projeto de desafio (https://github.com/noverde/challenge)

Para acessar a API acesse o link https://noverde-challenge.herokuapp.com (Pode demorar alguns segundos até a aplicação inicializar)

#### Execução

Entre na raiz do projeto e execute os comandos a seguir:
    
    ->  ./gradlew bootJar
    ->  java -jar build/libs/noverde-1.0.0.jar 

#### Considerações

Para o propoósito de arquitetura, a aplicação esta seperada em 2 pacotes (noverde.api e noverde.core), com o objetivo de separar o que é funcionalidade da API e o processamento dos emprestimos. Fazendo desta maneira, é possivel separar as aplicações visando escalabilidade.

Para o propoósito desse exercicio, utilizei um banco de dados em memória (H2), para facilitar o deploy da aplicação no Heroku.

Todas as configurações estão no arquivo application.properties, e podem ser sobrescrita por veriaveis de ambiente.


#### Sugestão de melhorias
- Utilizar um banco de dados não relacional para ter uma malhor performace

- Utilizar um serviço de fila (pub/sub ou RabitMq) ao invez de utilizar Jobs/Workers

- Melhorar o sistema de cache utilizado

- Implementar mais cenários de teste
    - Teste de Integração
    - Utilizar o Mockito nas requisições externas
    - Cobrir fluxos de teste
    - Aumentar coverage

- Efetuar a separação entre API e Processamento em dois projetos para uma melhora de escalabilidade. 

- Trabalhar com conainner (Docker)
