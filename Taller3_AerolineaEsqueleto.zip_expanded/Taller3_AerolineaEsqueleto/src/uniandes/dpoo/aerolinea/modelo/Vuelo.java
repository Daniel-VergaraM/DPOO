package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
    private String fecha;
    private Ruta ruta;
    private Avion avion;

    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public String getFecha() {
        return fecha;
    }

    public Avion getAvion() {
        return avion;
    }

    public Collection<Tiquete> getTiquetes() {
        return null;
    }

    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) {
        int r = 0;

        for (int i = 0; i < cantidad; i++) {
            Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente,
                    (int) calculadora.calcularTarifa(this, cliente));

            GeneradorTiquetes.registrarTiquete(tiquete);
            cliente.agregarTiquete(tiquete);
            r++;
        }
        if (r >= 0)
            return r;
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Vuelo) {
            Vuelo otro = (Vuelo) obj;
            return this.getRuta().equals(otro.getRuta()) && this.getFecha().equals(otro.getFecha());
        }
        return false;
    }
}
