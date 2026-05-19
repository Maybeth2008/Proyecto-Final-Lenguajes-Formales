import java.util.Scanner;
import java.util.ArrayList;

public class Main{
	public static void main(String[] args) {
	    Scanner sc= new Scanner(System.in);
	    
		System.out.println("Ingrese el número de variables a definir");
		int v= sc.nextInt();	    
		String[] varia= new String[v];
		
		System.out.println("Ingrese el número de reglas a registrar");
		int n= sc.nextInt();
		String[] entrada= new String[n];
		sc.nextLine(); // <- consumir el Enter pendiente

		
		
		Interprete interprete= new Interprete();
		
		for(int j=0; j<v; j++){
		    System.out.println("Ingrese la variable "+ (j+1));
		    String vax=varia [j]=sc.nextLine();
		    String[] partes = vax.split("=");
		    String izquierda= partes[0].trim();
		    String derecha= partes[1].trim();
		    int nume= Integer.parseInt(derecha);
		    interprete.agregarMap(izquierda,nume);
		}
		

		for(int i=0; i<n; i++){
		    System.out.println("Ingrese la regla "+ (i+1));
		    entrada [i]=sc.nextLine();
		    String [] reglaActual ={ entrada[i] };
		    
    		AnalisisLexico regla = new AnalisisLexico();
    		ArrayList<Token> tokens = regla.lexema(reglaActual);
    		for(Token t : tokens){System.out.println(t.tipo + " -> " + t.valor);
}
    		AnalisisSintactico parser = new AnalisisSintactico(tokens);
    		AST arbol=parser.Rule();    
    		interprete.evaluar(arbol);
		}
		
        
		
	}

}

