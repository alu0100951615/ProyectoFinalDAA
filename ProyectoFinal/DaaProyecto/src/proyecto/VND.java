package proyecto;

public class VND {


	private MatrizDistancia distancias;

	public VND(MatrizDistancia distancias) {
		this.distancias = distancias;
	}
	
	public Solucion compute() {
		Solucion sol = distancias.generateRandomSolution();
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
		
		MatrizDistancia md = new MatrizDistancia("Instancias/GKD-a_51_n30_m6.txt");
		VND vnd =  new VND(md);
		System.out.println(md.elementsDistance(vnd.compute()));
		
	}
	
	
	
	
	
}
//coges una solucion aleatorio 
//Busqueda local, si la solucion es mejor, la k es el minimo, en caso contrario aumenta