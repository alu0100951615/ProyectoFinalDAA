package proyecto;

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
	
		MatrizDistancia md = new MatrizDistancia("Instancias/GKD-a_1_n10_m2.txt");
		Grasp grasp = new Grasp(9700, md,10);
		System.out.println(md.elementsDistance(grasp.compute()));
		
	}
	
}
