<h1 align="center" style="font-size: 40px">Organize API</h1>
<p align="center" style="font-size: 18px">
  Uma API de gerenciamento de escola.
</p>
<br>
<br>
<p align="center">
  <img src="https://i.imgur.com/CGGzrbw.png" width="226" alt="Tecnologias" />
</p>

## Sobre

<p>
   Com o intuito de administrar uma instituição e seus alunos, essa api irá possibilitar
    gerenciar todos os alunos, cursos, matérias e professores
</p>

### Requisitos

- Postgres: `^13`
- Java: `^11`

Esse projeto foi configurado com [Spring Initializr](https://start.spring.io/).
> recomendo a instalação do maven localmente, mas o projeto tem uma versão portatil nos arquivos [`mvnw`](./mvnw) e [`mvnw.cmd`](./mvnw.cmd)

### Relacionamento

<img src="https://i.imgur.com/sFPcOF4.png" width="500;"/>

---

### Tecnologias

- JWT
- Postgresql
- Springboot
- Docker

### Instalação


```shell

# Clone o repositório e acesse o diretório.

$ git clone git@github.com:Christianmsousa/organize-school-api.git && cd organize-school-api

# Baixe as dependencias 

$ mvn clean install

# Vá a pasta do docker e use o comando:

$ docker-composer up -d
```

### Inicialização

```
$ mvn spring-boot:run
```

### Postman
Clique [**aqui**](./postman/organize_collection.json) para acessar o aquivo `json` da coleção do postman.

> são definidas em: [**application.properties**](./src/main/resources/application.properties)
>
> ```shell
> # para mudar o valor de alguma variável de ambiente
> # na execução basta passar ela como parâmetro. (como --port=80 por exemplo).
> $ java -jar api-1.0.0.RELEASE.jar --port=80
> ```
>
> > [Todas opções do `aplication.properties` **padrões** no Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html).
> >
> > [Todas **funcionalidades** do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html).