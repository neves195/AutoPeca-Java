# 🔧 AutoPeça - Sistema de Cadastro de Peças

> Sistema desktop para cadastro e gerenciamento de peças automotivas, desenvolvido em Java com interface gráfica Swing e integração com banco de dados.

---

## 📋 Sobre o projeto / About

**🇧🇷** Aplicação desktop desenvolvida como projeto acadêmico, com foco em operações de cadastro, listagem e gerenciamento de peças automotivas. A interface foi construída inteiramente em **Java Swing**, sem dependências de frameworks front-end.

**🇺🇸** Desktop application developed as an academic project, focused on registration, listing and management of automotive parts. The UI was built entirely in **Java Swing**, with no front-end framework dependencies.

---

## 🛠️ Tecnologias / Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Java Swing](https://img.shields.io/badge/Java_Swing-GUI-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Connector-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

---

## 📁 Estrutura do projeto / Project structure

```
AutoPeca-Java/
├── Main.java                # Ponto de entrada da aplicação
├── Conexao.java             # Configuração e conexão com o banco de dados
├── AcessoBancoDados.java    # Operações com o banco (CRUD)
├── Peca.java                # Modelo/entidade da peça
├── FormularioPecas.java     # Interface gráfica (Swing)
└── .gitignore
```

---

## ⚙️ Funcionalidades / Features

- ✅ Cadastro de peças automotivas
- ✅ Listagem de peças registradas
- ✅ Conexão com banco de dados via JDBC
- ✅ Interface gráfica desktop com Java Swing

---

## 🚀 Como rodar localmente / How to run

### Pré-requisitos / Prerequisites
- Java JDK 17+
- MySQL rodando localmente
- IDE de sua preferência (VS Code, IntelliJ, Eclipse)

### Passos / Steps

```bash
# Clone o repositório
git clone https://github.com/neves195/AutoPeca-Java.git

# Abra o projeto na sua IDE

# Configure as credenciais do banco em Conexao.java
# Configure your database credentials in Conexao.java

# Execute Main.java
```

---

## 👨‍💻 Autor / Author

**João Pedro das Neves**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/joão-pedro-das-neves-2b30b1225)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/neves195)

---

> 📌 Projeto acadêmico desenvolvido durante o curso de Sistemas de Informação — ITE Bauru.
