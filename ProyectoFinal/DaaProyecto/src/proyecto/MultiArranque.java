package proyecto;

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
		
		MatrizDistancia md = new MatrizDistancia(args[0]);
		MultiArranque ma = new MultiArranque(120000,100, md,100);
		System.out.println(md.elementsDistance(ma.compute()));
		
	}
	
	
}
