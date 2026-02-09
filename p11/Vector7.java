package p11;


/** Esta clase utiliza los metodos estaticos de la clase Vector1.
 * Sirve para medir tiempos de la operacion suma, para ello:
 * Va incrementando autom�ticamente el n del problema y adem�s
 * segun una escala de tiempos determinada por repeticiones, 
 * que se proporciona como argumento en linea de comandos para la ejecucion
 */
public class Vector7
{
static int []v1;
static int []v2;

public static void main (String arg [] )
{
	int repeticiones = Integer.parseInt (arg[0]);	// veces que se repite la operacion

	long t1,t2;

	System.out.println("repeticiones = "+ repeticiones);
	System.out.println ("n\tTiempo");   
	for ( int n= 10000; n<= 81920000; n*=2) // n se va duplicando   
	{
		v1 = new int [n] ;
		v2 = new int [n] ;

		Vector1.rellena (v1);
		Vector1.rellena (v2);

		t1=System.currentTimeMillis();

		int s= 0;
		// hay que repetir todo el proceso a medir (lo que que estaba entre t1 y t2) 
		for (int r= 1; r<=repeticiones; r++)
		{  	
			s= Vector1.coincidencias2 (v1,v2);
		}

		t2=System.currentTimeMillis();
		System.out.println (n+"\t"+(t2-t1));   

	} // fin de for
		
	System.out.println("\nFin de la medicion de tiempos *****");

} // fin de main

} // fin de clase