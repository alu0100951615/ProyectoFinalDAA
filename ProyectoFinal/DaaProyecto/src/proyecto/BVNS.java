package proyecto;

import java.io.File;

public class BVNS {

	private MatrizDistancia distancias;
	private int ValorKmax;
	long TInicio,TFin,tiempo;

	public BVNS(MatrizDistancia distancias,int ValorKmax) {
		this.distancias = distancias;
		this.ValorKmax = ValorKmax;
	}

	public Solucion compute() {
		Solucion sol = distancias.generateRandomSolution();
		boolean condicionParada = false;
		int k = 1;
		while (!condicionParada) {
			TInicio = System.nanoTime();
			if (k >= ValorKmax) {
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
			
			TFin = System.nanoTime();
			tiempo = TFin - TInicio;
			System.out.println(" Num Puntos " + distancias.getNumPuntos());
			System.out.println(" k " + k);
			System.out.println(" Solucion " + distancias.elementsDistance(sol));
			System.out.println("Tiempo " + tiempo/1000 + "MS" );

		}

		return sol;
	}



	public static void main(String[] args) {
		
		long TInicio,TFin,tiempo;
		int escenarios = 10;
		float sol;
		int kMAX = 75;
		File fichero = new File("../Soluciones/BVNS_2000_200.txt");
		fichero.delete();		
		MatrizDistancia md = new MatrizDistancia(args[0]);
		WriteFich a = new WriteFich("../Soluciones/BVNS_2000_200.txt");
		
		for (int i = 0; i < escenarios; i++) {
			TInicio = System.nanoTime();
			BVNS bvns =  new BVNS(md,kMAX);
			sol = md.elementsDistance((bvns.compute()));
			TFin = System.nanoTime();
			tiempo = TFin - TInicio;
			a.Write(sol, tiempo/1000000,i+1);
			
		}			
		

	}

}
