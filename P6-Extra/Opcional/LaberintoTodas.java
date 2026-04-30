import java.io.File;
import java.util.Scanner;

public class LaberintoTodas {

    private int[][] laberinto;
    private int[][] mejorSolucion;
    private int minPasos = Integer.MAX_VALUE;
    private int totalSoluciones = 0;
    private final int FILAS = 7;
    private final int COLUMNAS = 7;

    // Array de movimientos posibles: 
    private final int[][] MOVIMIENTOS = {
        {1, 0},  // Abajo
        {-1, 0}, // Arriba
        {0, 1},  // Derecha
        {0, -1}  // Izquierda
    };

    public void resolver(String nombreFichero, int inicio, int fin) {
        cargarLaberinto(nombreFichero);
        
        System.out.println("EL LABERINTO ES INICIALMENTE DEL SIGUIENTE MODO:");
        imprimirLaberinto(laberinto);
        System.out.println("\nEl objetivo es ir desde la posición " + inicio + " a la posición " + fin + "\n");

        int filaI = inicio / COLUMNAS;
        int colI = inicio % COLUMNAS;
        int filaF = fin / COLUMNAS;
        int colF = fin % COLUMNAS;

        // Iniciamos el algoritmo de backtracking
        backtracking(filaI, colI, filaF, colF, 0);

        if (totalSoluciones > 0) {
            System.out.println("LA MEJOR SOLUCIÓN TIENE " + minPasos + " PASOS, HABIENDO UN TOTAL DE " + totalSoluciones + " SOLUCIONES DIFERENTES");
            imprimirLaberintoFormateado(mejorSolucion);
        } else {
            System.out.println("No se encontró ninguna salida al laberinto.");
        }
    }

    private void backtracking(int r, int c, int dr, int dc, int pasosActuales) {
        laberinto[r][c] = 2;
        pasosActuales++;

        // CASO BASE
        if (r == dr && c == dc) {
            totalSoluciones++;
            System.out.println("SOLUCIÓN ENCONTRADA CON " + pasosActuales + " PASOS");
            imprimirLaberinto(laberinto);
            
            if (pasosActuales < minPasos) {
                minPasos = pasosActuales;
                mejorSolucion = copiarMatriz(laberinto);
            }
        } else {
            // EXPLORACIÓN: Usamos el array de movimientos posibles
            for (int[] mov : MOVIMIENTOS) {
                int nuevaFila = r + mov[0];
                int nuevaCol = c + mov[1];

                // Comprobamos si el movimiento es válido (dentro de límites y es camino libre '0')
                if (esValido(nuevaFila, nuevaCol)) {
                    backtracking(nuevaFila, nuevaCol, dr, dc, pasosActuales);
                }
            }
        }

        // BACKTRACKING
        laberinto[r][c] = 0;
    }

    private boolean esValido(int r, int c) {
        return (r >= 0 && r < FILAS && c >= 0 && c < COLUMNAS && laberinto[r][c] == 0);
    }

    private void cargarLaberinto(String fichero) {
        laberinto = new int[FILAS][COLUMNAS];
        try (Scanner sc = new Scanner(new File(fichero))) {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    if (sc.hasNextInt()) laberinto[i][j] = sc.nextInt();
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar el fichero: " + e.getMessage());
            System.exit(1);
        }
    }

    private void imprimirLaberinto(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int celda : fila) {
                System.out.print(" " + celda + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void imprimirLaberintoFormateado(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int celda : fila) {
                switch (celda) {
                    case 0 -> System.out.print(" · ");
                    case 1 -> System.out.print(" H ");
                    case 2 -> System.out.print(" * ");
                }
            }
            System.out.println();
        }
    }

    private int[][] copiarMatriz(int[][] original) {
        int[][] copia = new int[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, COLUMNAS);
        }
        return copia;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: LaberintoTodas <nombre_fichero_sin_txt> <inicio> <fin>");
            return;
        }

        String archivo = args[0] + ".txt";
        int inicio = Integer.parseInt(args[1]);
        int fin = Integer.parseInt(args[2]);

        new LaberintoTodas().resolver(archivo, inicio, fin);
    }
}
