
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlmacenajeContenedores{
    private int capacidadC;
    private Integer[] conjuntoS;
    private int mejorK;//Numero minimo de contenedores


    private List<List<Integer>> mejorDistribucion;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(args[0]));
        int c = sc.nextInt();
        String [] parts = sc.nextLine().split(" " );

        Integer[] toS = new Integer[parts.length];

        for (int i = 0; i < parts.length; i++) {
            toS[i] = Integer.valueOf(parts[i]);
        }
       new AlmacenajeContenedores(c,toS).resolver();
    }

    public void resolver() {
        //Backtraking
        backtraking(0, new ArrayList);


        //mostrarSolucion
    }


    public AlmacenajeContenedores(int c, Integer[] s) {
        this.capacidadC = c;
        this.conjuntoS = s;

        this.mejorK = conjuntoS.length;
    }

    private void backtraking(int indexObject,List<List<Integer>> contenedores){
        //Caso base
        if (indexObject == conjuntoS.length) {
            if (contenedores.size() < mejorK) {
                mejorK = contenedores.size();
                mejorDistribucion = copiar(contenedores); 
            }
            return;
        }

        //Podamos: si el size de contenedores > mejor PARAMOS
        if (contenedores.size() >= mejorK){
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
}