package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre
 * arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y
 * arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas
 * como construir listas para evitar la manipulación de arreglos).
 */
public class SandboxArreglos {
	/**
	 * Un arreglo de enteros para realizar varias de las siguientes operaciones.
	 * 
	 * Ninguna posición del arreglo puede estar vacía en ningún momento.
	 */
	private int[] arregloEnteros;

	/**
	 * Un arreglo de cadenas para realizar varias de las siguientes operaciones
	 * 
	 * Ninguna posición del arreglo puede estar vacía en ningún momento.
	 */
	private String[] arregloCadenas;

	/**
	 * Crea una nueva instancia de la clase con los dos arreglos inicializados pero
	 * vacíos (tamaño 0)
	 */
	public SandboxArreglos() {
		arregloEnteros = new int[] {};
		arregloCadenas = new String[] {};
	}

	/**
	 * Retorna una copia del arreglo de enteros, es decir un nuevo arreglo del mismo
	 * tamaño que contiene copias de los valores del arreglo original
	 * 
	 * @return Una copia del arreglo de enteros
	 */
	public int[] getCopiaEnteros() {
		int[] copiaEnteros = arregloEnteros.clone();
		return copiaEnteros;
	}

	/**
	 * Retorna una copia del arreglo de cadenas, es decir un nuevo arreglo del mismo
	 * tamaño que contiene copias de los valores del arreglo original
	 * 
	 * @return Una copia del arreglo de cadenas
	 */
	public String[] getCopiaCadenas() {
		String[] copiaCadenas = arregloCadenas.clone();
		return copiaCadenas;
	}

	/**
	 * Retorna la cantidad de valores en el arreglo de enteros
	 * 
	 * @return
	 */
	public int getCantidadEnteros() {
		int cantidadEnteros = arregloEnteros.length;
		return cantidadEnteros;
	}

	/**
	 * Retorna la cantidad de valores en el arreglo de cadenas
	 * 
	 * @return
	 */
	public int getCantidadCadenas() {
		int cantidadCadenas = arregloCadenas.length;
		return cantidadCadenas;
	}

	/**
	 * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre
	 * debería aumentar en 1 la capacidad del arreglo.
	 * 
	 * @param entero El valor que se va a agregar.
	 */
	public void agregarEntero(int entero) {
		int[] copia = this.getCopiaEnteros();
		int[] nuevo = new int[copia.length + 1];

		for (int i = 0; i < copia.length; i++) {
			nuevo[i] = copia[i];
		}

		nuevo[nuevo.length - 1] = entero;
		arregloEnteros = nuevo.clone();
	}

	/**
	 * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre
	 * debería aumentar en 1 la capacidad del arreglo.
	 * 
	 * @param cadena La cadena que se va a agregar.
	 */
	public void agregarCadena(String cadena) {
		String[] copia = this.getCopiaCadenas();
		String[] nuevo = new String[copia.length + 1];

		for (int i = 0; i < copia.length; i++) {
			nuevo[i] = copia[i];
		}

		nuevo[nuevo.length - 1] = cadena;
		arregloCadenas = nuevo.clone();
	}

	/**
	 * Elimina todas las apariciones de un determinado valor dentro del arreglo de
	 * enteros
	 * 
	 * @param valor El valor que se va eliminar
	 */
	public void eliminarEntero(int valor) {
		int[] copia = this.getCopiaEnteros();
		int index = 0;

		for (int i = 0; i < copia.length; i++) {
			if (copia[i] != valor) {
				copia[index++] = copia[i];
			}
		}

		int[] nuevo = new int[index];

		for (int i = 0; i < index; i++) {
			nuevo[i] = copia[i];
		}

		arregloEnteros = nuevo.clone();
	}

