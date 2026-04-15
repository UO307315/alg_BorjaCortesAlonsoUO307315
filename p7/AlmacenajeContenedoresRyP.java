
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AlmacenajeContenedoresRyP{
    private int capacidadC;
    private Integer[] conjuntoS;
    private int mejorK;//Numero minimo de contenedores

    private int llamadasRecursivas;
    private List<List<Integer>> mejorDistribucion;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Debes pasar el nombre del archivo como argumento.");
            return;
        }

        try (Scanner sc = new Scanner(new File(args[0]))) {
            if (!sc.hasNextInt()) return;
            int c = sc.nextInt(); // Capacidad C [cite: 29]
            
            List<Integer> listaObjetos = new ArrayList<>();
            while (sc.hasNextInt()) {
                listaObjetos.add(sc.nextInt()); // Tamaños de objetos [cite: 30]
            }
            
            Integer[] toS = listaObjetos.toArray(new Integer[0]);
            new AlmacenajeContenedoresRyP(c, toS).resolver();
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado: " + args[0]);
        }
    }

    public void resolver() {
        //Backtraking
        backtraking(0, new ArrayList<>());
        //mostrarSolucion
        mostrarSolucion();
    }
    public void resolverSinImprimir() {
        //Backtraking
        backtraking(0, new ArrayList<>());
    }


    public AlmacenajeContenedoresRyP(int c, Integer[] s) {
        this.capacidadC = c;
        Arrays.sort(s, Collections.reverseOrder());
        this.conjuntoS = s;

        this.mejorK = conjuntoS.length +1;
        this.mejorDistribucion = null;
    }

    private void backtraking(int indexObject,List<List<Integer>> contenedores){
        llamadasRecursivas++;
        //Caso base
        if (indexObject == conjuntoS.length) {
            if (contenedores.size() < mejorK) {
                mejorK = contenedores.size();
                mejorDistribucion = copiar(contenedores); 
            }
            return;
        }

        //Podamos:
        int pesoRestante = 0;
        for (int i = indexObject; i < conjuntoS.length; i++) {
            pesoRestante += conjuntoS[i];
        }
        int cota = contenedores.size()
                + (int) Math.ceil((double) pesoRestante / capacidadC);

        if (cota >= mejorK) {
            return;
        }
        

        //probar a meter en contenedores existentes
        for(int i=0;i< contenedores.size();i++){
            if(sum(contenedores.get(i)) + conjuntoS[indexObject] <= capacidadC){
                //Avanzar
                contenedores.get(i).add(conjuntoS[indexObject]);
                backtraking(indexObject+1,contenedores);
                //Retroceder
                contenedores.get(i).remove(contenedores.get(i).size() - 1);
            }
        }
        //Intentar meterlo en un nuevo contenedor
        List<Integer> nuevoContenedor = new ArrayList<Integer>();
        nuevoContenedor.add(conjuntoS[indexObject]);
        contenedores.add(nuevoContenedor);

        //Avanzo
        backtraking(indexObject+1,contenedores);

        //Retrocede
        contenedores.remove(contenedores.size() - 1);


    }

    private List<List<Integer>> copiar(List<List<Integer>> contenedores) {
        List<List<Integer>> copia = new ArrayList<>();
    
        for (List<Integer> c : contenedores) {
            copia.add(new ArrayList<>(c));
        }
        return copia;
    }

    private Integer sum(List<Integer> lista) {
        Integer contador=0;
        for(int i=0;i<lista.size();i++){
            contador += lista.get(i);
        }
        return contador;
    }

    private void mostrarSolucion() {
        if (mejorDistribucion != null) {
            System.out.println("Lista de contenedores y objetos contenidos:");
            for (int i = 0; i < mejorDistribucion.size(); i++) {
                System.out.print("Contenedor " + (i + 1) + ": ");
                for (Integer objeto : mejorDistribucion.get(i)) {
                    System.out.print(objeto + " ");
                }
                System.out.println();
            }
            System.out.println("El número de contenedores necesario es " + mejorK + ".");
            System.out.println("El número de llamadas recursivas es " + llamadasRecursivas + ".");
        }
    }
}