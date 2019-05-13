package proyecto;

import java.io.*;
import java.util.List;

/**
Clase que sirve para escribir sobre el fichero todas los escenarios que se le proporciones por consola.
 */
public class WriteFich {
	
	String fich;
	Integer ejecucion;
	float Distancia;
	long tiempo;

	public WriteFich(String fich) {
		this.fich = fich;
	}
	
	public void Write(float distancia,long tiempo, Integer ejecucion) {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try {
        	fichero = new FileWriter(fich,true);
            pw = new PrintWriter(fichero);
            	pw.println("****************************** EJECUCION Nº  " + ejecucion + " *****");
            	pw.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
                pw.println(" -------------------- Solucion Distancia ---------------------");
                pw.println(distancia);
                pw.println("--------------------- TIEMPO EN MILISEGUNDOS 10^-3 ------------------ ");
                pw.println(tiempo);

        }catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
                if (null != fichero)
                   fichero.close();
                } catch (Exception e2) {
                   e2.printStackTrace();
             }
        }
	}
}
