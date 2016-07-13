//Aluna: Barbara Camila C. Goncalves
package q2;

public class Tabuleiro {

	int[] tabuleiro;
	Tabuleiro pai;
	int custo;

	public Tabuleiro(int[] tabuleiro, Tabuleiro pai) {
		this.verificarTabuleiro(tabuleiro);
		this.tabuleiro = tabuleiro;
		this.pai = pai;
		this.custo = 0;
	}

	/**
	 * Método utilizado para comparar os custos(quantidade de ataques) de cada tabuleiro
	 * @param tabuleiroComp
	 * @return
	 */
	public int comparar(Tabuleiro tabuleiroComp) {
		int custoRetorno = 0;

		if (this.custo > tabuleiroComp.getCusto()) {
			custoRetorno = 1;
		} else if (this.custo == tabuleiroComp.getCusto()) {
			custoRetorno = 0;
		} else {
			custoRetorno = -1;
		}

		return custoRetorno;
	}

	/**
	 * Método para validar os dados do tabuleiro
	 * @param tab
	 * @return
	 */
	public boolean verificarTabuleiro(int[] tab) {
		boolean retorno = true;

		for (int i = 0; i < tab.length; i++) {
			if (tab[i] < 0 || tab[i] > 7) {
				retorno = false;
			}

			for (int j = i + 1; j < tab.length; j++) {
				if (tab[i] == tab[j]) {
					retorno = false;
				}
			}
		}
		if (!retorno) {
			System.out.println("Tabuleiro invalido!");
		}
		return retorno;
	}

	/**
	 * Método auxiliar para realizar copia do tabuleiro
	 * @return
	 */
	public int[] copiarTabuleiro() {
		int[] retorno = new int[8];

		for (int i = 0; i < 8; i++) {
			retorno[i] = this.tabuleiro[i];
		}

		return retorno;
	}
	
	/**
	 * Método que imprime a configuracao final onde nenhuma rainha se ataca
	 */
	public void imprimirSolucao(){
		System.out.print("[");
		for(int i = 0; i<8; i++){
			System.out.print(this.tabuleiro[i]);
			if(i!=7){
				System.out.print("|");
			}
		}
		System.out.print("]");
	}

	public int[] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(int[] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Tabuleiro getPai() {
		return pai;
	}

	public void setPai(Tabuleiro pai) {
		this.pai = pai;
	}

	public int getCusto() {
		return custo;
	}

	public void setCusto(int custo) {
		this.custo = custo;
	}
	
	public boolean equals(Tabuleiro tabuleiro) {
		boolean retorno = true;

		int[] temp = tabuleiro.getTabuleiro();

		for (int i = 0; i < temp.length; i++) {
			if (this.tabuleiro[i] != temp[i]) {
				retorno = false;
				break;
			}
		}

		return retorno;
	}

}
