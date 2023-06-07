package Controlador;
import Modelo.Modelo;
import Vista.Vista;

import java.util.*;
/**
 * Proyecto: miniProyecto2
 * @author Santiago Nuñez - 2225625
 * @author Julian Mosquera - 2229367
 * Fecha: 06 de junio del 2023
 */
/**
 * Esta clase representa el "Controlador" del juego de memoria siguiendo el patrón de diseño MVC.
 * Maneja la interacción entre el "Modelo" y la "Vista" del juego.
 */
public class Controlador {
    private Modelo modelo;
    private Vista vista;
    /**
     * Constructor de la clase JuegoMemoriaControlador.
     *
     * @param modelo El "Modelo" del juego.
     * @param vista  La "Vista" del juego.
     */
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    /**
     * Inicia el juego.
     */
    public void iniciarJuego() {
        vista.mostrarBienvenida(modelo.getJugador());
        while (true) {
            vista.mostrarNivel(modelo.getNivel());
            ArrayList<String> palabrasAMemorizar = new ArrayList<String>(modelo.getPalabrasNivel().subList(0, modelo.getPalabrasAMemorizar()));
            vista.mostrarPalabrasAMemorizar(palabrasAMemorizar);
            vista.pausa();
            for (String palabra : modelo.getPalabrasNivel()) {
                long tiempoInicio = System.currentTimeMillis();
                boolean respuesta = vista.mostrarPalabra(palabra);
                long tiempoFin = System.currentTimeMillis();
                if (tiempoFin - tiempoInicio > 7000) {
                    modelo.error();
                } else {
                    if (palabrasAMemorizar.contains(palabra)) {
                        if (respuesta) {
                            modelo.acierto();
                        } else {
                            modelo.error();
                        }
                    } else {
                        if (respuesta) {
                            modelo.error();
                        } else {
                            modelo.acierto();
                        }
                    }
                }
            }
            if (modelo.nivelSuperado()) {
                vista.mostrarNivelSuperado();
            } else {
                vista.mostrarNivelNoSuperado();
            }
            if (!vista.continuarJugando()) {
                break;
            }
        }
        vista.mostrarDespedida(modelo.getJugador());
    }
}
