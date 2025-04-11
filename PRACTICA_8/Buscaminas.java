package PRACTICA_8;


import java.util.Random;
import java.util.Scanner;

    public class Buscaminas {
        private final int FILAS = 8;
        private final int COLUMNAS = 8;
        private final int NUM_MINAS = 10;
        private int[][] tablero;
        private boolean[][] mostrado;
        private boolean juegoTerminado;

        public Buscaminas() {
            this.tablero = new int[FILAS][COLUMNAS];
            this.mostrado = new boolean[FILAS][COLUMNAS];
            colocarMinas();
            llenarPistas();
            this.juegoTerminado = false;
        }

        private void colocarMinas() {
            Random random = new Random();
            int minasColocadas = 0;
            while (minasColocadas < NUM_MINAS) {
                int fila = random.nextInt(FILAS);
                int columna = random.nextInt(COLUMNAS);
                if (tablero[fila][columna] != -1) {
                    tablero[fila][columna] = -1;
                    minasColocadas++;
                }
            }
        }

        private void llenarPistas() {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    if (tablero[i][j] == -1) continue;
                    int contador = 0;
                    for (int x = i - 1; x <= i + 1; x++) {
                        for (int y = j - 1; y <= j + 1; y++) {
                            if (x >= 0 && x < FILAS && y >= 0 && y < COLUMNAS && tablero[x][y] == -1) {
                                contador++;
                            }
                        }
                    }
                    tablero[i][j] = contador;
                }
            }
        }

        private void mostrarTablero() {
            System.out.println("\nTABLERO:");
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    String valor;
                    if (mostrado[i][j]) {
                        if (tablero[i][j] == -1) {
                            valor = "*";
                        } else if (tablero[i][j] == 0) {
                            valor = " ";
                        } else {
                            valor = String.valueOf(tablero[i][j]);
                        }
                    } else {
                        valor = "-";
                    }
                    System.out.print(String.format("%-3s", valor));
                }
                System.out.println();
            }
        }

        private void mostrarTableroFinal() {
            System.out.println("\nTABLERO:");
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    String valor;
                    if (mostrado[i][j]) {
                        if (tablero[i][j] == -1) {
                            valor = "*";
                        } else if (tablero[i][j] == 0) {
                            valor = " ";
                        } else {
                            valor = String.valueOf(tablero[i][j]);
                        }
                    } else {
                        valor = "-";
                    }
                    System.out.print(String.format("%-3s", valor));
                }
                System.out.println();
            }
        }

        private void revelarCelda(int fila, int columna) {
            if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS || mostrado[fila][columna]) {
                return;
            }

            mostrado[fila][columna] = true;

            if (tablero[fila][columna] == -1) {
                juegoTerminado = true;
                return;
            }

            if (tablero[fila][columna] == 0) {
                for (int i = fila - 1; i <= fila + 1; i++) {
                    for (int j = columna - 1; j <= columna + 1; j++) {
                        revelarCelda(i, j);
                    }
                }
            }
        }

        private boolean verificarGanador() {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    if (tablero[i][j] != -1 && !mostrado[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void jugar() {
            Scanner scanner = new Scanner(System.in);

            while (!juegoTerminado) {
                mostrarTablero();
                try {
                    System.out.print("Elige fila (0-7): ");
                    int fila = scanner.nextInt();
                    System.out.print("Elige columna (0-7): ");
                    int columna = scanner.nextInt();

                    if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
                        System.out.println("Entrada inválida. Intenta de nuevo.");
                        continue;
                    }

                    revelarCelda(fila, columna);

                    if (juegoTerminado) {
                        mostrarTodo();
                        System.out.println("\n¡GAME OVERR!Suerte para la proxima.");
                        break;
                    }

                    if (verificarGanador()) {
                        mostrarTodo();
                        System.out.println("\n¡En hora buena! Eres un ganador.");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida, intenta de nuevo.");
                    scanner.next();
                }
            }

            scanner.close();
        }

        private void mostrarTodo() {
            for (int i = 0; i < FILAS; i++) {
                for (int j = 0; j < COLUMNAS; j++) {
                    mostrado[i][j] = true;
                }
            }
            mostrarTableroFinal();
        }
    }

