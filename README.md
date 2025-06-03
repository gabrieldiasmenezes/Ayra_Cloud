# ☁️ Cloud - Projeto Ayra

Este repositório contém a parte de **Cloud Computing** do projeto Ayra, uma solução para prevenção de desastres naturais, utilizando tecnologias em nuvem para garantir escalabilidade, segurança e disponibilidade.

---

## 📌 Objetivos

- Disponibilizar a aplicação de forma acessível e escalável.
- Utilizar práticas modernas de DevOps e Cloud para automação e entrega contínua.
- Integrar com banco de dados em nuvem e expor endpoints via API.
- Configurar variáveis sensíveis com segurança usando ferramentas adequadas.

---

## 🧰 Tecnologias e Ferramentas Utilizadas

- **Spring Boot** – Backend da aplicação
- **PostgreSQL** – Banco de dados relacional
- **Docker ** – Containerização da aplicação
- **Insomnia** – Testes dos endpoints em ambiente remoto

---

## 🚀 Como rodar

1. Clone o repositório:

```bash
git clone https://github.com/gabrieldiasmenezes/Ayra_Api.git
cd Ayra_Api
```

2. Gere e rode os containers de banco de dados e da aplicação Java utilizando o `docker-compose.yml`:

```bash
docker compose up --build
```

Esse comando irá:

- Criar a imagem e o container do banco de dados PostgreSQL.
- Criar a imagem e o container da aplicação Spring Boot (Java).
- Executar automaticamente os scripts de seed (`DatabaseSeeder`) com dados fictícios para testes.
- Inicializar a aplicação e a persistência de dados integrada via Docker.

Após a inicialização, você verá no terminal logs indicando os inserts no banco e a aplicação rodando na porta `8080`.

Teste os endpoints (ex: login, cadastro, update, delete) com ferramentas como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/), utilizando a URL base:

```
http://localhost:8080
```

---

## 🔐 Segurança

- Variáveis sensíveis como credenciais do banco de dados foram armazenadas usando o painel de *Secrets* da plataforma de deploy.
- Nenhuma credencial está exposta no repositório.
- O acesso à aplicação pode ser protegido via autenticação JWT (dependendo da versão do projeto).

---

## 🗃️ Banco de Dados

O banco está hospedado remotamente (Railway, Render ou outra plataforma). A conexão é feita via `application.properties`/`application.yml` com URL, usuário e senha armazenados em variáveis de ambiente.

---

## ⚙️ Pipeline de Deploy

Sempre que um novo `commit` é enviado para a branch principal, um pipeline é executado automaticamente:

1. Compilação da aplicação
2. Testes (se aplicável)
3. Deploy automático no serviço em nuvem

---

## 🧪 Testes e Validação

- Todos os endpoints foram testados via Postman após o deploy.
- Erros comuns como CORS, autenticação ou erro 500 foram tratados no ambiente de produção.
- O vídeo de demonstração mostra a aplicação em funcionamento real na nuvem.

---

## 📽️ Vídeo de Demonstração

O vídeo demonstrando a etapa de cloud, com deploy e testes funcionais, pode ser acessado neste link:

📹 [Link para o vídeo]([https://www.youtube.com/watch?v=...](https://youtu.be/_AmXy9mNUls)) 

---

## ✅ Conclusão

A etapa de Cloud foi concluída com sucesso, com a aplicação Ayra funcionando de forma online, segura e acessível. Todo o processo de deploy foi documentado, e a aplicação está pronta para escalar e receber melhorias.




