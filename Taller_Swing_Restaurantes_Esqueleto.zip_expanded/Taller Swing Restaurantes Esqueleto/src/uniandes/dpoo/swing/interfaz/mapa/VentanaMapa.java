package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class VentanaMapa extends JFrame implements ActionListener {

    /**
     * El comando para reconocer al radio que muestra sólo los restaurantes
     * visitados
     */
    private static final String VISITADOS = "VISITADOS";

    /**
     * El comando para reconocer al radio que muestra todos los restaurantes
     */
    private static final String TODOS = "TODOS";

    /**
     * El panel con el mapa
     */
    private PanelMapaVisualizar panelMapa;

    /**
     * El radio button para hacer que se muestren todos los restaurantes. Si
     * este está activo, radioVisitados debe estar inactivo.
     */
    private JRadioButton radioTodos;

    /**
     * El radio button para hacer que se muestren sólo los restaurantes
     * visitados. Si este está activo, radioTodos debe estar inactivo.
     */
    private JRadioButton radioVisitados;

    /**
     * La referencia a la ventana principal
     */
    private VentanaPrincipal ventanaPrincipal;

    public VentanaMapa(VentanaPrincipal ventanaPrincipal, List<Restaurante> restaurantes) {
        this.ventanaPrincipal = ventanaPrincipal;

        panelMapa = new PanelMapaVisualizar();
        panelMapa.actualizarMapa(restaurantes);
        this.add(panelMapa, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout(1, 2));

        radioTodos = new JRadioButton("Todos");
        radioTodos.setActionCommand(TODOS);
        radioTodos.addActionListener(this);
        radioTodos.setSelected(true);

        radioVisitados = new JRadioButton("Visitados");
        radioVisitados.setActionCommand(VISITADOS);
        radioVisitados.addActionListener(this);

        panelBotones.add(radioTodos, BorderLayout.WEST);
        panelBotones.add(radioVisitados, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIcon("icon.jpeg");
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (TODOS.equals(comando)) {
            panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(true));
        } else if (VISITADOS.equals(comando)) {
            panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(false));
        }
    }

    private void setIcon(String path) {
        try {
            this.setIconImage(ImageIO.read(new File("./imagenes/" + path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
