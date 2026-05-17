import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
	    Scanner sc= new Scanner(System.in);
	    
		System.out.println("Ingrese el número de reglas a registrar");
		int n= sc.nextInt();
		sc.nextLine(); // <- consumir el Enter pendiente
		String[] entrada= new String[n];
		
		for(int i=0; i<n; i++){
		    System.out.println("Ingrese la regla "+ (i+1));
		    entrada [i]=sc.nextLine();
		}
		
		for(int i=0; i<n; i++){
		    System.out.println("rule r"+ (i+1)+":");
		    System.out.println(entrada[i]);
		}
		
		AnalisisLexico Token = new AnalisisLexico();
		
	}

}

