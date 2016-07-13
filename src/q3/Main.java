package q3;

public class Main {

	public static void main(String[] args) {
		//de 0 a 13
		int src = 6;
		int dest = 10;
		Estacao estacaoAtual = new Estacao(null, src);
		Estacao destino = new Estacao(null, dest);
		Trajetorias busca = new Trajetorias(estacaoAtual, destino);
		
		busca.percorrerEstacoes();

	}

}
