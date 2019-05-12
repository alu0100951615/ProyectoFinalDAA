package proyecto;

public class GVNS {
	

		private MatrizDistancia distancias;

		public GVNS(MatrizDistancia distancias) {
			this.distancias = distancias;
		}

		public Solucion compute() {
			Solucion sol = distancias.generateRandomSolution();
			boolean condicionParada = false;
			int k = 1;
			VND vndUmmy = new VND(distancias);
			while (!condicionParada) {
				if (k >= distancias.getNumPuntos()) {
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

			}

			return sol;
		}



		public static void main(String[] args) {
			MatrizDistancia md = new MatrizDistancia(args[0]);
			GVNS gvns =  new GVNS(md);
			System.out.println(md.elementsDistance(gvns.compute()));

		}

	
}
