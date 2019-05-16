package proyecto;

import java.io.File;

public class GVNS {
	

		private MatrizDistancia distancias;
		private int ValorKmax;

		public GVNS(MatrizDistancia distancias, int ValorKmax) {
			this.distancias = distancias;
			this.ValorKmax = ValorKmax;
		}

		public Solucion compute() {
			Solucion sol = distancias.generateRandomSolution();
			boolean condicionParada = false;
			int k = 1;
			VND vndUmmy = new VND(distancias,ValorKmax);
			while (!condicionParada) {
				if (k >= ValorKmax) {
					condicionParada = true;
				}
				
				Solucion dummy = distancias.getRandomVecino(sol, k);
				dummy = vndUmmy.compute(dummy);
				
				if (distancias.elementsDistance(dummy) <= distancias.elementsDistance(sol)) {
					k++;
				} else {
					k = 1;
					sol = dummy; 
				}
				
				System.out.println(" Num Puntos " + distancias.getNumPuntos());
				System.out.println(" k " + k);
				System.out.println(" Solucion " + distancias.elementsDistance(sol));
				System.out.println("************************************************");
				System.out.println("************************************************");
				System.out.println("************************************************");

			}

			return sol;
		}



		public static void main(String[] args) {			
			long TInicio,TFin,tiempo;
			int escenarios = 100;
			int Kmax = 75;
			float sol;
			File fichero = new File("../Soluciones/GVNS_400_160.txt");
			fichero.delete();		
			MatrizDistancia md = new MatrizDistancia(args[0]);
			WriteFich a = new WriteFich("../Soluciones/GVNS_400_160.txt");
			
			for (int i = 0; i < escenarios; i++) {
				TInicio = System.nanoTime();
				GVNS gvns =  new GVNS(md,Kmax);
				sol = md.elementsDistance((gvns.compute()));
				TFin = System.nanoTime();
				tiempo = TFin - TInicio;
				a.Write(sol, tiempo/1000000,i+1);
				
			}			

		}

	
}
