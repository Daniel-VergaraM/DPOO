package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {

	private Pedido pedido1;

	@BeforeEach
	void setUp() throws Exception {
		pedido1 = new Pedido("Cliente Prueba", "Dirección Prueba");
	}

	@AfterEach
	void tearDown() throws Exception {
		pedido1 = null;
	}

	@Test
	@DisplayName("Crear pedido")
	void testCrearPedido() {
		assertEquals("Cliente Prueba", pedido1.getNombreCliente());
		assertEquals("Dirección Prueba", pedido1.getDireccionCliente());
		assertNotNull(pedido1.getProductos());
		assertEquals(0, pedido1.getProductos().size());
	}

	@Test
	@DisplayName("Agregar producto menu")
	void testAgregarProducto() {
		ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);
		assertTrue(pedido1.agregarProducto(producto));
		assertEquals(1, pedido1.getProductos().size());
		assertEquals(producto, pedido1.getProductos().get(0));
	}

	@Test
	@DisplayName("Agregar producto nulo")
	void addNullProduct() {
		NullPointerException e = assertThrows(NullPointerException.class, () -> pedido1.agregarProducto(null));

		assertEquals("El producto no puede ser nulo.", e.getMessage());
	}

	@Test
	@DisplayName("Precio total pedido")
	void precio() {
		Producto p1 = new ProductoMenu("corral", 14000);
		Producto p2 = new ProductoMenu("especial", 24000);
		pedido1.agregarProducto(p1);
		pedido1.agregarProducto(p2);
		assertEquals(45220, pedido1.getPrecioTotalPedido(), "El precio total no es el esperado.");
	}

	@Test
	@DisplayName("Generar texto factura")
	void testGenerarTextoFactura() {
		pedido1.agregarProducto(new ProductoMenu("Producto1", 10000));
		pedido1.agregarProducto(new ProductoMenu("Producto2", 20000));
		String factura = pedido1.generarTextoFactura();

		assertTrue(factura.contains("Cliente: Cliente Prueba"));
		assertTrue(factura.contains("Dirección: Dirección Prueba"));
		assertTrue(factura.contains("Producto1\n            10000\n"));
		assertTrue(factura.contains("Producto2\n            20000\n"));
		assertTrue(factura.contains("Precio Neto:  30000"));
		assertTrue(factura.contains("IVA:          5700"));
		assertTrue(factura.contains("Precio Total: 35700"));
	}

	@Test
	@DisplayName("Guardar factura en archivo")
	void testGuardarFactura() {
		Producto p1 = new ProductoMenu("corral", 14000);
		Producto p2 = new ProductoMenu("especial", 24000);
		pedido1.agregarProducto(p1);
		pedido1.agregarProducto(p2);

		File archivo = new File("factura_test.txt");

		try {
			pedido1.guardarFactura(archivo);

			String contenidoArchivo = Files.readString(Path.of(archivo.getAbsolutePath()));
			String contenidoEsperado = pedido1.generarTextoFactura();

			assertEquals(contenidoEsperado, contenidoArchivo,
					"El contenido de la factura no coincide con el esperado.");

		} catch (IOException e) {
			fail("No se pudo leer el archivo generado: " + e.getMessage());
		} finally {
			archivo.delete();
		}
	}
}
