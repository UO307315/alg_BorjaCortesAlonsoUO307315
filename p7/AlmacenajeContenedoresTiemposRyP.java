import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlmacenajeContenedoresTiemposRyP{

    public static void main(String[] args) throws Exception {
        long t1, t2;

        for (int i = 0; i <= 9; i++) {
            String nombreFichero = "test0" + i + ".txt";
            File f = new File(nombreFichero);
            
            if (!f.exists()) continue;

            Scanner sc = new Scanner(f);
            int capacidad = sc.nextInt();
            List<Integer> lista = new ArrayList<>();
            while (sc.hasNextInt()) {
                lista.add(sc.nextInt());
            }
            Integer[] objetos = lista.toArray(new Integer[0]);
            sc.close();

            AlmacenajeContenedoresRyP solver = new AlmacenajeContenedoresRyP(capacidad, objetos);
            t1 = System.currentTimeMillis();
            solver.resolverSinImprimir(); 
            t2 = System.currentTimeMillis();

            System.out.println("Fichero=" + nombreFichero + "\tTiempo=" + (t2 - t1) + " ms");
        }
    }
}