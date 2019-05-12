package proyecto;

public class VND {


	private MatrizDistancia distancias;

	public VND(MatrizDistancia distancias) {
		this.distancias = distancias;
	}
	
	public Solucion compute() {
		return compute(distancias.generateRandomSolution());
	}
	
	
	public Solucion compute(Solucion sol) {
		boolean condicionParada = false;
		int k = 1;
		while(!condicionParada) {
			if( k >= distancias.getNumPuntos()) {
				condicionParada = true;
			}
			Solucion dummy= distancias.getBestVecino(sol, k);
			if(distancias.elementsDistance(dummy)<=distancias.elementsDistance(sol)) {
				k++;
			}else {
				k = 1;
				sol = dummy;
			}
				
			
		}
		
		
		
		return sol;
	}
	
	
	public static void main(String[] args) {
		
		MatrizDistancia md = new MatrizDistancia("Instancias/GKD-b_50_n150_m45.txt");
		VND vnd =  new VND(md);
		System.out.println(md.elementsDistance(vnd.compute()));
		
	}
	
	
	//Lo mismo que el vns pero en vez del mejor vecino uno random
	
	
}
//coges una solucion aleatorio 
//Busqueda local, si la solucion es mejor, la k es el minimo, en caso contrario aumenta