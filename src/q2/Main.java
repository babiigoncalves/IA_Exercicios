package q2;

public class Main {

	public static void main(String[] args) {
		int[] tabTeste = {7,6,5,4,3,2,1,0};
			//{0,1,6,4,5,3,7,2};

		Tabuleiro tab = new Tabuleiro(tabTeste,null);
		Jogo jogo = new Jogo(tab);
		
		jogo.buscarSolucao();

	}

}
