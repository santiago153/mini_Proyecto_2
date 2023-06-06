package Modelo;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Proyecto: miniProyecto2
 * @author Santiago Nuñez - 2225625
 * @author Julian Mosquera - 2229367
 * Fecha: 06 de junio del 2023
 */
public class Modelo {
    private int nivel;
    private String jugador;
    private int palabrasAMemorizar;
    private int palabrasDelNivel;
    private int porcentajeAciertos;
    private ArrayList<String> palabras;
    private ArrayList<String> palabrasNivel;
    /**
     * Constructor de la clase JuegoMemoriaModelo.
     * Carga la información del jugador y del nivel, así como las palabras del juego.
     *
     * @param jugador El nombre del jugador.
     */
    public Modelo(String jugador) {
        this.jugador = jugador;
        guardarJugador();
        cargarJugador(jugador);
        cargarNivel();
        cargarPalabras();
        seleccionarPalabrasNivel();
    }
    /**
     * Carga la información del jugador desde un archivo de texto.
     * @param nombreJugador El nombre del jugador a cargar.
     */
    public void cargarJugador(String nombreJugador) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\IdeaProjects\\mini_Proyecto_2\\src\\jugadores.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[0].equals(nombreJugador)) {
                    nivel = Integer.parseInt(partes[1]);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar jugador: " + e.getMessage());
        }
    }
    /**
     * Guarda la información del jugador en un archivo de texto.
     */
    public void guardarJugador() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\IdeaProjects\\mini_Proyecto_2\\src\\jugadores.txt"));
            String linea;
            StringBuilder sb = new StringBuilder();
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[0].equals(jugador)) {
                    sb.append(jugador + "," + nivel + "\n");
                } else {
                    sb.append(linea + "\n");
                }
            }
            System.out.println(sb.toString()); // Imprime el contenido del objeto sb
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\IdeaProjects\\mini_Proyecto_2\\src\\jugadores.txt"));
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar jugador: " + e.getMessage());
        }
    }
    /**
     * Carga la información del nivel actual del juego.
     */
    public void cargarNivel() {
        switch (nivel) {
            case 0:
                palabrasAMemorizar = 10;
                palabrasDelNivel = 20;
                porcentajeAciertos = 70;
                break;
            case 1:
                palabrasAMemorizar = 20;
                palabrasDelNivel = 40;
                porcentajeAciertos = 70;
                break;
            case 2:
                palabrasAMemorizar = 25;
                palabrasDelNivel = 50;
                porcentajeAciertos = 75;
                break;
            case 3:
                palabrasAMemorizar = 30;
                palabrasDelNivel = 60;
                porcentajeAciertos = 80;
                break;
            case 4:
                palabrasAMemorizar = 35;
                palabrasDelNivel = 70;
                porcentajeAciertos = 80;
                break;
            case 5:
                palabrasAMemorizar = 40;
                palabrasDelNivel = 80;
                porcentajeAciertos = 85;
                break;
            case 6:
                palabrasAMemorizar = 50;
                palabrasDelNivel = 100;
                porcentajeAciertos = 90;
                break;
            case 7:
                palabrasAMemorizar = 60;
                palabrasDelNivel = 120;
                porcentajeAciertos = 90;
                break;
            case 8:
                palabrasAMemorizar = 70;
                palabrasDelNivel = 140;
                porcentajeAciertos = 95;
                break;
            case 9:
                palabrasAMemorizar = 100;
                palabrasDelNivel = 200;
                porcentajeAciertos = 100;
                break;
        }
    }
    /**
     * Carga las palabras del juego desde un archivo de texto.
     */
    public void cargarPalabras() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\IdeaProjects\\mini_Proyecto_2\\src\\palabras.txt"));
            String linea;
            palabras = new ArrayList<String>();
            while ((linea = br.readLine()) != null) {
                palabras.add(linea);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar palabras: " + e.getMessage());
        }
    }
    /**
     * Selecciona las palabras del nivel actual del juego.
     */
    public void seleccionarPalabrasNivel() {
        Collections.shuffle(palabras);
        palabrasNivel = new ArrayList<String>(palabras.subList(0, palabrasDelNivel));
    }
    /**
     * Devuelve el nombre del jugador.
     *
     * @return El nombre del jugador.
     */
    public String getJugador() {
        return jugador;
    }
    /**
     * Devuelve el nivel actual del juego.
     *
     * @return El nivel actual del juego.
     */
    public int getNivel() {
        return nivel;
    }
    /**
     * Devuelve el número de palabras a memorizar en el nivel actual del juego.
     *
     * @return El número de palabras a memorizar en el nivel actual del juego.
     */
    public int getPalabrasAMemorizar() {
        return palabrasAMemorizar;
    }
    public ArrayList<String> getPalabrasNivel() {
        return palabrasNivel;
    }
    public void error() {
    }
    public void acierto() {
    }
    public boolean nivelSuperado() {
        return false;
    }
}

