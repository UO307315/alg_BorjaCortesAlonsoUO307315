package p0;
import java.util.ArrayList;

public class JavaA3 {

    // Algoritmo A3: verifica divisores hasta m/2 y solo revisa impares
    public static boolean primoA3(int m) {
        if (m == 2) return true; // 2 es primo
        if (m % 2 == 0) return false; // descarta pares
        for (int i = 2; i <= m / 2; i++) {
            if (m % i == 0) return false;
        }
        return true;
    }

    // Listado de primos hasta n
    public static ArrayList<Integer> listadoPrimos(int n) {
        ArrayList<Integer> lSal = new ArrayList<>();
        lSal.add(2); // agregamos el primer primo
        int contPrimos = 1;

        for (int i = 3; i <= n; i += 2) { // solo números impares
            if (primoA3(i)) {
                lSal.add(i);
                contPrimos++;
            }
        }

        System.out.println("Hasta " + n + " hay " + contPrimos + " primos");
        return lSal;
    }

    public static void main(String[] args) {
        System.out.println("TIEMPOS DEL ALGORITMO A3");

        int n = 5000;
        for (int casos = 0; casos < 8; casos++) {
            long t1 = System.currentTimeMillis(); // tiempo inicial en ms
            ArrayList<Integer> lPrimos = listadoPrimos(n);
            long t2 = System.currentTimeMillis(); // tiempo final en ms

            System.out.println("n=" + n + " *** tiempo = " + (t2 - t1) + " milisegundos\n");

            n *= 2;
        }
    }
}
