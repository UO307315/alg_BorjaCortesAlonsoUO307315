package algstudent.s4;

import java.util.*;

public class ColoreoGrafo {

    private static final String[] COLORES = {
        "red", "blue", "green", "yellow", "orange", 
        "purple", "cyan", "magenta", "lime"
    };
    
    public static Map<String, String> realizarVoraz(Map<String, List<String>> grafo) {
        Map<String, String> asignacionColores = new HashMap<>();
        List<String> nodos = new ArrayList<>(grafo.keySet());

        for (String nodo : nodos) {
            Set<String> coloresProhibidos = new HashSet<>();
            Object vecinosObj = grafo.get(nodo);
            if (vecinosObj instanceof List) {
                List<?> vecinos = (List<?>) vecinosObj;
                for (Object v : vecinos) {
                    String vecinoId = v.toString(); 
                    if (asignacionColores.containsKey(vecinoId)) {
                        coloresProhibidos.add(asignacionColores.get(vecinoId));
                    }
                }
            }
            
            for (String color : COLORES) {
                if (!coloresProhibidos.contains(color)) {
                    asignacionColores.put(nodo, color);
                    break;
                }
            }
        }
        return asignacionColores;
    }
}
