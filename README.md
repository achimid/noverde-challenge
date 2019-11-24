# noverde-challenge-api

Este é um projeto de desafio (https://github.com/noverde/challenge)

Para acessar a API acesse o link https://noverde-challenge.herokuapp.com (Pode demorar alguns segundos até a aplicação inicializar)

#### Execução

Entre na raiz do projeto e execute os comandos a seguir:
    
    ->  ./gradlew bootJar
    ->  java -jar build/libs/noverde-1.0.0.jar 

#### Considerações


#### Sugestão de melhorias
- Utilizar um banco de dados não relacional para ter uma malhor performace

- Utilizar um serviço de fila (pub/sub ou RabitMq) ao invez de utilizar Jobs/Workers

- Melhorar o sistema de cache utilizado

- Implementar mais cenários de teste

- Efetuar a separação entre API e Processamento em dois projetos para uma melhora de escalabilidade. 