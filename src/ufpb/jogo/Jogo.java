package ufpb.jogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Jogo {
	private static final Scanner input = new Scanner(System.in);
	private int numeroDeJogadores;
	private LinkedList<Jogador> listaJogadores;
	private Dado dado = new Dado();
	
	
	//private ListJogadores jogadores;
	
	
	
	public Jogo() {
		//jogadores = new ListJogadores();
		listaJogadores = new LinkedList<Jogador>();
	}
	
	public void iniciarJogo() {
		nJogadores();
		criarJogadores();
		System.out.println("O Banco Imobiliario vai comecar. Aproveite!");
		partida();

	}
	
	private void nJogadores() {
		System.out.println("Digite o numero de jogadores [2 - 8]: ");
		int numero = Integer.parseInt(input.nextLine());
		if(numero > 8 || numero < 2) {
			nJogadores();
		}else {
			this.numeroDeJogadores = numero;	
		}
	}
	
	private void criarJogadores() {
		int cont = 0;
		while(cont < numeroDeJogadores) {
			System.out.println("Digite o nome do jogador"+(cont+1)+":");
			String nome = input.nextLine();
			System.out.println("Digite a cor:");
			String cor = input.nextLine();			
			this.listaJogadores.add(new Jogador(nome,cor));
			cont += 1;
		}
	}
	
	private void opcoes(Jogador j){
		System.out.println("A jogada de "+j.getNome()+"("+j.getCor()+") começou:");
		System.out.println("Comandos disponíveis: [jogar][sair]/n Entre com um comando:" );
		String opcao = input.nextLine();
		switch (opcao) {
			case "jogar":
				j.jogada(dado);
				break;
			//IMPLEMENTAR ENCERRANDO QUANDO FICAR APENAS 1 JOGADOR
			case "sair":				
				System.out.println("sim/nao");
				String sair = input.nextLine();
				if(sair.equals("sim") || sair.equals("s")) {
					System.out.println("Jogo encerrado.");
					System.exit(0);
				}
				opcoes(j);
				break;
		}
	}
	
	private void partida() {
		for(Jogador x: this.listaJogadores) {
			opcoes(x);
		}
		partida();
	}
	
	
}