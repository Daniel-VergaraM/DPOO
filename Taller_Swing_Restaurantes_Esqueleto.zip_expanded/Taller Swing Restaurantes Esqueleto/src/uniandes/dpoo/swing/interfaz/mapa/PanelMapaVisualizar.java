package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelMapaVisualizar extends JPanel {

    /**
     * La etiqueta donde se dibuja el mapa y se hacen las señales de los
     * restaurantes
     */
    private JLabel labMapa;

    /**
     * La lista de restaurantes que se están dibujando en el mapa
     */
    private List<Restaurante> restaurantes;

    public PanelMapaVisualizar() {
        this.labMapa = new JLabel(new ImageIcon("./imagenes/mapa.png"));
        labMapa.setBorder(new LineBorder(Color.DARK_GRAY));
        add(labMapa, BorderLayout.CENTER);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (restaurantes != null) {
            HashMap<String, int[]> posiciones = getPosiciones();

            for (Restaurante r : restaurantes) {
                int[] pos = posiciones.get(r.getNombre());
                g2d.setColor(Color.red);
                g2d.fillOval(pos[0] - 3, pos[1] - 3, 7, 7);
            }

            for (Restaurante r : restaurantes) {
                int[] pos = posiciones.get(r.getNombre());
                g2d.setColor(Color.black);
                g2d.drawString(r.getNombre(), pos[0], pos[1]);
            }
        }

    }

    /**
     * Retorna un mapa con las posiciones de los restaurantes
     *
     * @return
     */
    private HashMap<String, int[]> getPosiciones() {
        HashMap<String, int[]> posiciones = new HashMap<>();

        for (Restaurante r : restaurantes) {
            int x = r.getX();
            int y = r.getY();
            posiciones.put(r.getNombre(), new int[]{x, y});
        }

        return posiciones;
    }

    /**
     * Actualiza la lista de restaurantes y llama al método repaint() para que
     * se actualice el mapa
     *
     * @param nuevosRestaurantes
     */
    public void actualizarMapa(List<Restaurante> nuevosRestaurantes) {
        if (restaurantes != null) {
            this.restaurantes.clear();
            this.restaurantes.addAll(nuevosRestaurantes);
        } else {
            this.restaurantes = nuevosRestaurantes;
        }
        repaint();
    }
}
