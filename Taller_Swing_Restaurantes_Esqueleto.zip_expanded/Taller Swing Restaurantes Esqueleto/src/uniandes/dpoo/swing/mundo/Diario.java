package uniandes.dpoo.swing.mundo;

import java.util.ArrayList;
import java.util.List;

public class Diario {
	/**
	 * La lista de restaurantes, que pueden ser visitados o no
	 */
	private List<Restaurante> restaurantes;

	public Diario() {
		this.restaurantes = new ArrayList<>();
	}

	/**
	 * Retorna una lista de restaurantes
	 * 
	 * @param completos Si es true, retorna todos los restaurantes; si es false,
	 *                  sólo los visitados
	 * @return Una lista con todos los restaurantes o sólo con los visitados
	 */
	public List<Restaurante> getRestaurantes(boolean completos) {
		List<Restaurante> seleccion;
		if (completos)
			seleccion = new ArrayList<>(this.restaurantes);
		else {
			seleccion = new ArrayList<>();
			for (Restaurante r : this.restaurantes) {
				if (r.isVisitado())
					seleccion.add(r);
			}
		}
		return seleccion;

	}

	/**
	 * Agrega un restaurante al diario, al final de la lista
	 * 
	 * @param restaurante
	 */
	public void agregarRestaurante(Restaurante restaurante) throws NullPointerException, RuntimeException {
		if (restaurante == null)
            throw new NullPointerException("El restaurante no puede ser nulo");
		if (this.restaurantes.contains(restaurante))
			throw new RuntimeException("El restaurante ya está en la lista");
		
		this.restaurantes.add(restaurante);
	}
}
