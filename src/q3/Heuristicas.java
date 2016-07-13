package q3;

public class Heuristicas {

	
	//Matriz que guarda a distancia em linha reta
		//entre todas as estacoes possiveis.
		public static int[][] matrizDist = {
			{0,11,20,27,40,43,39,28,18,10,18,30,30,32},//distancia entre E1 e outras
			{11,0,9,16,29,32,28,19,11,4,17,23,21,24},//distancia entre E2 e outras
			{20,9,0,7,20,22,19,15,10,11,21,21,13,18},//distancia entre E3 e outras
			{27,16,7,0,13,16,12,13,13,18,26,21,11,17},//distancia entre E4 e outras
			{40,29,20,13,0,3,2,21,25,31,38,27,16,20},//distancia entre E5 e outras
			{43,32,22,16,3,0,4,23,28,33,41,30,17,20},//distancia entre E6 e outras
			{39,28,19,12,2,4,0,22,25,29,38,28,13,17},//distancia entre E7 e outras
			{28,19,15,13,21,23,22,0,9,22,18,7,25,30},//distancia entre E8 e outras
			{18,11,10,13,25,28,25,9,0,13,12,12,23,28},//distancia entre E9 e outras
			{10,4,11,18,31,33,29,22,13,0,20,27,20,23},//distancia entre E10 e outras
			{18,17,21,26,38,41,38,18,12,20,0,15,35,39},//distancia entre E11 e outras
			{30,23,21,21,27,30,28,7,12,27,15,0,31,37},//distancia entre E12 e outras
			{30,21,13,11,16,17,13,25,23,20,35,31,0,5},//distancia entre E13 e outras
			{32,24,18,17,20,20,17,30,28,23,39,37,5,0},//distancia entre E14 e outras
			};
		
		//checar as distancias de E8.
		
		//Matriz que guarda as estacoes que eu posso ir
		//partindo de uma estacao.
		public static boolean[][] matrizProximidade ={
			{false,true,false,false,false,false,false,false,false,false,false,false,false,false},//possibilidades de E1
			{true,false,true,false,false,false,false,false,true,true,false,false,false,false},//possibilidades de E2
			{false,true,false,true,false,false,false,false,true,false,false,false,true,false},//possibilidades de E3
			{false,false,true,false,true,false,false,true,false,false,false,false,true,false},//possibilidades de E4
			{false,false,false,true,false,true,true,true,false,false,false,false,false,false},//possibilidades de E5
			{false,false,false,false,true,false,false,false,false,false,false,false,false,false},//possibilidades de E6
			{false,false,false,false,true,false,false,false,false,false,false,false,false,false},//possibilidades de E7
			{false,false,false,true,true,false,false,false,true,false,false,true,false,false},//possibilidades de E8
			{false,true,true,false,false,false,false,true,false,false,true,false,false,false},//possibilidades de E9
			{false,true,false,false,false,false,false,false,false,false,false,false,false,false},//possibilidades de E10
			{false,false,false,false,false,false,false,false,true,false,false,false,false,false},//possibilidades de E11
			{false,false,false,false,false,false,false,true,false,false,false,false,false,false},//possibilidades de E12
			{false,false,true,true,false,false,false,false,false,false,false,false,false,true},//possibilidades de E13
			{false,false,false,false,false,false,false,false,false,false,false,false,true,false}//possibilidades de E14
		};
		
		
		//Matriz que informa o numero real da distancia entre 
		//estacoes.
		public static int[][] distanciaEstacoes = {
			{0,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},//distancia entre E1 e outras estacoes
			{11,0,9,-1,-1,-1,-1,-1,11,4-1,-1,-1,-1,},//distancia entre E2 e outras estacoes
			{-1,9,0,7,-1,-1,-1,-1,10,-1,-1,-1,12,-1},//distancia entre E3 e outras estacoes
			{-1,-1,7,0,14,-1,-1,16,-1,-1,-1,-1,12,-1},//distancia entre E4 e outras estacoes
			{-1,-1,-1,14,0,3,2,33,-1,-1,-1,-1,-1,-1},//distancia entre E5 e outras estacoes
			{-1,-1,-1,-1,3,0,-1,-1,-1,-1,-1,-1,-1,-1},//distancia entre E6 e outras estacoes
			{-1,-1,-1,-1,2,-1,0,-1,-1,-1,-1,-1,-1,-1},//distancia entre E7 e outras estacoes
			{-1,-1,-1,16,33,-1,-1,0,10,-1,-1,7,-1,-1},//distancia entre E8 e outras estacoes
			{-1,11,10,-1,-1,-1,-1,10,0,-1,14,-1,-1,-1},//distancia entre E9 e outras estacoes
			{-1,4,-1,-1,-1,-1,-1,-1,-1,0,-1,-1,-1,-1},//distancia entre E10 e outras estacoes
			{-1,-1,-1,-1,-1,-1,-1,-1,14,-1,0,-1,-1,-1},//distancia entre E11 e outras estacoes
			{-1,-1,-1,-1,-1,-1,-1,7,-1,-1,-1,0,-1,-1},//distancia entre E12 e outras estacoes
			{-1,-1,19,12,-1,-1,-1,-1,-1,-1,-1,-1,0,5},//distancia entre E13 e outras estacoes
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5,0},//distancia entre E14 e outras estacoes
			};
		
		/**
		 *  Retorna a distancia em linha reta entre as estacoes.
		 *  
		 * @param estacao1
		 * @param estacao2
		 * @return a distancia em linha reta entre as estacoes parametros.
		 */
		public static int getDistanciaEntreEstacoes(int estacao1,int estacao2){
			int dist = 0;
			
			dist = Heuristicas.matrizDist[estacao1][estacao2];
			
			
			return dist;
		}
		
		/**
		 *  Metodo que faz o teste se as estacoes possui uma rota direta entre elas,
		 *  se possuirem retorna a distancia,senão retorna -1.
		 * @param estacao1
		 * @param estacao2
		 * @return
		 */
		public static int validaCaminhoEstacoes(int estacao1,int estacao2){
			int distEstacoes = 0;
			
			if(Heuristicas.matrizProximidade[estacao1][estacao2]){
				distEstacoes = Heuristicas.distanciaEstacoes[estacao1][estacao2];
			}else{
				distEstacoes = -1;
			}
			
			return distEstacoes;
		}
		
		public static boolean trocaEstacao(int estacao1,int estacao2){
			boolean retorno = false;
			
			if(Heuristicas.matrizProximidade[estacao1][estacao2]){
				retorno = true;
			}
			return retorno;
		}
		
		public static int tempoEstacao(int estacao1,int estacao2,int velocidade){
			int distancia= 0;
			int tempo = 0;
			
			distancia = Heuristicas.distanciaEstacoes[estacao1][estacao2];
			
			tempo= (velocidade /distancia);
			
			return tempo;
		}
}