	public void eliminarCadena(String cadena) {
		List<String> listaFiltrada = new ArrayList<>();

		for (String current : arregloCadenas) {
			if (!current.equals(cadena)) {
				listaFiltrada.add(current);
			}
		}

		arregloCadenas = listaFiltrada.toArray(new String[0]);
	}
	/*
	 * String[] copia = this.getCopiaCadenas();
	 * int index = 0;
	 * 
	 * for (int i = 0; i < copia.length; i++) {
	 * if (copia[i] != cadena) {
	 * copia[index++] = copia[i];
	 * }
	 * }
	 * arregloCadenas = copia.clone();
	 */

	/**
	 * Inserta un nuevo entero en el arreglo de enteros.
	 * 
	 * @param entero   El nuevo valor que debe agregarse
	 * @param posicion La posición donde debe quedar el nuevo valor en el arreglo
	 *                 aumentado. Si la posición es menor a 0, se inserta el valor
	 *                 en la primera posición. Si la posición es mayor que el tamaño
	 *                 del arreglo, se inserta el valor en la última posición.
	 */
	public void insertarEntero(int entero, int posicion) {
		int[] copia = this.getCopiaEnteros();
		int[] nuevo = new int[copia.length + 1];

		if (posicion < 0)
			posicion = 0;
		if (posicion >= copia.length)
			posicion = nuevo.length - 1;

		for (int i = 0; i < posicion; i++) {
			nuevo[i] = copia[i];
		}

		nuevo[posicion] = entero;

		for (int i = posicion; i < copia.length; i++) {
			nuevo[i + 1] = copia[i];
		}

		arregloEnteros = nuevo.clone();
	}

	/**
	 * Elimina un valor del arreglo de enteros dada su posición.
	 * 
	 * @param posicion La posición donde está el elemento que debe ser eliminado. Si
	 *                 el parámetro posicion no corresponde a ninguna posición del
	 *                 arreglo de enteros, el método no debe hacer nada.
	 */
	public void eliminarEnteroPorPosicion(int posicion) {
		int[] copia = this.getCopiaEnteros();

		if (posicion < 0 || posicion >= copia.length) {
			return;
		}

		int[] nuevoArreglo = new int[copia.length - 1];
		for (int i = 0, j = 0; i < copia.length; i++) {
			if (i != posicion) {
				nuevoArreglo[j++] = arregloEnteros[i];
			}
		}

		arregloEnteros = nuevoArreglo;
	}

	/**
	 * Reinicia el arreglo de enteros con los valores contenidos en el arreglo del
	 * parámetro 'valores' truncados.
	 * 
	 * Es decir que si el valor fuera 3.67, en el nuevo arreglo de enteros debería
	 * quedar el entero 3.
	 * 
	 * @param valores Un arreglo de valores decimales.
	 */
	public void reiniciarArregloEnteros(double[] valores) {
		int[] nuevo = new int[valores.length];

		for (int i = 0; i < nuevo.length; i++) {
			nuevo[i] = (int) valores[i];
		}

		arregloEnteros = nuevo.clone();
	}

	/**
	 * Reinicia el arreglo de cadenas con las representaciones como Strings de los
	 * objetos contenidos en el arreglo del parámetro 'objetos'.
	 * 
	 * Use el método toString para convertir los objetos a cadenas.
	 * 
	 * @param valores Un arreglo de objetos
	 */
	public void reiniciarArregloCadenas(Object[] objetos) {
		String[] nuevo = new String[objetos.length];

		for (int i = 0; i < nuevo.length; i++) {
			nuevo[i] = objetos[i].toString();
		}

		arregloCadenas = nuevo.clone();
	}

	/**
	 * Modifica el arreglo de enteros para que todos los valores sean positivos.
	 * 
	 * Es decir que si en una posición había un valor negativo, después de ejecutar
	 * el método debe quedar el mismo valor muliplicado por -1.
	 */
	public void volverPositivos() {
		int[] copia = this.getCopiaEnteros();
		int[] nuevo = new int[copia.length];

		for (int i = 0; i < copia.length; i++) {
			if (copia[i] < 0)
				nuevo[i] = copia[i] * -1;
			if (copia[i] >= 0)
				nuevo[i] = copia[i];
		}
		arregloEnteros = nuevo.clone();
	}

