package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

	private Combo combo;

	@BeforeEach
	void setUp() throws Exception {
		ArrayList<ProductoMenu> items = new ArrayList<>();
		items.add(new ProductoMenu("corral", 14000));
		items.add(new ProductoMenu("papas medianas", 5500));
		items.add(new ProductoMenu("gaseosa", 5000));
		combo = new Combo("combo corral", 0.1, items);
	}

	@AfterEach
	void tearDown() throws Exception {
		combo = null;
	}

	@Test
	@DisplayName("Add null item")
	void add() {
		assertThrows(NullPointerException.class, () -> {
			Combo c = new Combo("combo especial", 0.095, null);
		});
	}

	@Test
	void getNombre() {
		assertEquals("combo corral", combo.getNombre(), "El nombre no es el esperado.");
	}

	@Test
	void getDescuento() {
		assertEquals(0.1, combo.getDescuento(), "El descuento no es el esperado.");
	}

	@Test
	void getPrecio() {
		assertEquals(22050, combo.getPrecio(), "El precio no es el esperado.");
	}

	@Test
	void getTextoFactura() {
		StringBuffer sb = new StringBuffer();
		sb.append("Combo " + combo.getNombre() + "\n");
		sb.append(" Descuento: " + combo.getDescuento() + "\n");
		sb.append("            " + combo.getPrecio() + "\n");
		assertEquals(sb.toString(), combo.generarTextoFactura(), "No se generó la factura correctamente.");
	}
}
