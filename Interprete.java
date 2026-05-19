import java.util.HashMap;
import java.util.Map;
public class Interprete{
    
    Map<String,Integer> variable=new HashMap<>();
    int real1=0;
    int real2=0;
    AnalisisLexico lexe = new AnalisisLexico();
    
    public void agregarMap(String izqd, Integer der){
        variable.put(izqd,der);
    }
    
    public Interprete(AST arbol){
        int num= Integer.parseInt(arbol.condicion);
        variable.put(arbol.nombreRegla,num);
        
    }
    
    public Interprete(){
    }

    public void evaluar(AST arbol){
        String variable= arbol.nombreRegla;
        String condicion= arbol.condicion;
        String accion= arbol.accion;
        AnalisisSemantico(variable, condicion, accion);
    }
    
    public boolean ejecutar(String NodoIzq, String NodoDerecha, String operador, String acc){
        
        if(lexe.esIdentificador(NodoIzq)){
            real1=variable.get(NodoIzq);
            
        }else{
            int num1= Integer.parseInt(NodoIzq);
            real1=num1;
            
        }
        if(lexe.esIdentificador(NodoDerecha)){
            real2=variable.get(NodoDerecha);
            
        }else{
            int num2= Integer.parseInt(NodoDerecha);
            real2=num2;
            
        }
        
        boolean resultado;
        switch(operador){
            case ">=":
                resultado=real1 >= real2;
                if(resultado==true){
                    ejecutarAccion(acc);
                }
                return resultado;
            case "<=":
                resultado=real1 <= real2;
                if(resultado==true){
                    ejecutarAccion(acc);
                }
                return resultado;
            case "==":
                resultado=real1 == real2;
                if(resultado==true){
                    ejecutarAccion(acc);
                }
                return resultado;
            case "<":
                resultado=real1 < real2;
                if(resultado==true){
                    ejecutarAccion(acc);
                }
                return resultado;
            case ">":
                resultado=real1 > real2;
                if(resultado==true){
                    ejecutarAccion(acc);
                }
                return resultado; 
            default:
                return false;
        }
        
            
    }
    
    public String ejecutarAccion(String accion){
        int activo=1;
        variable.put(accion, activo);
        return accion;
    }
    
    
    public void AnalisisSemantico(String vari, String con, String acc){
        if(!lexe.esIdentificador(vari) || vari.isEmpty() || con.isEmpty() || acc.isEmpty() ){
            System.out.println("Error Semántico");
        }
        else{
            
            if (con.contains(">=")){
                String operador= ">=";
                operadores(operador, con, acc);
            }else if(con.contains("<=")){
                String operador= "<=";
                operadores(operador, con, acc);
            }else if(con.contains("==")){
                String operador= "==";
                operadores(operador, con, acc);
            }else if(con.contains("AND")){
                String operador= "AND";
                String[] estruc_con=con.split(operador);
                int verdadero =0;
                for(int i=0; i< estruc_con.length; i++){
                    int result=variable.get(estruc_con[i].trim());
                    if(result==1)
                    verdadero ++;
                }
                if(verdadero == estruc_con.length){
                    ejecutarAccion(acc);
                }
            }else if(con.contains("=")){
                String operador= "=";
                operadores(operador, con, acc);
            }else if(con.contains(">")){
                String operador= ">";
                operadores(operador, con, acc);
            }else if(con.contains("<")){
                String operador= "<";
                operadores(operador, con, acc);
            }else{
                System.out.println("Error Semántico");
            }
        }
        
    }
    
    public boolean operadores( String opr, String cond, String accion){
        String[] estruc_con=cond.split(opr); 
        String NodoIzq = estruc_con[0].trim();
        String NodoDerecha= estruc_con[1].trim();
        
        if( lexe.esIdentificador(NodoIzq) || lexe.esNumero(NodoIzq)){
            if(lexe.esIdentificador(NodoDerecha) || lexe.esNumero(NodoDerecha)){
                return ejecutar(NodoIzq,NodoDerecha,opr, accion);
            }else{
                return false;
            }
        }else{
                return false;
            }
        
    }
    
}
