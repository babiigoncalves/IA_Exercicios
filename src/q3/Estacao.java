package q3;

public class Estacao {
	
	public Estacao pai;
	public int custo;
	public int distanciaPercorrida;
	public int numeroEstacao;
	
	
	public Estacao(Estacao pai,int numeroEstacao) {
		this.pai = pai;
		this.numeroEstacao = numeroEstacao;
	}




	public boolean equals(Estacao est) {
		if (this.numeroEstacao == est.getNumeroEstacao()){
			return true;
			}
		else{
			return false;
		}
	}
	
	
	public int compareTo(Estacao est){
		int custoCaminho = 0;
		
		custoCaminho = this.custo - est.getCusto();
		
		return custoCaminho;
	}

	
	public Estacao getPai() {
		return pai;
	}


	public void setPai(Estacao pai) {
		this.pai = pai;
	}


	public int getCusto() {
		return custo;
	}


	public void setCusto(int custo) {
		this.custo = custo;
	}


	public int getDistanciaPercorrida() {
		return distanciaPercorrida;
	}


	public void setDistanciaPercorrida(int distanciaPercorrida) {
		this.distanciaPercorrida = distanciaPercorrida;
	}


	public int getNumeroEstacao() {
		return numeroEstacao;
	}


	public void setNumeroEstacao(int numeroEstacao) {
		this.numeroEstacao = numeroEstacao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroEstacao;
		return result;
	}

	
}
