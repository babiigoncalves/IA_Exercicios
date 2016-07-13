package q3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Trajetorias {

	public Estacao estacaoOrigem;
	public Estacao estacaoDestino;
	public ArrayList<Estacao> listaAvaliados;
	public Queue<Estacao> fila;
	public final int velocidade = 30;
	public double tempo = 0;

	public Trajetorias(Estacao estacaoOrigem, Estacao estacaoDestino) {
		this.estacaoOrigem = estacaoOrigem;
		this.estacaoDestino = estacaoDestino;
		this.listaAvaliados = new ArrayList<Estacao>();
		this.fila = new LinkedList<Estacao>();
	}

	public void gerarFilhos(Estacao estacao) {
		ArrayList<Estacao> listaAux = new ArrayList<Estacao>();
		Estacao estacaoAux = null;
		int tempo = 0;

		for (int i = 0; i < 14; i++) {
			if (Heuristicas
					.validaCaminhoEstacoes(estacao.getNumeroEstacao(), i) > 0) {
				estacaoAux = new Estacao(estacao, i);
				estacaoAux.setDistanciaPercorrida(estacao
						.getDistanciaPercorrida()
						+ Heuristicas.validaCaminhoEstacoes(
								estacao.getNumeroEstacao(), i));
				if(Heuristicas.trocaEstacao(estacao.getNumeroEstacao(), estacaoAux.getNumeroEstacao())){
					tempo = Heuristicas.tempoEstacao(estacao.getNumeroEstacao(), estacaoAux.getNumeroEstacao(), this.getVelocidade());
					estacaoAux.setDistanciaPercorrida(estacaoAux.getDistanciaPercorrida() + tempo);
				}
				estacaoAux.setCusto(estacaoAux.getDistanciaPercorrida()
						+ Heuristicas.getDistanciaEntreEstacoes(
								this.estacaoDestino.getNumeroEstacao(), i));

				try {
					if (!estacaoAux.equals(estacao)
							&& !(estacao.getPai() == (estacaoAux.getPai()))) {
						listaAux.add(estacaoAux);
					}
				} catch (Exception e) {
					// pam!
				}
			}
		}

		listaAux.addAll(this.fila);
		Collections.sort(listaAux, new Comparator<Estacao>() {

			@Override
			public int compare(Estacao estacao1, Estacao estacao2) {
				return estacao1.compareTo(estacao2);
			}
		});

		this.fila = new LinkedList<Estacao>();
		this.fila.addAll(listaAux);

	}

	public void percorrerEstacoes() {
		Estacao estacao = null;
		Estacao destino = null;
		boolean solucao = false;

		this.fila.add(this.estacaoOrigem);

		while (!this.fila.isEmpty()) {

			estacao = this.fila.remove();
			
			if (estacao.getNumeroEstacao() == this.estacaoDestino
					.getNumeroEstacao()) {
				destino = estacao;
				solucao = true;
				break;
			} else {
				if (!verificaEstacoesAvaliadas(estacao)) {
					this.listaAvaliados.add(estacao);
					this.gerarFilhos(estacao);

				}
			}
		}
		if (solucao) {
			this.imprimirPercurso(destino);
		} else {
			System.out.println("Perdeu-se no metro de Parri!xD");
		}
	}

	public boolean verificaEstacoesAvaliadas(Estacao estacao) {
		boolean retorno = false;
		for (Estacao temp : this.listaAvaliados) {
			if (estacao.equals(temp)) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	public void imprimirPercurso(Estacao percurso) {
		Stack<Estacao> pilha = new Stack<Estacao>();
		Estacao estacaoAux = percurso;

		while (estacaoAux != null) {
			pilha.add(estacaoAux);
			estacaoAux = estacaoAux.getPai();

		}
		System.out.println("Percurso:");
		System.out.println("Total:" + pilha.size());
		int tamPilha = pilha.size();
		while (!pilha.isEmpty()) {
			estacaoAux = pilha.pop();
			
			System.out.print("E" + (estacaoAux.getNumeroEstacao() + 1));

			if (!pilha.isEmpty()) {
				System.out.println(" > ");
			}

		}
		System.out.println("Distancia - " + estacaoAux.getDistanciaPercorrida());
		//double tempoCalc = this.CalcTempo(estacaoAux,tamPilha);
		//System.out.println(tempoCalc);
	}

	
	public Estacao getEstacaoOrigem() {
		return estacaoOrigem;
	}

	public void setEstacaoOrigem(Estacao estacaoOrigem) {
		this.estacaoOrigem = estacaoOrigem;
	}

	public Estacao getEstacaoDestino() {
		return estacaoDestino;
	}

	public void setEstacaoDestino(Estacao estacaoDestino) {
		this.estacaoDestino = estacaoDestino;
	}

	public ArrayList<Estacao> getListaAvaliados() {
		return listaAvaliados;
	}

	public void setListaAvaliados(ArrayList<Estacao> listaAvaliados) {
		this.listaAvaliados = listaAvaliados;
	}

	public Queue<Estacao> getFila() {
		return fila;
	}

	public void setFila(Queue<Estacao> fila) {
		this.fila = fila;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public int getVelocidade() {
		return velocidade;
	}

}
