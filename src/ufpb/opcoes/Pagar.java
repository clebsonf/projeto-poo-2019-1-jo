package ufpb.opcoes;

import ufpb.jogo.JogoFacade;

/**
 * <p>
 * Represents when the player has to pay a certain value.
 * </p>
 *
 */
public class Pagar implements Opcao {

	/**
	 * <p>
	 * The player pays a value and the match continues.
	 * </p> 
	 * @param jogo JogoFacade
	 * @return boolean
	 */
	@Override
	public boolean fazerOP(JogoFacade jogo) {
		jogo.JogadorAtual();
		jogo.JogadorAtual().pagar(50, jogo);
		int novoDado = jogo.lancaDados();
		int novoDado2 = jogo.lancaDados();
		jogo.JogadorAtual().jogada(novoDado, novoDado2, jogo);
		jogo.getPosicaoAtual().evento(jogo);
		return true;
	}

}
