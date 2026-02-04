package p0;
import java.util.ArrayList;

public class JavaA4 {

    // Algoritmo A4: criba de Eratóstenes
    public static ArrayList<Integer> primosA4(int n) {
        boolean[] listaNumeros = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            listaNumeros[i] = true;
        }

        int x = 2;
        while (x * x <= n) {
            if (listaNumeros[x]) {
                int paso = 2 * x;
                while (paso <= n) {
                    listaNumeros[paso] = false;
                    paso += x;
                }
            }
            x++;
        }

        ArrayList<Integer> lSal = new ArrayList<>();
        int contPrimos = 0;
        for (int i = 2; i <= n; i++) {
            if (listaNumeros[i]) {
                lSal.add(i);
                contPrimos++;
            }
        }

        System.out.println("Hasta " + n + " hay " + contPrimos + " primos");
        return lSal;
    }

    public static void main(String[] args) {
        System.out.println("TIEMPOS DEL ALGORITMO A4");

        int n = 5000;
        for (int casos = 0; casos < 15; casos++) {
            long t1 = System.currentTimeMillis(); // tiempo inicial
            ArrayList<Integer> lPrimos = primosA4(n);
            long t2 = System.currentTimeMillis(); // tiempo final

            System.out.println("n=" + n + " *** tiempo = " + (t2 - t1) + " milisegundos\n");
            n *= 2;
        }
    }
}