	/**
	 * Modifica el arreglo de enteros para que todos los valores queden organizados
	 * de menor a mayor.
	 */
	public void organizarEnteros() {

		int[] copia = this.getCopiaEnteros();

		boolean cambiado = true;
		int temp;

		while (cambiado && copia.length != 0) {
			cambiado = false;
			for (int i = 1; i < copia.length; i++) {
				if (copia[i - 1] <= copia[i]) {
					temp = copia[i - 1];
					copia[i - 1] = copia[i];
					copia[i] = temp;
					cambiado = true;
				}
			}
		}
		arregloEnteros = copia;
	}

	/**
	 * Modifica el arreglo de cadenas para que todos los valores queden organizados
	 * lexicográficamente.
	 */
	public void organizarCadenas() {
		String[] copia = this.getCopiaCadenas();
		String temp;

		for (int i = 0; i < copia.length; i++) {
			for (int j = i + 1; j < copia.length; j++) {
				if (copia[i].compareToIgnoreCase(copia[j]) > 0) {
					temp = copia[i];
					copia[i] = copia[j];
					copia[j] = temp;
				}
			}
		}

		arregloCadenas = copia.clone();
	}

	/**
	 * Cuenta cuántas veces aparece el valor recibido por parámetro en el arreglo de
	 * enteros
	 * 
	 * @param valor El valor buscado
	 * @return La cantidad de veces que aparece el valor
	 */
	public int contarApariciones(int valor) {
		int[] copia = this.getCopiaEnteros();
		int ap = 0;

		for (int i = 0; i < copia.length; i++) {
			if (copia[i] == valor)
				ap++;
		}
		if (ap != -1)
			return ap;
		return ap;
	}

	/**
	 * Cuenta cuántas veces aparece la cadena recibida por parámetro en el arreglo
	 * de cadenas.
	 * 
	 * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
	 * 
	 * @param cadena La cadena buscada
	 * @return La cantidad de veces que aparece la cadena
	 */
	public int contarApariciones(String cadena) {
		String[] copia = this.getCopiaCadenas();
		String cadenaMinus = cadena.toLowerCase();
		int ap = 0;

		for (int i = 0; i < copia.length; i++) {
			if (copia[i].toLowerCase().equals(cadenaMinus))
				ap++;
		}
		if (ap != -1)
			return ap;

		return ap;
	}

	/**
	 * Busca en qué posiciones del arreglo de enteros se encuentra el valor que se
	 * recibe en el parámetro
	 * 
	 * @param valor El valor que se debe buscar
	 * @return Un arreglo con los números de las posiciones del arreglo de enteros
	 *         en las que se encuentra el valor buscado. Si el valor no se
	 *         encuentra, el arreglo retornado es de tamaño 0.
	 */
	public int[] buscarEntero(int valor) {
		int[] copia = this.getCopiaEnteros();
		int index = 0;

		for (int i = 0; i < copia.length; i++) {
			if (copia[i] == valor) {
				index++;
			}
		}
		if (index == 0)
			return new int[0];

		int[] aps = new int[index];
		int k = 0;
		for (int i = 0; i < copia.length; i++) {
			if (copia[i] == valor) {
				aps[k++] = i;
			}
		}

		return aps;
	}

	/**
	 * Calcula cuál es el rango de los enteros (el valor mínimo y el máximo).
	 * 
	 * @return Un arreglo con dos posiciones: en la primera posición, debe estar el
	 *         valor mínimo en el arreglo de enteros; en la segunda posición, debe
	 *         estar el valor máximo en el arreglo de enteros. Si el arreglo está
	 *         vacío, debe retornar un arreglo vacío.
	 */
	public int[] calcularRangoEnteros() {
		int[] copia = this.getCopiaEnteros();
		int min = (int) 999999L;
		int max = 0;

		if (copia.length == 0)
			return new int[0];

		for (int i = 0; i < copia.length; i++) {
			if (copia[i] < min)
				min = copia[i] - 1;
			if (copia[i] > max)
				max = copia[i];
		}

		int[] rango = { min, max };

		return rango;
	}

