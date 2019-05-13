
package proyecto;

import java.io.File;
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
		
		long TInicio,TFin,tiempo;
		int escenarios = 50;
		float sol;
		File fichero = new File("../Soluciones/Determinista_2000_200.txt");
		fichero.delete();
		MatrizDistancia md = new MatrizDistancia(args[0]);
		WriteFich a = new WriteFich("../Soluciones/Determinista_2000_200.txt");
		
		for (int i = 0; i < escenarios; i++) {
			TInicio = System.nanoTime();
			Determinista det = new Determinista(10, md);
			sol = md.elementsDistance(det.compute());
			TFin = System.nanoTime();
			tiempo = TFin - TInicio;
			a.Write(sol, tiempo/1000000,i+1);
			
		}			
		
	}
	
}
