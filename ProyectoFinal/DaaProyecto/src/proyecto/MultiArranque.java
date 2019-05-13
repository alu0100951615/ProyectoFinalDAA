package proyecto;

import java.io.File;

public class MultiArranque {
	//Pillas una solucion del grasp
	//Despues de esta solucion haces muchas veces el bestVecino
	int minDist;
	int iteraciones;
	MatrizDistancia distancias;
	int num;
	public MultiArranque(int minDist, int iteraciones, MatrizDistancia md, int num) {
		this.minDist = minDist;
		this.iteraciones = iteraciones;
		this.distancias = md;
		this.num = num;
	}
	
	public Solucion compute() {
		
		Solucion actual = new Solucion(new Grasp(minDist,distancias,num).compute());
		Solucion maxSol = actual.clone();
		for(int i = 0; i < iteraciones ; i++) {
			actual = distancias.getBestVecino(actual);
			if(distancias.elementsDistance(actual) > distancias.elementsDistance(maxSol)) {
				maxSol = actual;
			}
		
			actual = new Solucion(new Grasp(minDist,distancias,num).compute());
			
			
		}
		
		return maxSol;
		
	}
	
	public static void main(String[] args) { 		
		
		long TInicio,TFin,tiempo;
		int escenarios = 50;
		float sol;
		File fichero = new File("../Soluciones/Multiarranque_150_45.txt");
		fichero.delete();
		
		MatrizDistancia md = new MatrizDistancia(args[0]);
		
		
		WriteFich a = new WriteFich("../Soluciones/Multiarranque_150_45.txt");
		
		for (int i = 0; i < escenarios; i++) {
			TInicio = System.nanoTime();
			MultiArranque ma = new MultiArranque(115000,100, md,100);
			sol = md.elementsDistance((ma.compute()));
			TFin = System.nanoTime();
			tiempo = TFin - TInicio;
			a.Write(sol, tiempo/1000000,i+1);
			
		}			
		
		
	}
	
	
}
