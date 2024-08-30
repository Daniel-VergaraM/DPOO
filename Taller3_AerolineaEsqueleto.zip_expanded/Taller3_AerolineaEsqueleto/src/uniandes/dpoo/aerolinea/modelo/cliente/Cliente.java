package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.HashSet;
import java.util.Set;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {

    private Set<Tiquete> tiquetes = new HashSet<Tiquete>();

    public Cliente() {
    }

    public abstract String getTipoCliente();

    public abstract String getIdentificador();

    public void agregarTiquete(Tiquete tiquete) throws NullPointerException {
        try {
            if (!tiquetes.contains(tiquete))
                tiquetes.add(tiquete);
            else
                throw new NullPointerException("El tiquete ya existe");
        } catch (NullPointerException e) {
            throw new NullPointerException("No se puede agregar un tiquete inválido.");
        }
    }

    public int calcularValorTotalTiquetes() throws NullPointerException {
        int total = 0;
        try {
            for (Tiquete tiquete : tiquetes) {
                if (tiquete != null)
                    total += tiquete.getTarifa();
                else
                    total += 0;
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("El cliente no tiene tiquetes");
        }
        return total;
    }

    public void usarTiquetes(Vuelo vuelo) throws NullPointerException {
        try {
            for (Tiquete tiquete : tiquetes) {
                if (tiquete != null && !tiquete.esUsado())
                    tiquete.marcarComoUsado();
                else
                    throw new NullPointerException("El tiquete ya esta usado o no existe.");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("El cliente no tiene tiquetes sin usar.");
        }
    }
}
