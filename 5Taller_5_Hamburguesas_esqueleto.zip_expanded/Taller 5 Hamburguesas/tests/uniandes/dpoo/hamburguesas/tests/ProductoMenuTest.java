package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	private ProductoMenu productoMenu;

	@BeforeEach
	void setUp() throws Exception {
		productoMenu = new ProductoMenu("tomate", 1000);
	}

	@AfterEach
	void tearDown() throws Exception {
		productoMenu = null;
	}

	@Test
	void getNombre() {
		assertEquals("tomate", productoMenu.getNombre(), "El nombre del ingrediente no es el esperado.");
	}

	@Test
	void getPrecio() {
		assertEquals(1000, productoMenu.getPrecio(), "El precio base no es el esperado.");
	}

	@Test
	void generarTextoFactura() {
		StringBuffer sb = new StringBuffer();
		sb.append("tomate" + "\n");
		sb.append("            " + 1000 + "\n");
		assertEquals(sb.toString(), productoMenu.generarTextoFactura(), "La factura se generó incorrectamente.");
	}
}
