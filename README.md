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

├── src/

│ ├── model/

│ │ ├── Livro.java

│ │ ├── Pedido.java

│ │ ├── Usuario.java

│ │ ├── Admin.java

│ │ ├── Cliente.java

│ │ ├── Pagamento.java

│ │ ├── Devolucao.java

│ │ └── EnderecoEntrega.java

│ ├── repository/

│ │ ├── AbstractRepositorio.java

│ │ ├── LivroRepositorio.java

│ │ ├── PedidoRepositorio.java

│ │ └── UsuarioRepositorio.java

│ ├── service/

│ │ ├── LivroService.java

│ │ ├── PedidoService.java

│ │ └── UsuarioService.java

│ └── programa/

│ └── Programa.java

└── README.md
