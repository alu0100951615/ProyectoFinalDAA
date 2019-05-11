
package proyecto;

import java.util.*;

public class Determinista {
	
	private List<Solucion> soluciones = new ArrayList<Solucion>();
	private MatrizDistancia distancias;
	public Determinista(int num, MatrizDistancia distancias){
		this.distancias = distancias;
		for(int i = 0 ; i < num ; i++) {
			
			 soluciones.add(distancias.generateRandomSolution());
			
		}
		
	}
	
	public Solucion pickBetterSolution() {
		
		int maxIndex = 0;
		//System.out.println(" 0 " + distancias.elementsDistance(soluciones.get(0)) +"  " +  soluciones.get(0));
		for(int i = 1; i < soluciones.size(); i++) {
		//	System.out.println(" " + i + " " + distancias.elementsDistance(soluciones.get(i)) +"  " +  soluciones.get(i));
			if(distancias.elementsDistance(soluciones.get(maxIndex)) <  distancias.elementsDistance(soluciones.get(i))) {
				maxIndex = i;
			}
			
		}
		
		//System.out.println(" " + maxIndex + " " + distancias.elementsDistance(soluciones.get(maxIndex)) +"  " +  soluciones.get(maxIndex));
		
		return soluciones.get(maxIndex);
		
		
	}
	
	public Solucion compute() {
		//System.out.println(distancias.elementsDistance(distancias.getBestVecino(pickBetterSolution())));
		return distancias.getBestVecino(pickBetterSolution());
		
	}
	public static void main(String[] args) {
		
		
		MatrizDistancia md = new MatrizDistancia("Instancias/GKD-a_1_n10_m2.txt");
		Determinista det = new Determinista(10, md);
		System.out.println(det.compute());
		
		
	}
	
}
