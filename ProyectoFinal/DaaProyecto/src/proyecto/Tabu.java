package proyecto;

import java.util.ArrayList;

public class Tabu {
	
	MatrizDistancia distancias;
	
	public Tabu(MatrizDistancia distancias) {
		this.distancias = distancias;
	}
	
	
	public Solucion compute() {
		
		Solucion solucion = distancias.generateRandomSolution();
		
		ArrayList<TabuMove> tabuMoves = new ArrayList<TabuMove>();
		
		for(int i = 0; i < Math.sqrt(distancias.getNumPuntos()); i++) {
			
			Solucion solucionActual = distancias.getBestVecinoTabu(solucion, tabuMoves);
			if(distancias.elementsDistance(solucionActual) > distancias.elementsDistance(solucion)) {
				solucion = solucionActual.clone();
			}
			
		}
		
		return solucion;
		
	}
	
	public static void main(String[] args) {
		
		MatrizDistancia md = new MatrizDistancia("Instancias/GKD-b_50_n150_m45.txt");
		Tabu ma = new Tabu(md);
		//for(int i = 0; i < 100; i++)
		System.out.println(md.elementsDistance(ma.compute()));
		
	}
	

}
