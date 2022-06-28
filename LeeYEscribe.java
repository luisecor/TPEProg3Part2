package Generos;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;


public class LeeYEscribe {

	public BufferedReader readFile(String csvFile) {
        try {
        	return new BufferedReader(new FileReader(csvFile)); 
           
        } catch (IOException e) {
        	
            e.printStackTrace();
        }
        
		return null;
	}
	
	public static void cargarCSVGeneros(GrafoDirigido grafo/*, String csvFile*/) {
		String csvFile = ".\\assets\\dataset3.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	int fila = 0;
        	System.out.println("Leyendo CSV");
        	System.out.println("Generando Estructura");
            while ((line = br.readLine()) != null) {            	
            	if (fila>0) {
          
		                String[] items = line.split(",");

		                int index = 0;
		                while(index < items.length-1 && items.length >1) {
		                	//System.out.println(items[index]+ " - " + items[index+1]);
		                	grafo.agregarVertice(items[index]);
		                	grafo.agregarVertice(items[index+1]);
		                	grafo.agregarArco(items[index],items[index+1]);
		                	index++;
		                }
            	}
            	fila++ ;
            }
         
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------");
        grafo.ordenarArcos();
	}
	
	

}