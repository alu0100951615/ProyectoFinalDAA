package proyecto;

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
		MatrizDistancia md = new MatrizDistancia(args[0]);
		BVNS bvns =  new BVNS(md);
		System.out.println(md.elementsDistance(bvns.compute()));

	}

}
