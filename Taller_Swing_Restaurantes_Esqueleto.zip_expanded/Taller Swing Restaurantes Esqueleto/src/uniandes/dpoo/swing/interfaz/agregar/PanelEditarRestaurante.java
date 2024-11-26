package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelEditarRestaurante extends JPanel {

    /**
     * El campo para que el usuario ingrese el nombre del restaurante
     */
    private JTextField txtNombre;

    /**
     * Un selector (JComboBox) para que el usuario seleccione la calificación (1
     * a 5) del restaurante
     */
    private JComboBox<String> cbbCalificacion;

    /**
     * Un selector (JComboBox) para que el usuario indique si ya visitó el
     * restaurante o no
     */
    private JComboBox<String> cbbVisitado;

    public PanelEditarRestaurante() {

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout(new GridLayout(1, 2));
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        panelNombre.add(lblNombre);
        panelNombre.add(txtNombre);

        JPanel panelCalificacion = new JPanel();
        panelCalificacion.setLayout(new GridLayout(1, 2));
        JLabel lblCalificacion = new JLabel("Calificación:");
        cbbCalificacion = new JComboBox<String>();
        cbbCalificacion.addItem("1");
        cbbCalificacion.addItem("2");
        cbbCalificacion.addItem("3");
        cbbCalificacion.addItem("4");
        cbbCalificacion.addItem("5");
        panelCalificacion.add(lblCalificacion);
        panelCalificacion.add(cbbCalificacion);

        JPanel panelVisitado = new JPanel();
        panelVisitado.setLayout(new GridLayout(1, 2));
        JLabel lblVisitado = new JLabel("Visitado:");
        cbbVisitado = new JComboBox<>();
        cbbVisitado.addItem("Sí");
        cbbVisitado.addItem("No");
        panelVisitado.add(lblVisitado);
        panelVisitado.add(cbbVisitado);

        this.setLayout(new GridLayout(3, 1));
        this.add(panelNombre);
        this.add(panelCalificacion);
        this.add(panelVisitado);

    }

    /**
     * Indica si en el selector se seleccionó la opción que dice que el
     * restaurante fue visitado
     *
     * @return
     */
    public boolean getVisitado() {
        String visitado = (String) cbbVisitado.getSelectedItem();
        return visitado.equals("Sí");
    }

    /**
     * Indica la calificación marcada en el selector
     *
     * @return
     */
    public int getCalificacion() {
        String calif = (String) cbbCalificacion.getSelectedItem();
        return Integer.parseInt(calif);
    }

    /**
     * Indica el nombre digitado para el restaurante
     *
     * @return
     */
    public String getNombre() {
        return txtNombre.getText();
    }
}
