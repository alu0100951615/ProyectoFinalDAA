package proyecto;

import java.io.File;

public class VND {


	private MatrizDistancia distancias;
	private int ValorKmax;

	public VND(MatrizDistancia distancias,int ValorKmax) {
		this.distancias = distancias;
		this.ValorKmax = ValorKmax;
	}
	
	public Solucion compute() {
		return compute(distancias.generateRandomSolution());
	}
	
	
	public Solucion compute(Solucion sol) {
		boolean condicionParada = false;
		int k = 1;
		while(!condicionParada) {
			if( k >= ValorKmax) {
				condicionParada = true;
			}
			Solucion dummy= distancias.getBestVecino(sol, k);
			if(distancias.elementsDistance(dummy)<=distancias.elementsDistance(sol)) {
				k++;
			}else {
				k = 1;
				sol = dummy;
			}
			
			System.out.println(" Num Puntos " + distancias.getNumPuntos());
			System.out.println(" k " + k);
			System.out.println(" Solucion " + distancias.elementsDistance(sol));
				
			
		}
		
		
		
		return sol;
	}
	
	
	public static void main(String[] args) {
		
		
		long TInicio,TFin,tiempo;
		int kMAX = 100;
		int escenarios = 10;
		float sol;
		File fichero = new File("../Soluciones/VND_2000_200.txt");
		fichero.delete();		
		MatrizDistancia md = new MatrizDistancia(args[0]);
		WriteFich a = new WriteFich("../Soluciones/VND_2000_200.txt");
		
		for (int i = 0; i < escenarios; i++) {
			TInicio = System.nanoTime();
			VND vnd =  new VND(md,kMAX);
			sol = md.elementsDistance((vnd.compute()));
			TFin = System.nanoTime();
			tiempo = TFin - TInicio;
			a.Write(sol, tiempo/1000000,i+1);
			
		}			
		
		
	}
	
	
	//Lo mismo que el vns pero en vez del mejor vecino uno random
	
	
}
//coges una solucion aleatorio 
//Busqueda local, si la solucion es mejor, la k es el minimo, en caso contrario aumenta