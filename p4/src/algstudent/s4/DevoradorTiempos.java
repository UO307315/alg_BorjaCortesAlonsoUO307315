package algstudent.s4;

import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class DevoradorTiempos {

    public static void main(String[] args) {
        int repeticiones = (args.length > 0) ? Integer.parseInt(args[0]) : 1;
        JSONParser parser = new JSONParser();

        System.out.println("n\tTiempo Total (ms)\tRepeticiones");
        for (int n = 8; n <= 65536; n *= 2) {
            long tAcumulado = 0;
            String rutaArchivo = "sols/g" + n + ".json";

            try (FileReader reader = new FileReader(rutaArchivo)) {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                Map<String, List<String>> grafo = (Map<String, List<String>>) jsonObject.get("grafo");
                for (int i = 0; i < repeticiones; i++) {
                    long t1 = System.currentTimeMillis();
                    ColoreoGrafo.realizarVoraz(grafo); 
                    long t2 = System.currentTimeMillis();
                    tAcumulado += (t2 - t1);
                }

                System.out.println(n + "\t" + tAcumulado + "\t\t\t" + repeticiones);

            } catch (Exception e) {
                System.err.println("Error con n=" + n + ": " + e.getMessage());
            }
        }
    }
}