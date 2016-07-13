//Aluna: Barbara Camila C. Goncalves
package q2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Jogo {

	Tabuleiro tabuleiroInicial;
	Queue<Tabuleiro> fila;
	ArrayList<Tabuleiro> listaAvaliados;

	public Jogo(Tabuleiro tabuleiroInicial) {
		this.tabuleiroInicial = tabuleiroInicial;
		this.fila = new LinkedList<Tabuleiro>();
		this.listaAvaliados = new ArrayList<Tabuleiro>();
	}

	/**
	 * Método que realiza a permutação das posições das rainhas no tabuleiro
	 * de acordo com uma determinada posicao para gerar filhos.
	 * @param tab
	 * @param posicaoPermutada
	 * @return
	 */
	public ArrayList<Tabuleiro> permutarRainhas(Tabuleiro tab,
			int posicaoPermutada) {
		ArrayList<Tabuleiro> temp = new ArrayList<Tabuleiro>();
		int[] aux;
		int troca;
		int posicao;
		Tabuleiro tabAux;

		posicao = posicaoPermutada;

		do {
			aux = tab.copiarTabuleiro();
			troca = aux[posicao + 1];
			aux[posicao + 1] = aux[posicaoPermutada];
			aux[posicaoPermutada] = troca;

			tabAux = new Tabuleiro(aux, tab);

			if (tab.getPai() == null || !tab.getPai().equals(tabAux)) {
				temp.add(tabAux);
			}
			posicao++;
		} while (posicao < 6); //permuta as rainhas depois da posicao

		//permutar ultima posicao
		if (posicaoPermutada < 6) { 
			posicao++;
			aux = tab.copiarTabuleiro();
			troca = aux[posicao];
			aux[posicao] = aux[posicaoPermutada];
			aux[posicaoPermutada] = troca;

			tabAux = new Tabuleiro(aux, tabAux);

			if (tab.getPai() == null || !tab.getPai().equals(tabAux)) {
				temp.add(tabAux);
			}

			posicao = posicaoPermutada;
		}

		// permutar rainhas antes da posicao informada
		do {
			aux = tab.copiarTabuleiro();
			troca = aux[posicao - 1];
			aux[posicao - 1] = aux[posicaoPermutada];
			aux[posicaoPermutada] = troca;

			tabAux = new Tabuleiro(aux, tab);

			if (tab.getPai() == null || !tab.getPai().equals(tabAux)) {
				temp.add(tabAux);
			}
			posicao--;

		} while (posicao > 1); 
		//permutar a primeira posicao

		if (posicaoPermutada > 1) {
			posicao--;
			aux = tab.copiarTabuleiro();
			troca = aux[posicao];
			aux[posicao] = aux[posicaoPermutada];
			aux[posicaoPermutada] = troca;

			tabAux = new Tabuleiro(aux, tabAux);

			if (tab.getPai() == null || !tab.getPai().equals(tabAux)) {
				temp.add(tabAux);
			}

			posicao = posicaoPermutada;
		}

		return temp;
	}

	/**
	 * Metodo responsavel por gerar os filhos e os ordena.
	 * @param tabuleiro
	 */
	public void gerarFilhos(Tabuleiro tabuleiro) {
		ArrayList<Tabuleiro> listaAux = new ArrayList<Tabuleiro>();

		for (int i = 0; i < 8; i++) {
			try {
				listaAux.addAll(this.permutarRainhas(tabuleiro, i));
			} catch (Exception e) {
				//tratar exception
			}
		}

		//verificando custo/ataques
		this.verificaAtaques(listaAux);
		listaAux.addAll(this.fila);

		this.fila = null;

		Collections.sort(listaAux, new Comparator<Tabuleiro>() {
			@Override
			public int compare(Tabuleiro t1, Tabuleiro t2) {
				return t1.comparar(t2);
			}
		});

		this.fila = new LinkedList<Tabuleiro>();
		this.fila.addAll(listaAux);
	}

	public boolean verificaAvaliados(Tabuleiro tab) {
		boolean avaliado = false;

		for (Tabuleiro tabAux : this.listaAvaliados) {
			if (tabAux.equals(tab)) {
				avaliado = true;
				break;
			}
		}
		return avaliado;
	}

	public void verificaAtaques(ArrayList<Tabuleiro> tabuleiros) {

		for (Tabuleiro tab : tabuleiros) {
			tab.custo = this.verificaAtaquesPosicao(tab.getTabuleiro());
			System.out.println(tab.getCusto());
		}
	}

	public int verificaAtaquesPosicao(int[] vetor) {
		int posicao = 0;
		int qtdAtaques = 0;

		do {
			if ((posicao + 1) <= 7) {
				int proximaPosicao = posicao;
				proximaPosicao++;

				while (proximaPosicao < 8) {
					if (Math.abs(posicao - proximaPosicao) == Math
							.abs(vetor[posicao] - vetor[proximaPosicao])) {
						qtdAtaques++;
					}
					proximaPosicao++;
				}
			}
			posicao++;
		} while (posicao < 8);

		return qtdAtaques;
	}

	public void buscarSolucao() {
		boolean solucao = false;
		Tabuleiro tabTemp = null;

		this.fila.add(this.tabuleiroInicial);

		while (!this.fila.isEmpty() && !solucao) {
			tabTemp = this.fila.remove();

			if (this.verificaAtaquesPosicao(tabTemp.getTabuleiro()) == 0) {
				solucao = true;
			} else {
				if (!this.verificaAvaliados(tabTemp)) {
					this.listaAvaliados.add(tabTemp);
					this.gerarFilhos(tabTemp);
				}
			}
		}
		if (solucao) {
			System.out.println("Solucao encontrada:");
			// implementar imprimir Solucao
			tabTemp.imprimirSolucao();
		//	System.out.println(tabTemp.getCusto());
		} else {
			System.out.println("Bugou!");
		}
	}

}
