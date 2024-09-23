# Ilustração de Algoritmo de Busca em Grafo 
## Sobre
<div style="text-align: justify;">
Este projeto consiste em um visualizador de grafos em Java utilizando o padrão MVC (Model-View-Controller), sendo capaz de ilustrar um algoritmo de busca em profundidade a partir de uma estrutura de dados que representa um grafo. Além disso, a aplicação é capaz de mostrar as iterações e os estados do grafo em cada uma delas, desde as suas distâncias até colorações.Em relaçao ao input, o programa é capaz criar um grafo aleatório com n vértices (com 5 <= n <= 25) e o usuário pode, também, cadastrar um grafo inserindo informações sobre seus vértices, arestas e, se for o caso, pesos.
</div>

# Informações Gerais
## Estrutura MVC
### Model (Modelo)
O modelo consiste nas classes que representam os componentes fundamentais do grafo, incluindo nós e arestas. As principais classes são:

- **`Grafo.java`**: Representa a estrutura do grafo, contendo métodos para adicionar vértices e arestas, além de implementar a lógica de busca em profundidade.

#### Atributos:
- `map`: um `Map` que associa cada vértice a uma lista de seus vizinhos.
- `arestas`: uma lista de todas as arestas do grafo.

#### Métodos:
- `adicionarVertice(Vertice vertice)`: adiciona um vértice ao grafo.
- `adicionarAresta(Vertice vertice1, Vertice vertice2)`: adiciona uma aresta entre dois vértices.
- `temAresta(Vertice vertice1, Vertice vertice2)`: verifica se existe uma aresta entre dois vértices.
- `vizinhos(Vertice vertice)`: retorna uma lista de vizinhos do vértice especificado.

### View (Visão)
A camada de visão é responsável pela interface do usuário e pela ilustração do grafo. As classes nesta camada lidam com a entrada do usuário e a visualização das operações realizadas.

#### Principais Classes e Funcionalidades:
- **`MainFrame.java`**: Cria a janela principal do aplicativo, onde o grafo é visualizado. Gerencia menus e exibe informações ao usuário.
- **`Vertice.java`**: Representa um vértice no grafo e cuida da sua renderização gráfica.
- **`Aresta.java`**: Representa uma aresta entre dois vértices e cuida da sua visualização.
- **`Modo.java`**: Enum para definir os modos de operação do aplicativo, como adicionar ou remover vértices e arestas.

### Controller (Controlador)
O controlador atua como intermediário entre o modelo e a visão, gerenciando a lógica de fluxo do programa e a interação entre as duas camadas.

#### Atributos:
- `grafo`: uma instância da classe `Grafo`.
- `view`: uma instância da classe `GrafoView`.

#### Métodos:
- `iniciar()`: inicializa o programa e exibe o menu principal.
- `gerar_grafo_aleatorio(n)`: gera um grafo aleatório e atualiza a visualização.
- `cadastrar_grafo()`: processa a entrada do usuário para criar um grafo manualmente.
- `executar_busca()`: executa o algoritmo de busca e atualiza a visualização.

## Fluxo do Projeto

### Início do Programa
O programa inicia e a classe `Main` cria uma nova instância de `MainFrame`.

### Exibição da Interface
- A janela principal é exibida, com um menu para interações.  
- O modo atual (Adicionar Vértice, Adicionar Aresta, etc.) é mostrado na interface.

### Seleção de Modo
- O usuário seleciona um modo através do menu (ex: Adicionar Vértice).  
- O modo é atualizado e refletido na interface.

### Entrada de Dados
- **Adicionar Vértice**: O usuário clica na área de desenho para adicionar um novo vértice.
- **Adicionar Aresta**: O usuário seleciona dois vértices para conectar.
- **Remover Vértice/Aresta**: O usuário clica em um vértice ou aresta existente para removê-los.

### Atualização do Modelo
As ações do usuário chamam métodos na classe `Grafo`:
- `adicionarVertice(Vertice vertice)`
- `adicionarAresta(Vertice vertice1, Vertice vertice2)`
- `removerVertice(Vertice vertice)`
- `removerAresta(Aresta aresta)`

### Visualização do Grafo
- O grafo atualizado é redesenhado na interface.  
- A visualização inclui os vértices e arestas.

### Execução de Algoritmo de Busca
- O usuário escolhe o algoritmo através do menu.  
- O controlador chama o método correspondente no modelo (`executar_busca()`).  
- O algoritmo é executado, e as iterações são mostradas na interface, destacando as distâncias e colorações (se aplicável).

### Geração de Grafo Aleatório
- O usuário pode optar por gerar um grafo aleatório com um número definido de vértices (5 <= n <= 25).  
- O controlador chama `gerar_grafo_aleatorio(n)` no modelo.  
- O grafo gerado é visualizado automaticamente.

### Finalização
- O usuário pode fechar o programa a qualquer momento, encerrando a aplicação.

