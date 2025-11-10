# 📚 Projeto Livraria 

![Java](https://img.shields.io/badge/Java-22+-orange?logo=openjdk)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)
![License](https://img.shields.io/badge/Licença-MIT-blue)
![Contribuições](https://img.shields.io/badge/Contribuições-Bem--vindas-success)

---

## 🏪 Sobre o Projeto

O **Projeto Livraria** é um sistema desenvolvido em **Java** pelos alunos de Introdução à Programação 2, que simula o funcionamento de uma livraria digital.  
Seu objetivo é aplicar **conceitos avançados de Programação Orientada a Objetos (POO)**, como **herança**, **abstração**, **encapsulamento**, **polimorfismo** e **repositórios genéricos**.

O sistema foi estruturado com foco em modularidade e clareza, dividindo responsabilidades entre as camadas de **modelo**, **repositório**, **serviço** e **execução**.

---

## 🧩 Funcionalidades Principais

✅ Cadastro, edição e listagem de livros  
✅ Gerenciamento de usuários (Clientes e Administradores)  
✅ Criação e controle de pedidos  
✅ Registro de pagamentos e devoluções  
✅ Sistema de notificações e rastreamento de entrega  
✅ Camada de abstração para repositórios genéricos  

---

## 🧠 Conceitos e Padrões Utilizados

- **Abstração e Encapsulamento** — uso de classes `abstract` e atributos `protected`  
- **Herança** — especialização de `Usuario` em `Admin` e `Cliente`  
- **Polimorfismo** — sobrescrita e uso genérico de métodos  
- **Padrão Repository** — centraliza operações CRUD de forma genérica  
- **Responsabilidade Única (SRP)** — separação clara de camadas e papéis  

---

## 🗂️ Estrutura do Projeto


Projeto-Livraria/
└── src/
    ├── cadastro/
    │   ├── produto/
    │   │   ├── CadastroAvaliacao.java
    │   │   ├── CadastroCarrinho.java
    │   │   ├── CadastroCupomPromocional.java
    │   │   ├── CadastroDevolucao.java
    │   │   ├── CadastroLivro.java
    │   │   ├── CadastroNotificacao.java
    │   │   ├── CadastroPagamento.java
    │   │   ├── CadastroPedido.java
    │   │   └── CadastroWishList.java
    │   └── usuario/
    │       ├── CadastroCliente.java
    │       └── CadastroUsuario.java
    │
    ├── excecoes/
    │   ├── AutenticacaoFalhaExcecao.java
    │   ├── CarrinhoVazioExcecao.java
    │   ├── CupomExpiradoExcecao.java
    │   ├── CupomInvalidoExcecao.java
    │   ├── CupomJaUtilizadoExcecao.java
    │   ├── EmailInvalidoExcecao.java
    │   ├── EntidadeJaExistenteExcecao.java
    │   ├── EntidadeNaoEncontradaExcecao.java
    │   ├── EstoqueInsuficienteExcecao.java
    │   ├── EstoqueInvalidoExcecao.java
    │   ├── OperacaoNaoPermitidaExcecao.java
    │   ├── PrecoInvalidoExcecao.java
    │   ├── QuantidadeInvalidaExcecao.java
    │   └── StatusPedidoInvalidoExcecao.java
    │
    ├── main/
    │   ├── Fachada.java
    │   └── Programa.java
    │
    ├── model/
    │   ├── Avaliacao.java
    │   ├── Carrinho.java
    │   ├── Cliente.java
    │   ├── CupomPromocional.java
    │   ├── Devolucao.java
    │   ├── Endereco.java
    │   ├── ItemPedido.java
    │   ├── Livro.java
    │   ├── Notificacao.java
    │   ├── Pagamento.java
    │   ├── Pedido.java
    │   ├── RelatorioVendas.java
    │   ├── Usuario.java
    │   └── WishList.java
    │
    └── repositorio/
        ├── AvaliacaoRepositorioArray.java
        ├── CarrinhoRepositorioArray.java
        ├── ClienteRepositorioArray.java
        ├── CupomPromocionalRepositorioArray.java
        ├── DevolucaoRepositorioArray.java
        ├── LivroRepositorioArray.java
        ├── NotificacaoRepositorioArray.java
        ├── PagamentoRepositorioArray.java
        ├── PedidoRepositorioArray.java
        ├── UsuarioRepositorioArray.java
        └── WishListRepositorioArray.java


└── README.md
---

## ⚙️ Como Executar o Projeto


    Clone o repositório:
    git clone https://github.com/lucifer-son/Projeto-Livraria.git

    Abra o projeto na IDE de sua preferência:
    IntelliJ IDEA
    Eclipse
    VS Code com extensão Java

    Execute a classe principal:
    src/programa/Programa.java

    Saída esperada:
    --- Iniciando Testes do Sistema da Livraria ---

---

# 👨‍💻 Autores

📍 Desenvolvido como projeto acadêmico para estudo de POO em Java.

Rafael Melo
🌐 GitHub - lucifer-son

Mariana Oliveira
🌐 GitHub - Mari-olima

João Lucas
🌐 GitHub - joaoluc90

Nickollas Vital
🌐 GitHub - Nickollasg1 

---

## 🧭 Diagrama de Classes Simplificado

```mermaid
classDiagram
    class Usuario {
        - id : String
        - nome : String
        - email : String
        - senha : String
        + getRoles() : List<String>
    }

    class Admin {
        + Admin(id, nome, email, senha)
    }

    class Cliente {
        + Cliente(id, nome, email, senha)
    }

    Usuario <|-- Admin
    Usuario <|-- Cliente

    class Livro {
        - id : int
        - titulo : String
        - autor : String
        - preco : double
        - estoque : int
    }

    class Pedido {
        - id : int
        - cliente : Cliente
        - data : Date
        - itens : List<ItemPedido>
        - valorTotal : double
        - status : StatusPedido
    }

    class Pagamento {
        - metodo : String
        - valor : double
        - status : String
    }

    class LivroRepositorio
    class PedidoRepositorio
    class UsuarioRepositorio


    Pedido --> Pagamento
    Pedido --> Livro
    Pedido --> Cliente





