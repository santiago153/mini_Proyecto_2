package Vista;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/**
 * Proyecto: miniProyecto2
 * @author Santiago Nuñez - 2225625
 * @author Julian Mosquera - 2229367
 * Fecha: 06 de junio del 2023
 */
/**
 * Esta clase representa la "Vista" del juego de memoria siguiendo el patrón de diseño MVC.
 * Muestra información al usuario y recibe su entrada a través de una interfaz gráfica de usuario creada con la biblioteca Swing.
 */
public class Vista {
    private JFrame frame;
    private JLabel labelNivel;
    private JLabel labelPalabra;
    private JButton botonSi;
    private JButton botonNo;
    private JButton botonContinuar;
    private boolean respuesta;
    /**
     * Constructor de la clase JuegoMemoriaVistaSwing.
     */
    /**
     * Constructor de la clase JuegoMemoriaVistaSwing.
     */
    public Vista() {
        frame = new JFrame("Juego de Memoria");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        labelNivel = new JLabel("", SwingConstants.CENTER);
        frame.add(labelNivel);

        labelPalabra = new JLabel("", SwingConstants.CENTER);
        frame.add(labelPalabra);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2));
        botonSi = new JButton("Si");
        botonSi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                respuesta = true;
            }
        });
        panelBotones.add(botonSi);
        botonNo = new JButton("No");
        botonNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                respuesta = false;
            }
        });
        panelBotones.add(botonNo);
        frame.add(panelBotones);

        botonContinuar = new JButton("Continuar");
        frame.add(botonContinuar);

        frame.pack();
        frame.setVisible(true);
    }
    /**
     * Muestra un mensaje de bienvenida al jugador.
     *
     * @param jugador El nombre del jugador.
     */
    public void mostrarBienvenida(String jugador) {
        JOptionPane.showMessageDialog(frame, "Bienvenido al juego de memoria, " + jugador + "!");
    }
    /**
     * Muestra el nivel actual del juego.
     *
     * @param nivel El nivel actual del juego.
     */
    public void mostrarNivel(int nivel) {
        labelNivel.setText("Nivel " + nivel);
    }
    /**
     * Muestra las palabras a memorizar en el nivel actual del juego.
     *
     * @param palabrasAMemorizar La lista de palabras a memorizar en el nivel actual del juego.
     */
    public void mostrarPalabrasAMemorizar(ArrayList<String> palabrasAMemorizar) {        for (String palabra : palabrasAMemorizar) {
        labelPalabra.setText(palabra);
        pausa(5000);
    }
        labelPalabra.setText("");
    }
    /**
     * Pausa la ejecución del programa durante un tiempo determinado.
     *
     * @param milisegundos El tiempo en milisegundos durante el cual se pausará la ejecución del programa.
     */
    public void pausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println("Error al pausar: " + e.getMessage());
        }
    }
    /**
     * Pausa la ejecución del programa hasta que el usuario presione el botón Continuar.
     */
    public void pausa() {
        botonContinuar.setEnabled(true);
        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                synchronized (frame) {
                    frame.notify();
                }
            }
        });
        synchronized (frame) {
            try {
                frame.wait();
            } catch (InterruptedException e) {
                System.out.println("Error al pausar: " + e.getMessage());
            }
        }
        botonContinuar.setEnabled(false);
    }
    /**
     * Muestra una palabra y recibe la respuesta del usuario (Si/No).
     *
     * @param palabra La palabra a mostrar.
     * @return La respuesta del usuario (true para Si, false para No).
     */
    public boolean mostrarPalabra(String palabra) {
        labelPalabra.setText(palabra);
        respuesta = false;
        botonSi.setEnabled(true);
        botonNo.setEnabled(true);
        long tiempoInicio = System.currentTimeMillis();
        while (System.currentTimeMillis() - tiempoInicio < 7000) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Error al pausar: " + e.getMessage());
            }
        }
        botonSi.setEnabled(false);
        botonNo.setEnabled(false);
        labelPalabra.setText("");
        return respuesta;
    }
    /**
     * Muestra un mensaje indicando que el nivel ha sido superado.
     */
    public void mostrarNivelSuperado() {        JOptionPane.showMessageDialog(frame, "Nivel superado!");
    }
    /**
     * Muestra un mensaje indicando que el nivel no ha sido superado.
     */
    public void mostrarNivelNoSuperado() {
        JOptionPane.showMessageDialog(frame, "Nivel no superado.");
    }
    /**
     * Pregunta al usuario si desea continuar jugando.
     *
     * @return La respuesta del usuario (true para Si, false para No).
     */
    public boolean continuarJugando() {
        int respuesta = JOptionPane.showConfirmDialog(frame, "¿Deseas continuar jugando?", "Continuar jugando", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }
    /**
     * Muestra un mensaje de despedida al jugador.
     *
     * @param jugador El nombre del jugador.
     */
    public void mostrarDespedida(String jugador) {
        JOptionPane.showMessageDialog(frame, "Gracias por jugar, " + jugador + "!");
    }
}
