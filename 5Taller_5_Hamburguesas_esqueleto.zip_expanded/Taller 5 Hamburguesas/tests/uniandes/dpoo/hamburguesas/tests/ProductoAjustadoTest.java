package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	private ProductoMenu pm;
	private ProductoAjustado pa;

	@BeforeEach
	void setUp() throws Exception {
		pm = new ProductoMenu("costeña", 20000);
		pa = new ProductoAjustado(pm);
	}

	@AfterEach
	void tearDown() throws Exception {
		pa = null;
	}

	@Test
	void getNombre() {
		assertEquals("costeña", pa.getNombre(), "El nombre del producto no es el esperado.");
	}

	@Test
	void ingredienteAgregado() {
		boolean agregado = pa.agregarIngrediente(new Ingrediente("huevo", 2500));
		assertEquals(true, agregado, "No se agregó el ingrediente");
	}

	@Test
	@DisplayName("Agregar ingrediente nulo")
	void ingredienteNuloAgregado() {
		NullPointerException e = assertThrows(NullPointerException.class, () -> pa.agregarIngrediente(null),
				"Se esperaba una excepción cuando se intenta agregar un ingrediente nulo.");

		assertEquals("El ingrediente no puede ser nulo.", e.getMessage(),
				"El mensaje de la excepción no es el esperado.");
	}

	@Test
	void ingredienteRemovido() {
		pa.agregarIngrediente(new Ingrediente("huevo", 2500));
		boolean removido = pa.removerIngrediente("huevo");
		assertEquals(true, removido, "No se pudo eliminar el ingrediente");
	}

	@Test
	@DisplayName("Remover ingrediente nulo")
	void ingredienteNuloRemovido() {
		NullPointerException e = assertThrows(NullPointerException.class, () -> pa.removerIngrediente(null),
				"Se esperaba una excepción cuando se intenta remover un ingrediente con nombre inicializado en nulo.");

		assertEquals("El nombre del ingrediente no puede ser nulo.", e.getMessage(),
				"El mensaje de la excepción no es el esperado.");
	}

	@Test
	void getPrecioAjustado() {
		pa.agregarIngrediente(new Ingrediente("huevo", 2500));
		assertEquals(22500, pa.getPrecio(), "El precio no es el esperado.");
	}

	@Test
	@DisplayName("Precio ajustado sin ingredientes")
	void getPrecioAjustadoEmpty() {
		assertEquals(20000, pa.getPrecio(), "El precio no es el esperado.");
	}

	@Test
	void generarTextoFactura() {
		pa.agregarIngrediente(new Ingrediente("huevo", 2500));
		pa.removerIngrediente("tocineta");
		StringBuffer sb = new StringBuffer();
		sb.append(pm);
		for (Ingrediente ing : pa.getIngredientesAgregados()) {
			sb.append("    +" + ing.getNombre());
			sb.append("                " + ing.getCostoAdicional());
		}

		for (Ingrediente ing : pa.getIngredientesEliminados()) {
			sb.append("    -" + ing.getNombre());
		}

		sb.append("            " + pa.getPrecio() + "\n");
		assertEquals(sb.toString(), pa.generarTextoFactura(), "No se generó la factura correctamente.");
	}

}
