package ufpb.cartas;

import ufpb.jogo.Jogador;
import ufpb.jogo.JogoFacade;

public class Presente extends SorteOuReves {

	public Presente(String descricao, String valor) {
		super(descricao, valor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void acao(Jogador j) {
		// TODO Auto-generated method stub
		System.out.println(this.descricao);
	}

}
