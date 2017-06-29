# Ballz Clone

Ballz é um jogo estilo breakout, onde deve-se utilizar uma bolinha para destruir inúmeras caixas. Porém, apesar de ser fortemente baseado nesse estilo de jogo, há algumas diferenças cruciais que o diferem do famoso predecessor:

### As caixas avançam a cada rodada. 

As caixas são dispostas em um grid, e caso não sejam destruídas se aproximam do chão ao final de cada rodada. O jogo é perdido quando qualquer caixa alcançar além do último nível (mais próximo do chão). 

### O jogador é responsável pela trajetória das bolinhas.

No início de cada rodada, o jogador deve escolher a trajetória que as bolinhas irão tomar. Após liberadas, elas obedecerão simples regras da física de colisão e restituição para que rebatam nas paredes e outras caixas até eventualmente voltarem para a base.

### As caixas não quebram imediatamente após colisão. 

Diferentemente do que acontece em 'Breakout' o contato com as bolas não é condição suficiente para que ela seja destruída. Ao invéz disso, cada caixa é numerada e seu valor representa o número de colisões que ela deve receber antes de ser removida do jogo.

### O número de bolas aumenta.

Um dos únicos itens que podem ser adquiridos durante o jogo, são bolas adicionais. Esse é um item cumulativo extremamente importante, pois conforme o valor das caixas aumenta é necessário um maior "poder de fogo" para destruí-las (mais colisões).

## Telas:

Nem todas as telas serão implementadas nesse projeto. Abaixo, têm-se a lista de telas agrupadas por responsabilidade. Ao lado de seus nomes, o 'X' significa que não terá implementação e o '#' indica que a funcionalidade será implementada

### # Splash Screen
Tela de apresentação e carregamento de Assets do Jogo. A intenção é utilizar apenas primitivas na construção desse jogo, portanto, talvez seja apenas um tela de transição suave.

	- Essa é a tela de abertura do jogo. Permite acesso a todas as outras telas dispovível para o usuário:
	Tela principal:

	- Botão Jogar
	X Botão de Avaliação do Jogo
	
	X Music (Turn ON/OFF)
	- High Scores
	X Market
	
Tela de Jogo:
	Essa tela conterá além dos elementos gráficos que compõe o jogo, também terá alguns elementos de UI:
		- Personal Best Score
		- Current Score
		X Number of total coins
	O restante da tela será controlada pela engine de jogo.
