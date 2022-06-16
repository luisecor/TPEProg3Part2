package Grafo;

import java.util.Objects;

/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco implements Comparable<Arco> {
	
		private String destino;
		private Integer valor = 1;
		
		public Arco (String destino) {
			this.destino = destino;
		}
		
		public void sumar() {
			this.valor++;
		}
		
		public String getDestino() {
			return destino;
		}
		
		public Integer getValor() {
			return valor;
		}
		
		public String getVerticeDestino() {
			return destino + " " + valor;
		}

		@Override
		public int hashCode() {
			return Objects.hash(destino);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Arco other = (Arco) obj;
			return Objects.equals(destino, other.destino);
		}

		@Override
		public int compareTo(Arco o) {
			
			return this.valor.compareTo(o.getValor());
		}
		
		

//	private String verticeOrigen;
//	private String verticeDestino;
//	private Integer etiqueta;
//
//	public Arco(String verticeOrigen, String verticeDestino, Integer etiqueta) {
//		this.verticeOrigen = verticeOrigen;
//		this.verticeDestino = verticeDestino;
//		this.etiqueta = etiqueta;
//	}
//	
//	public String getVerticeOrigen() {
//		return verticeOrigen;
//	}
//	
//	public String getVerticeDestino() {
//		return verticeDestino;
//	}
//
//	public Integer getEtiqueta() {
//		return etiqueta;
//	}
//	
//	public void sumar() {
//		this.etiqueta++;		
//	}

}
