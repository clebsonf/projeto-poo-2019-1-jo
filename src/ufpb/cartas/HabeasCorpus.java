package ufpb.cartas;

import ufpb.jogo.JogoFacade;

/**
 * <p>
 * This class is a subclass of LuckyOrReves and therefore inherits its
 * attributes from the card's description and position in the Lucky or Setback
 * queue.
 * </p>
 * <p>
 * In addition, its behavior is also inherited, therefore is required to
 * implement the acao method.
 * </p>
 */
public class HabeasCorpus extends SorteOuReves {

	/**
	 * <p>
	 * Constructor method of class HabeasCorpus.
	 * </p>
	 * 
	 * @param description - A String with representing the description of the action
	 *                    taken by the lucky card or setback - type String
	 * @param posision    - A String with representing the position in the luck or
	 *                    setback queue.
	 */
	public HabeasCorpus(String descricao, String valor) {
		super(descricao, valor);
	}

	/**
	 * <p>
	 * This method implement the method acao, therefore it must realize a action
	 * with base in your type and description.
	 * </p>
	 * <p>
	 * Habeas Corpus card is added as an attribute of the Jogador class and is
	 * prints the descrption of card.
	 * </p>
	 * 
	 * @param jogo - JogoFacade
	 */
	@Override
	public void acao(JogoFacade jogo) {
		jogo.JogadorAtual().addCarta();
		System.out.println(this.descricao);
	}

}
