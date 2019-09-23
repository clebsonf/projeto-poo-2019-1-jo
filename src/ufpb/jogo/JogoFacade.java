package ufpb.jogo;

import java.util.LinkedList;
import java.util.Scanner;

import ufpb.cartas.SorteOuReves;
import ufpb.exceptions.CorValidaException;
import ufpb.exceptions.ExisteJogadorComEstaCorException;
import ufpb.exceptions.ValorInvalidoException;
import ufpb.lougradouros.Posicao;

/**
 * <p>
 * Represents the facade of the games.
 * </p>
 *
 */
public class JogoFacade {

	protected static final Scanner in = new Scanner(System.in);
	protected LinkedList<Jogador> listaJogadores;
	protected Dado dado = new Dado();
	protected Tabuleiro tabuleiro = new Tabuleiro();
	private JogoFactory fabrica;
	private int[] ultimosDados;

	/**
	 * <p>
	 * Shows the facade of the game to the players.
	 * </p>
	 * 
	 * @param
	 */
	public JogoFacade() {
		listaJogadores = new LinkedList<Jogador>();
		ultimosDados = new int[2];
	}

	/**
	 * <p>
	 * Convert to String.
	 * </p>
	 * 
	 * @param
	 */
	public String input() {
		return in.nextLine();
	}

	/**
	 * <p>
	 * Convert to integer.
	 * </p>
	 * 
	 * @param
	 */
	public int inputInt() {
		return Integer.parseInt(in.nextLine());
	}

	/**
	 * <p>
	 * Add a new player.
	 * </p>
	 * 
	 * @param
	 */
	public void addJogador(Jogador e) {
		this.listaJogadores.add(e);
	}

	/**
	 * <p>
	 * Add the player who is at the beginning of the queue to the end.
	 * </p>
	 * 
	 * @param
	 */
	public void pollJogador() {
		this.listaJogadores.add(this.listaJogadores.pollFirst());
	}

	/**
	 * <p>
	 * Removes the player.
	 * </p>
	 * 
	 * @param
	 */
	public void removeJogador() {
		this.listaJogadores.remove(this.JogadorAtual());
	}

	/**
	 * <p>
	 * Throws the dices.
	 * </p>
	 * 
	 * @param
	 */
	public int lancaDados() {
		return (this.dado.lancaDado());
	}

	/**
	 * <p>
	 * Verifies if the player is in prison.
	 * </p>
	 * 
	 * @param
	 */
	public boolean verificarSeTaNaPrisao() {
		return this.tabuleiro.getPosicoeDoTabuleiro(this.JogadorAtual().getPosicao()).getTipo().equals("Prisão");
	}

	/**
	 * <p>
	 * This method checks if there is any other player using the color passed as a
	 * parameter.
	 * </p>
	 * 
	 * @param cor
	 * @throws ExisteJogadorComEstaCorException
	 */

	public void verificaSeExisteJogadorComEstaCor(String cor) throws ExisteJogadorComEstaCorException {
		for (Jogador j : this.listaJogadores) {
			if (j.getCor().equals(cor)) {
				throw new ExisteJogadorComEstaCorException(
						"Já existe um jogador utilizando esta cor. Tente novamente!");
			}
		}
	}

	/**
	 * <p>
	 * Verifies if the chosen color is still available. 
	 * </p>
	 * 
	 * @param String cor
	 * @throws CorValidaException
	 * @return true if the color passed as a parameter is within expected colors
	 */
	public boolean verificaSeAhCorEhValida(String cor) throws CorValidaException {
		if (cor.equalsIgnoreCase("preto") || cor.equalsIgnoreCase("branco") || cor.equalsIgnoreCase("vermelho")
				|| cor.equals("verde") || cor.equalsIgnoreCase("azul") || cor.equalsIgnoreCase("amarelo")
				|| cor.equalsIgnoreCase("laranja") || cor.equals("rosa")) {
			return true;
		}
		throw new CorValidaException("Esta cor não é válida. Tente novamente uma cor disponível!");
	}

	/**
	 * <p>
	 * The current player.
	 * </p>
	 * 
	 * @param
	 */
	public Jogador JogadorAtual() {
		return this.listaJogadores.getFirst();
	}

	/**
	 * <p>
	 * </p>
	 * 
	 * @param
	 */
	public void chamarEvento() {
		this.tabuleiro.getPosicoeDoTabuleiro(this.JogadorAtual().getPosicao()).evento(this);

	}

	/**
	 * <p>
	 * Verifies if the number of player in the macth is valid.
	 * It must be above 2 and up to 8 players.
	 * </p>
	 * 
	 * @param
	 */
	public void verificaNumeroJogadores(int numero) throws ValorInvalidoException {
		if ((numero > 8 || numero < 2)) {
			throw new ValorInvalidoException("Número de jogadores inválidos!\n");
		}
	}

	/**
	 * <p>
	 * Pass the turn.
	 * </p>
	 * 
	 * @param
	 */
	public void passarAVez() {
		this.listaJogadores.add(this.listaJogadores.pollFirst());
	}

	/**
	 * <p>
	 * Shows the option "yes or no" in certains situations. 
	 * </p>
	 * 
	 * @param @return @throws
	 */
	public boolean simOuNao(String msg) throws ValorInvalidoException {
		System.out.print(msg + "\nSim/Não");
		String escolha = this.input();
		if (escolha.matches("[Ss]+[Ii]*[Mm]*")) {
			return true;
		} else if (!(escolha.matches("[Nn]+[ÃãAa]*[Oo]*"))) {
			throw new ValorInvalidoException("Opção não permitida");
		}
		return false;
	}

	public SorteOuReves getCarta() {
		return this.tabuleiro.getSorteOuReves();
	}

	public int getNumeroDeJogadores() {
		return this.listaJogadores.size();
	}

	public Posicao getPosicaoAtual() {
		return this.tabuleiro.getPosicoeDoTabuleiro(this.JogadorAtual().getPosicao());
	}

	public void setPrisao() {
		this.fabrica = new JogoFactoryPrisao();
	}

	public JogoFactory getFabrica() {
		return this.fabrica;
	}

	public void setFabrica() {
		this.fabrica = new JogoFactory();
	}

	public int[] getUltimosDados() {
		return ultimosDados;
	}

	public void setUltimosDados(int[] ultimosDados) {
		this.ultimosDados = ultimosDados;
	}

}
