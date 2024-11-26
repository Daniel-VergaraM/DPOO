package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotonesAgregar extends JPanel implements ActionListener {
	/**
	 * El comando utilizado para el botón que sirve para crear un nuevo restaurante
	 */
	private static final String CREAR = "nuevo";

	/**
	 * El comando utilizado para el botón que sirve para cerrar la ventana sin crear
	 * un restaurante
	 */
	private static final String CERRAR = "ver";

	private JButton butNuevo;
	private JButton butCerrar;

	/**
	 * La ventana principal de la aplicación
	 */
	private VentanaAgregarRestaurante ventanaPrincipal;

	public PanelBotonesAgregar(VentanaAgregarRestaurante ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;

		this.setLayout(new FlowLayout());

		JButton butNuevo = new JButton("Agregar Restaurante");
		butNuevo.setActionCommand(CREAR);
		butNuevo.addActionListener(this);
		this.add(butNuevo);

		JButton butCerrar = new JButton("Cerrar");
		butCerrar.setActionCommand(CERRAR);
		butCerrar.addActionListener(this);
		this.add(butCerrar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(CREAR)) {
			ventanaPrincipal.agregarRestaurante();
		} else if (comando.equals(CERRAR)) {
			ventanaPrincipal.cerrarVentana();
		}
	}
}
