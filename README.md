# Ball News

#### Aplicação Futebolística em Kotlin

##### Engenharia e Desenvolvimento de Jogos Digitais - Desenvolvimento de Jogos para Plataformas Móveis
###### Gabriel Rosas nº27943 / João Reis nº27917 

# __Indíce__
1. [__Introdução__](#Introdução)
2. [__Estrutura do Projeto__](#Estrutura)
   * [__Organização das Pastas__](#Pastas)
3. [__Funcionalidades__](#Funcionalidades)
4. [__Modelo de Dados__](#ModelodeDados)
5. [__Implementação__](#Implementação)
6. [__Tecnologias__](#Tecnologias)
7. [__Dificuldades__](#Dificuldades)
8. [__Conclusão__](#Conclusão)

<a name="Introdução"></a>
# __Introdução__

Este trabalho tem como objetivo principal o desenvolvimento de uma aplicação em kotlin, através do Android Studio. A ideia principal seria criar uma aplicação de futebol conectada diretamente a uma firebase e uma API de futebol. A firebase seria necessária para um sistema básico de login que permitiria os utilizadores criar uma conta na aplicação e acompanhar todos os jogos e clubes. A API seria responsável pelo fornecimento de todos os jogos, sendo eles em direto, já realizados ou por realizar. 


<p align="center">
 <img src="https://cdn.discordapp.com/attachments/1162876754462003200/1326366131048681544/image.png?ex=677f2a38&is=677dd8b8&hm=1e40bc97dde1de4f69c8ef4304158d2902b27dc348752c5b3a5a382778f76cd8&"  alt="Pong" width=200>
</p>

<a name="Estrutura"></a>
# __Estrutura do Projeto__

```plaintext
app
├── manifests
│   └── AndroidManifest.xml
├── kotlin+java
│   └── com.example.trabalhofinal
│       ├── models
│       │   ├── Match
│       │   └── User
│       ├── repositories
│       │   ├── MatchRepository
│       │   └── UserRepository
│       └── ui
│           └── theme
│               ├── login
│               │   ├── LoginView.kt
│               │   └── LoginViewModel.kt
│               ├── match
│               │   ├── SavedView.kt
│               │   └── SavedViewModel
│               ├── profile
│               │   ├── ProfileView.kt
│               │   └── ProfileViewModel.kt
│               ├── Color.kt
│               ├── MatchView.kt
│               ├── MatchViewModel
│               ├── Theme.kt
│               └── Type.kt
└── MainActivity.kt
```

<a name="Pastas"></a>
## __Estrutura das Pastas__

* __Models:__
  - **`Match`**: Modelo para representar uma partida de futebol
  - **`User`**: Modelo para representar informações do utilizador
* __Repositories:__
  - **`MatchRepository`**: Código responsável pelos dados das matches através da API
  - **`UserRepository`**: Código responsável pela ligação dos dados do utilizador à Firebase
* __UI:__
  * __Theme:__
    * __Login:__
      - **`LoginView.kt`**: Define a interface do login
      - **`LoginViewModel.kt`**: Define o processo de login e registo do utilizador através da Firebase
    * __Match:__
      - **`SavedView.kt`**: Define a interface das Matches guardadas
      - **`SavedViewModel.kt`**: Gerencia e carrega os jogos salvos na Firebase
    * __Profile:__
      - **`ProfileView.kt`**: Define a tela de perfil do utilizador, podendo alterar o nome e dar logout
      - **`ProfileViewModel.kt`**: Gere e interage diretamente com a Firebase para obter e salvar os dados do Utilizador
    - **`Color.kt`**: Define uma palete de cores para a UI da App
    - **`Theme.kt`**: Define um esquema de cores e um tema personalizado para a App
    - **`Type.kt`**: Código responsável pela tipografia dos textos da App
    - **`MatchView.kt`**: Gere e interage diretamente com a Firebase para obter e salvar os dados do Utilizador
    - **`MatchViewModel.kt`**: Gere e interage diretamente com a Firebase para obter e salvar os dados do Utilizador

<a name="Funcionalidades"></a>
# __Funcionalidades__
  - **`Login e Registo do Utilizador`**:
    * Permite o utilizador se registar e fazer login na App
  - **`Firebase`**:
    * Serve para guardar todo o tipo de dados numa base de dados online, sejam eles do utilizador ou relacionados com a App
  - **`Football-Data.org`**:
    * Responsável por fornecer dados oficiais de jogos de futebol, como, por exemplo, data, hora e clubes atráves de uma API
  - **`Profile`**:
    * O profile permite-nos gerir o perfil do utilizador bem como, a funcionalidade de logout
  - **`Matches`**:
    * Apresenta todos os jogos e dados fornecidos pela API e permite compartilhar os jogos
  - **`Favorite Matches`**:
    * Permite guardar os jogos com interesse noutra tela e se necessário removê-los


<a name="ModelodeDados"></a>
# __Modelo de Dados__

  - **`Match`**:
    * id (String)
    * name (String)
    * date (String)
    * league (String)
    * timestamp (Long) 

  - **`User`**:
    * name (String)
    * email (String) 
    * docId (String)

<a name="Implementaçãos"></a>
# __Implementação__

<a name="Tecnologias"></a>
# __Tecnologias__

  - **`Firebase`**:
    * Permite guardar dados numa base de dados online
  - **`API`**:
    * Responsável por fornecer dados de qaulquer de tópico sejam eles oficiais, ou falsos
  - **`MVVM`**:
    - **`Model`**: Dados e Lógica da App
    - **`View`**: Apresentam os dados ao utilizador e recebe o seu feedback
    - **`ViewModel`**: Contém a lógica de apresentação e é responsável por fornecer dados formatados para a View

<a name="Dificuldades"></a>
# __Dificuldades__

  - Configuração da Firebase para o registo do utilizador;
  - Configuração da API para a aplicação;
  - Conseguir ligar;
  - Aprendizagem do padrão MVVM e aplicaçaão pratica;
  - Dificuldade em evoluir a interface das telas da aplicaçao;

# __Conclusão:__
Em suma, o desenvolvimento desta aplicação de futebol foi desafiadora mas muito interessante de se elaborar, acho que este projeto evoluiu muito o meu conhecimente de kotlin e acho muito importante para esta disciplina e curso.
