package proyecto;

import java.io.File;

public class BVNS {

	private MatrizDistancia distancias;

	public BVNS(MatrizDistancia distancias) {
		this.distancias = distancias;
	}

	public Solucion compute() {
		Solucion sol = distancias.generateRandomSolution();
		boolean condicionParada = false;
		int k = 1;
		while (!condicionParada) {
			if (k >= distancias.getNumPuntos()) {
				condicionParada = true;
			}
			Solucion dummy = distancias.getRandomVecino(sol, k);
			dummy = distancias.getBestVecino(dummy);
			if (distancias.elementsDistance(dummy) <= distancias.elementsDistance(sol)) {
				k++;
			} else {
				k = 1;
				sol = dummy;
			}

		}

		return sol;
	}



	public static void main(String[] args) {
		
		long TInicio,TFin,tiempo;
		int escenarios = 10;
		float sol;
		File fichero = new File("../Soluciones/BVNS_2000_200.txt");
		fichero.delete();		
		MatrizDistancia md = new MatrizDistancia(args[0]);
		WriteFich a = new WriteFich("../Soluciones/BVNS_2000_200.txt");
		
		for (int i = 0; i < escenarios; i++) {
			TInicio = System.nanoTime();
			BVNS bvns =  new BVNS(md);
			sol = md.elementsDistance((bvns.compute()));
			TFin = System.nanoTime();
			tiempo = TFin - TInicio;
			a.Write(sol, tiempo/1000000,i+1);
			
		}			
		

	}

}
