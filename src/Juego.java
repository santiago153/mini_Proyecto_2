
import Controlador.Controlador;
import Modelo.Modelo;
import Vista.Vista;

import javax.swing.*;
/**
 * Proyecto: miniProyecto2
 * @author Santiago Nuñez - 2225625
 * @author Julian Mosquera - 2229367
 * Fecha: 06 de junio del 2023
 */
class Juego {
    public static void main(String[] args) {
        String jugador = JOptionPane.showInputDialog("Ingresa tu nombre:");
        Modelo modelo = new Modelo(jugador);
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
        controlador.iniciarJuego();
    }
}
