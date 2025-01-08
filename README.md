# Ball News

#### Aplicação Futebolística em Kotlin

##### Engenharia e Desenvolvimento de Jogos Digitais - Desenvolvimento de Jogos para Plataformas Móveis
###### Gabriel Rosas nº27943 / João Reis nº27917 

# __Indíce__
1. [__Introdução__](#Introdução)
2. [__Estrutura do Projeto__](#Estrutura)
   * [__Organização das Pastas__](#Pastas)
4. [__Funcionalidades__](#Funcionalidades)
5. [__Modelo de Dados__](#ModelodeDados)
6. [__Implementação__](#Implementação)
7. [__Tecnologias__](#Tecnologias)
8. [__Dificuldades__](#Dificuldades)
9. [__Conclusão__](#Conclusão)

# __Introdução__

Este trabalho tem como objetivo principal o desenvolvimento de uma aplicação em kotlin, através do Android Studio. A ideia principal seria criar uma aplicação de futebol conectada diretamente a uma firebase e uma API de futebol. A firebase seria necessária para um sistema básico de login que permitiria os utilizadores criar uma conta na aplicação e acompanhar todos os jogos e clubes. A API seria responsável pelo fornecimento de todos os jogos, sendo eles em direto, já realizados ou por realizar. 

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


     


# __Funcionalidades__



<a name="ModelodeDados"></a>
# __Modelo de Dados__
# __Implementação__
# __Tecnologias__
# __Dificuldades__


# __Conclusão:__
Em suma, o desenvolvimento desta aplicação de futebol foi essencial para
