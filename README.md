# Projeto de Teste

Este é um projeto Android que demonstra a arquitetura MVVM utilizando Kotlin.
Ele inclui várias bibliotecas e recursos para aumentar a eficiência e a manutenção do desenvolvimento.

## Recursos

- Arquitetura MVVM (Model-View-ViewModel)
- Linguagem: Kotlin
- Parser JSON: Moshi
- Carregador de Imagens: Glide
- Testes Unitários: Mockk
- LiveData com ViewModel
- Gerenciamento de Threads: Coroutines
- Projeto com Múltiplos Módulos
- Chamadas de API: Retrofit e OkHttp

## Módulos

O projeto está dividido em vários módulos para manter uma estrutura modular e separação de responsabilidades. Os principais módulos são:

- `app`: O módulo principal do aplicativo Android que contém os componentes de interface do usuário e a lógica específica do aplicativo.
- `shared`: O módulo de componentes compartilhados que contém componentes reutilizáveis e lógica comum.
- `github-user`: O módulo do projeto de teste real que demonstra a funcionalidade específica relacionada à obtenção de dados de usuários do GitHub. Observe que nem todos os testes foram implementados, mas existe um exemplo demonstrando como eles seriam feitos.

## Configuração

Para executar o projeto localmente, siga estas etapas:

1. Clone o repositório: `git clone https://github.com/maiconhellmann/github-user`
2. Abra o projeto no Android Studio.
3. Compile e execute o módulo `app` em um emulador ou dispositivo físico.

Certifique-se de ter a versão mais recente do Android Studio e os SDKs necessários instalados.

## Dependências

O projeto utiliza as seguintes dependências:

- [Moshi](https://github.com/square/moshi): Uma biblioteca moderna de análise e serialização JSON para Kotlin e Java.
- [Glide](https://github.com/bumptech/glide): Uma biblioteca de carregamento e cache de imagens para Android.
- [Mockk](https://github.com/mockk/mockk): Uma biblioteca de simulação para testes unitários em Kotlin.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines): Uma biblioteca para programação assíncrona utilizando coroutines.
- [Retrofit](https://github.com/square/retrofit) e [OkHttp](https://github.com/square/okhttp): Bibliotecas para fazer chamadas de API e gerenciar conexões de rede.
- [Shimmer by Facebook](https://github.com/facebook/shimmer-android): Uma biblioteca para adicionar efeitos de shimmer (brilho) em views.
- [Material Design by Google](https://material.io): Um conjunto de componentes e diretrizes de design fornecidos pelo Google.
- [CircleImageView by hdodenhof](https://github.com/hdodenhof/CircleImageView): Uma biblioteca para exibir imagens em formato circular.


## Licença

Este projeto está licenciado sob a [Licença MIT](
