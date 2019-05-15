package proyecto;

import java.io.File;
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
				
		long TInicio,TFin,tiempo;
		int escenarios = 1000;
		float sol;
		File fichero = new File("../Soluciones/Tabu_400_160.txt");
		fichero.delete();
		
		MatrizDistancia md = new MatrizDistancia(args[0]);
		
		
		WriteFich a = new WriteFich("../Soluciones/Tabu_400_160.txt");
		
		for (int i = 0; i < escenarios; i++) {
			TInicio = System.nanoTime();
			Tabu ma = new Tabu(md);
			sol = md.elementsDistance((ma.compute()));
			TFin = System.nanoTime();
			tiempo = TFin - TInicio;
			a.Write(sol, tiempo/1000000,i+1);
			
		}			
		
		
	}
	

}
