package p5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ferry
{
	  private int  boatLength; //Longitud de los carriles del barco
	  private  List<Integer> vehicles; //lista de vehiculos
	  private boolean [][] dp;// matriz con las psoibles soluciones
	  private int [] sumatorio; //suma acumulada de las longitudes de los vehiculos


	public Ferry(int boatLength, List<Integer> vehicles){

		this.boatLength=boatLength;
		this.vehicles = vehicles;
		this.dp= new boolean[vehicles.size()+1][boatLength+1];
		this.sumatorio= new int[vehicles.size()+1];

		this.sumatorio[0]=0;
		for(int i=1;i <= vehicles.size();i++){
			this.sumatorio[i]= sumatorio[i-1] + vehicles.get(i-1);
		}
	} 


	public void run(String file){
		loadData(file);
		int n = vehicles.size();
    	dp = new boolean[n + 1][boatLength + 1];  
    	sumatorio = new int[n + 1];                

    	sumatorio[0] = 0;
    	for (int i = 1; i <= n; i++) {
        	sumatorio[i] = sumatorio[i - 1] + vehicles.get(i - 1);
    	}
		this.dp[0][0] = true;
		for(int i=1;i <= vehicles.size();i++){
			for(int l=boatLength; l >=0; l--){
				if(!dp[i-1][l]){
					continue; 
				}

				//meter coche en babor
				if((l+vehicles.get(i-1))<=boatLength){
					dp[i][l +vehicles.get(i-1)] = true;
				}

				//meter coche en estribor
				if((sumatorio[i]-l)<=boatLength){
					dp[i][l]=true;
				}
			}
		}
	}

	public int maxVehicles() {
		for (int i = vehicles.size(); i >= 0; i--) {
			for (int l = 0; l <= boatLength; l++) {
				if (dp[i][l]) {
					return i;
				}
			}
		}
		return 0;
	}




	private void loadData(String file) {
		this.vehicles = new ArrayList<Integer>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			this.boatLength = Integer.valueOf(reader.readLine());

			String line;
			while ((line = reader.readLine()) != null) {
				for (String s : line.split(" ")) {
					if (!s.trim().equals("")) {
						this.vehicles.add(Integer.valueOf(s));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void printData() {
    	int n = vehicles.size();
    	int maxV = maxVehicles();
    	System.out.println("Han llegado un total de " + vehicles.size() + " vehículos (" + maxV + " viajarán).");
    	System.out.println();

    	int lastRow = 0;
    	for (int i = 0; i <= n; i++) {
        	boolean anyTrue = false;
        	for (int l = 0; l <= boatLength; l++) {
            	if (dp[i][l]) {
                	anyTrue = true;
                	break;
            	}
        	}
        	if (anyTrue) lastRow = i;
    	}

    	System.out.println("Tabla con los cálculos realizados:");
    	System.out.print(" V/L ");
    	for (int l = 0; l <= boatLength; l++) System.out.print(l + " ");
    		System.out.println();

    	for (int i = 0; i <= lastRow; i++) {
       	 	System.out.print("   " + i + " ");
        	for (int l = 0; l <= boatLength; l++) {
            	System.out.print(dp[i][l] ? "T " : "F ");
        	}
        	System.out.println();
    	}
    	System.out.println();

    	int l = 0;
    	for (int i = 0; i <= boatLength; i++) {
        	if (dp[maxV][i]) {
            	l = i;
            	break;
        	}
    	}

    	List<String> asignacion = new ArrayList<>();
    	int baborUsed = 0;
    	int estriborUsed = 0;

    	for (int i = maxV; i >= 1; i--) {
        	int v = vehicles.get(i - 1);
        	if (l >= v && dp[i - 1][l - v]) {
            	asignacion.add("Vehículo " + i + " (longitud " + v + ") a babor.");
            	l -= v;
            	baborUsed += v;
        	} else {
            	asignacion.add("Vehículo " + i + " (longitud " + v + ") a estribor.");
            	estriborUsed += v;
        	}
    	}

    	for (int i = asignacion.size() - 1; i >= 0; i--) {
        	System.out.println(asignacion.get(i));
    	}
    	System.out.println("Ocupación final: Babor " + baborUsed + "m / Estribor " + estriborUsed + "m (válido <= " + boatLength + ").");
	}


	public static void main(String[] args) {
		Ferry f = new Ferry(0, new ArrayList<Integer>());
		f.run(args[0]);
		f.printData();
	}
}

