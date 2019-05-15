package proyecto;

import java.io.File;
import java.util.*;

public class Grasp {
	
	private List<Solucion> candidatos = new ArrayList<Solucion>();
	private MatrizDistancia distancias;
	public Grasp(int minDist, MatrizDistancia distancias, int num){
		this.distancias = distancias;
		for(int i = 0 ; i < num ; i++) {
			Solucion dummy = distancias.generateRandomSolution();
			if(distancias.elementsDistance(dummy)> minDist) {
				candidatos.add(dummy);
			}
			
		}
		
	}
	
	
	public Solucion compute() {
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		if(candidatos.size() == 0) {
			return new Solucion();
		}else
			return distancias.getBestVecino(candidatos.get( Math.abs(random.nextInt() % candidatos.size())));
		
	}
	public static void main(String[] args) {			
		long TInicio,TFin,tiempo;
		int escenarios = 200;
		float sol;
		File fichero = new File("../Soluciones/GRASP_200_40.txt");
		fichero.delete();
		
		MatrizDistancia md = new MatrizDistancia(args[0]);
		
		
		WriteFich a = new WriteFich("../Soluciones/GRASP_200_40.txt");
		
		for (int i = 0; i < escenarios; i++) {
			TInicio = System.nanoTime();
			Grasp grasp = new Grasp(3000, md,50);
			sol = md.elementsDistance((grasp.compute()));
			TFin = System.nanoTime();
			tiempo = TFin - TInicio;
			a.Write(sol, tiempo/1000000,i+1);
			
		}			
		
	}
		
	
}
