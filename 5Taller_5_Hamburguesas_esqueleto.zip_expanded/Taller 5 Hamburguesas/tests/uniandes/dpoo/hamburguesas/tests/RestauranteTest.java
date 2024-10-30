package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.DisplayName;

import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.*;

public class RestauranteTest {
	private Restaurante r;

	@TempDir
	File tempDir;

	@BeforeEach
	void setUp() throws Exception {
		r = new Restaurante();
	}

	@AfterEach
	void tearDown() throws Exception {
		r = null;
	}

	@Test
	@DisplayName("Iniciar pedido")
	void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
		r.iniciarPedido("Cliente1", "Dirección1");
		assertNotNull(r.getPedidoEnCurso());
		assertEquals("Cliente1", r.getPedidoEnCurso().getNombreCliente());
	}

	@Test
	@DisplayName("Iniciar pedido con uno en curso")
	void testIniciarPedidoConPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
		r.iniciarPedido("Cliente1", "Dirección1");
		assertThrows(YaHayUnPedidoEnCursoException.class, () -> {
			r.iniciarPedido("Cliente2", "Dirección2");
		});
	}

	@Test
	@DisplayName("No hay pedido en curso")
	void testCerrarYGuardarPedidoSinPedidoEnCurso() {
		assertThrows(NoHayPedidoEnCursoException.class, () -> {
			r.cerrarYGuardarPedido();
		});
	}

	@Test
	@DisplayName("Cerrar y guardar pedido")
	void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
		r.iniciarPedido("Cliente1", "Dirección1");
		r.getPedidoEnCurso().agregarProducto(new ProductoAjustado(new ProductoMenu("corral", 20000)));
		r.cerrarYGuardarPedido();
		assertNull(r.getPedidoEnCurso());
		assertTrue(r.getPedidos().isEmpty());
	}

	@Test
	@DisplayName("Cargar menu repetido")
	void testCargarMenuRepetido() throws IOException, NoSuchMethodException, SecurityException {
		File menuFile = new File(tempDir, "menu.txt");
		try (FileWriter writer = new FileWriter(menuFile)) {
			writer.write("Hamburguesa;15000\nHamburguesa;15000\n");
		}

		Method cargarMenuMethod = Restaurante.class.getDeclaredMethod("cargarMenu", File.class);
		cargarMenuMethod.setAccessible(true);

		Exception exception = assertThrows(InvocationTargetException.class, () -> {
			cargarMenuMethod.invoke(r, menuFile);
		});

		assertTrue(exception.getCause() instanceof ProductoRepetidoException,
				"Se esperaba ProductoRepetidoException pero se obtuvo: " + exception.getCause());
	}

	@Test
	@DisplayName("Cargar ingredientes repetidos")
	void testCargarIngredientesRepetidos()
			throws IOException, NoSuchMethodException, SecurityException, IngredienteRepetidoException {
		File ingredientesFile = new File(tempDir, "ingredientes.txt");
		try (FileWriter writer = new FileWriter(ingredientesFile)) {
			writer.write("Lechuga;300\nLechuga;300\n");
		}

		Method cargarIngredientesMethod = Restaurante.class.getDeclaredMethod("cargarIngredientes", File.class);
		cargarIngredientesMethod.setAccessible(true);

		Exception exception = assertThrows(InvocationTargetException.class, () -> {
			cargarIngredientesMethod.invoke(r, ingredientesFile);
		});

		assertTrue(exception.getCause() instanceof IngredienteRepetidoException,
				"Se esperaba IngredienteRepetidoException pero se obtuvo: " + exception.getCause());
	}

	@Test
	@DisplayName("Cargar combos con producto faltante")
	void testCargarCombosConProductoFaltante()
			throws IOException, ProductoRepetidoException, NoSuchMethodException, SecurityException {
		File combosFile = new File(tempDir, "combos.txt");
		try (FileWriter writer = new FileWriter(combosFile)) {
			writer.write("Combo1;10%;Pizza;Vino\n");
		}

		Method cargarCombosMethod = Restaurante.class.getDeclaredMethod("cargarCombos", File.class);
		cargarCombosMethod.setAccessible(true);
		assertThrows(InvocationTargetException.class, () -> {
			cargarCombosMethod.invoke(r, combosFile);
		});
		r.getMenuCombos();
	}

	@Test
	@DisplayName("Cargar toda la información del restaurante")
	void testCargarInformacionRestaurante() throws IOException, NumberFormatException, HamburguesaException {
// r.getMenuBase().add(new ProductoMenu("Pizza", 15000));
		// r.getMenuBase().add(new ProductoMenu("Refresco", 5000));
		File ingredientesFile = new File(tempDir, "ingredientes.txt");
		try (FileWriter writer = new FileWriter(ingredientesFile)) {
			writer.write("Lechuga;300\nTomate;250\n");
		}

		File menuFile = new File(tempDir, "menu.txt");
		try (FileWriter writer = new FileWriter(menuFile)) {
			writer.write("Hamburguesa;15000\nPapas;5000\n");
		}

		File combosFile = new File(tempDir, "combos.txt");
		try (FileWriter writer = new FileWriter(combosFile)) {
			writer.write("Combo1;10%;Hamburguesa;Papas\n");
		}

		r.cargarInformacionRestaurante(ingredientesFile, menuFile, combosFile);

		ArrayList<Ingrediente> ingredientes = r.getIngredientes();
		assertEquals(2, ingredientes.size(), "Se esperaban 2 ingredientes");
		assertEquals("Lechuga", ingredientes.get(0).getNombre());
		assertEquals(300, ingredientes.get(0).getCostoAdicional());

		ArrayList<ProductoMenu> menuBase = r.getMenuBase();
		assertEquals(2, menuBase.size(), "Se esperaban 2 productos en el menú base");
		assertEquals("Hamburguesa", menuBase.get(0).getNombre());
		assertEquals(15000, menuBase.get(0).getPrecio());

		ArrayList<Combo> combos = r.getMenuCombos();
		assertEquals(1, combos.size(), "Se esperaba 1 combo");
		assertEquals("Combo1", combos.get(0).getNombre());
		assertEquals(0.10, combos.get(0).getDescuento());
	}
}
