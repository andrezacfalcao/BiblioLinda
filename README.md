# | Projeto BiblioLinda |

## Integrantes:
* Andreza Maria Coutinho Falcão - andreza.mcfalcao@ufrpe.br
* Beatriz Pereira da Silva - beatrizbusinessx@gmail.com
* Brenno Araújo Caldeira Silva - caldeirabrenno208@gmail.com
* Victor Schmitz Donato Cavalcanti - victor.schmitz@ufrpe.br

## Principal funcionalidade do sistema: 
É um sistema de gerenciamento de biblioteca que permite cadastrar e controlar o acervo de livros, realizar empréstimos e oferecer busca eficiente de livros para os usuários.

1. Quem vai usar o programa?
   - Bibliotecários: Responsáveis pelo cadastro e controle do acervo de livros, gerenciamento de empréstimos e relatórios.
   - Usuários da biblioteca: Podem utilizar a busca de livros, solicitar empréstimos e realizar consultas sobre suas pendências e histórico de leituras.

2. Que serviços são "necessários" (importantes para os clientes e usuários)?
   - Cadastro de livros: Permitir o registro das informações relevantes dos livros no acervo.
   - Empréstimos e devoluções: Possibilitar aos usuários solicitar empréstimos e aos bibliotecários registrar e controlar as devoluções.
   - Busca: Oferecer um mecanismo de busca de livros para facilitar o acesso dos usuários às obras desejadas.

3. Quais serviços cada usuário pode executar?
   - Usuário admnistrador:
     - Cadastrar, editar e remover informações dos livros no sistema.
     - Registrar empréstimos e devolução de livros.

   - Cliente:
     - Buscar livros por título, autor ou palavras-chave.
     - Solicitar empréstimos de livros.
     - Consultar pendências.
     
## Requisitos do projeto
* **REQ1** - O sistema vai controlar o acesso atráves de um login e senha de usuário do tipo administrador ou clientes.
* **REQ2** - Apenas o usuário admnistrador poderá consultar a lista com todos os empréstimos pendentes.
* **REQ3** - Os clientes só poderão fazer o empréstimo de livros disponiveis.
* **REQ4** - A multa terá valor fixo por semana, gerado automaticamente após o atraso.
* **REQ5** - O cliente poderá fazer o empréstimo apenas de um livro por vez.
* **REQ6** - O sistema deve permitir o gerenciamento (CRUD) de usuários, empréstimos, quitação de multa e essa ação deve ser feita pelo usuário admnistador.
* **REQ7** - O cliente só poderá alugar um livro se não tiver nenhuma pendência.
* **REQ8** - O usuário administrador poderá acessar o histórico de pagamentos e consultar um subtotal de apurados de multas em dado período de tempo.
