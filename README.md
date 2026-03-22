# ♟️ Chess Engine Java

Um jogo de xadrez completo desenvolvido em Java, com suporte a partidas **Jogador vs Jogador** e **Jogador vs IA**, utilizando o algoritmo **Minimax** para tomada de decisões da IA.

## Funcionalidades

- Tabuleiro 8x8 com todas as peças do xadrez
- Movimentos válidos para todas as peças
- Movimentos especiais: **Roque** e **En Passant**
- Detecção de **Xeque** e **Xeque-Mate**
- Modo **Jogador vs Jogador**
- Modo **Jogador vs IA** com 3 níveis de dificuldade
- IA baseada no algoritmo **Minimax**
- Detecção automática de símbolos Unicode

## Tecnologias

- Java 17
- Algoritmo Minimax
- Programação Orientada a Objetos

## Como rodar

### Pré-requisitos
- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Git](https://git-scm.com/downloads)

### Passo a passo
```bash
# Clone o repositório
git clone https://github.com/dan-august0/Chess-Engine-Java.git

# Entre na pasta
cd Chess-Engine-Java

# Crie a pasta de compilação
mkdir bin

# Compile
javac -encoding U#TF-8 -d bin src/chess/*.java src/chess/pieces/*.java src/chess/board/*.java src/chess/engine/*.java

#Rodar com as peças 
chcp 65001

# Rode
java -cp bin chess.Main
```

## Como jogar

- Digite o movimento no formato `e2 e4` (origem destino)
- Para **roque pequeno**: `0-0`
- Para **roque grande**: `0-0-0`
- Para **sair**: `sair`

## Estrutura do projeto
```
src/chess/
├── Main.java
├── Color.java
├── Position.java
├── TerminalUtils.java
├── board/
│   └── Board.java
├── pieces/
│   ├── Piece.java
│   ├── Pawn.java
│   ├── King.java
│   ├── Queen.java
│   ├── Rook.java
│   ├── Bishop.java
│   └── Knight.java
└── engine/
    ├── GameLoop.java
    └── AI.java
```

## Sobre o algoritmo Minimax

O Minimax é um algoritmo de busca usado em jogos de dois jogadores. Ele simula jogadas futuras, assumindo que o oponente sempre jogará a melhor jogada possível, e escolhe o movimento que maximiza o ganho da IA.

Cada peça tem um valor:
| Peça | Valor |
|------|-------|
| Peão | 1 |
| Cavalo | 3 |
| Bispo | 3 |
| Torre | 5 |
| Rainha | 9 |
| Rei | 1000 |

## Roadmap

- [x] Tabuleiro e peças
- [x] Movimentos válidos
- [x] Roque e En Passant
- [x] Detecção de Xeque e Xeque-Mate
- [x] IA com Minimax
- [x] Seletor de dificuldade
- [ ] Alpha-Beta Pruning
- [ ] Interface gráfica
- [ ] Multiplayer em rede

## 👤 Autor

**Danillo** — [@dan-august0](https://github.com/dan-august0)