	/**
	 * Calcula un histograma de los valores del arreglo de enteros y lo devuelve
	 * como un mapa donde las llaves son los valores del arreglo y los valores son
	 * la cantidad de veces que aparece cada uno en el arreglo de enteros.
	 * 
	 * @return Un mapa con el histograma de valores.
	 */
	public HashMap<Integer, Integer> calcularHistograma() {
		int[] copia = this.getCopiaEnteros();
		HashMap<Integer, Integer> hist = new HashMap<Integer, Integer>();

		for (int i = 0; i < copia.length; i++) {
			hist.put(copia[i], hist.getOrDefault(copia[i], 0) + 1);
		}

		return hist;
	}

	/**
	 * Cuenta cuántos valores dentro del arreglo de enteros están repetidos.
	 * 
	 * @return La cantidad de enteros diferentes que aparecen más de una vez
	 */
	public int contarEnterosRepetidos() {
		int[] copia = this.getCopiaEnteros();

		int contador = 0;

		for (int i = 0; i < copia.length; i++) {
			boolean repetido = false;
			boolean contado = false;

			for (int j = 0; j < i; j++) {
				if (copia[i] == copia[j]) {
					contado = true;
					break;
				}
			}

			if (!contado) {
				for (int k = i + 1; k < copia.length; k++) {

					if (copia[i] == copia[k]) {
						repetido = true;
						break;
					}

				}

				if (repetido) {
					contador++;
				}
			}
		}

		if (contador != 0)
			return contador;
		return contador;
	}

	/**
	 * Compara el arreglo de enteros con otro arreglo de enteros y verifica si son
	 * iguales, es decir que contienen los mismos elementos exactamente en el mismo
	 * orden.
	 * 
	 * @param otroArreglo El arreglo de enteros con el que se debe comparar
	 * @return True si los arreglos son idénticos y false de lo contrario
	 */
	public boolean compararArregloEnteros(int[] otroArreglo) {
		int[] copia = this.getCopiaEnteros();
		boolean r = false;

		if (copia.length != otroArreglo.length)
			return r;

		for (int i = 0; i < copia.length; i++) {
			if (copia[i] != otroArreglo[i])
				return r;
		}

		return !r;
	}

	/**
	 * Compara el arreglo de enteros con otro arreglo de enteros y verifica que
	 * tengan los mismos elementos, aunque podría ser en otro orden.
	 * 
	 * @param otroArreglo El arreglo de enteros con el que se debe comparar
	 * @return True si los elementos en los dos arreglos son los mismos
	 */
	public boolean mismosEnteros(int[] otroArreglo) {

		int[] copia = this.getCopiaEnteros();
		boolean r = true;

		if (copia.length != otroArreglo.length)
			r = false;

		if (!r) {

			boolean[] usado = new boolean[copia.length];

			for (int i = 0; i < copia.length; i++) {
				boolean encontrado = false;
				for (int j = 0; j < otroArreglo.length; j++) {
					if (!usado[j] && copia[i] == otroArreglo[j]) {
						encontrado = true;
						usado[j] = true;
						break;
					}
				}

				if (!encontrado)
					r = false;
			}
		}

		return r;
	}

	/**
	 * Cambia los elementos del arreglo de enteros por una nueva serie de valores
	 * generada de forma aleatoria.
	 * 
	 * Para generar los valores se debe partir de una distribución uniforme usando
	 * Math.random().
	 * 
	 * Los números en el arreglo deben quedar entre el valor mínimo y el máximo.
	 * 
	 * @param cantidad La cantidad de elementos que debe haber en el arreglo
	 * @param minimo   El valor mínimo para los números generados
	 * @param maximo   El valor máximo para los números generados
	 */
	public void generarEnteros(int cantidad, int minimo, int maximo) {
		int[] nuevo = new int[cantidad];

		for (int i = 0; i < cantidad; i++) {

			double random = Math.random() * (maximo - minimo + 1) + minimo;
			int v = (int) random;
			nuevo[i] = v;

		}
		arregloEnteros = nuevo.clone();
	}

}
