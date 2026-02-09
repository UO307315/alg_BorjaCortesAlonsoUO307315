package p0;
import java.util.ArrayList;

public class JavaA2 {

    public static boolean primoA2(int m) {
        for (int i = 2; i < m; i++) {
            if (m % i == 0) return false;
        }
        return true;
    }

    public static void listadoPrimos(int n) {
        ArrayList<Integer> lista = new ArrayList<>();
        int contPrimos = 0;
        for (int i = 2; i <= n; i++) {
            if (primoA2(i)) {
                lista.add(i);
                contPrimos++;
            }
        }
        System.out.println("Hay " + contPrimos + " primos hasta " + n);
        // System.out.println(lista);
    }

    public static void main(String[] args) {
        System.out.println("TIEMPO EN JAVA DEL ALGORITMO A2");
        long t1, t2;

        int n = 5000;
        for (int casos = 0; casos < 8; casos++) {
            t1 = System.currentTimeMillis();
            listadoPrimos(n);
            t2 = System.currentTimeMillis();

            System.out.println(t1 + " /// " + t2);
            System.out.println("n=" + n + " **** tiempo = " + (t2 - t1) + " milisegundos\n");

            n *= 2;
        }
    }
}
