import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
public class Interprete{
    
    Map<String,Integer> variable=new HashMap<>();
    Set<String> hechos=new TreeSet<>();
    int real1=0;
    int real2=0;
    AnalisisLexico lexe = new AnalisisLexico();
    
    public Interprete(){
    }
    
    public void agregarMap(String izqd, Integer der){
        variable.put(izqd,der);
    }
    
    public Interprete(AST arbol){
        int num= Integer.parseInt(arbol.condicion);
        variable.put(arbol.nombreRegla,num);
        
    }

    public void evaluar(AST arbol){
        String variable= arbol.nombreRegla;
        String condicion= arbol.condicion;
        String accion= arbol.accion;
        AnalisisSemantico(variable, condicion, accion, arbol);
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
                return resultado;
            case "<=":
                resultado=real1 <= real2;
                return resultado;
            case "==":
                resultado=real1 == real2;
                return resultado;
            case "<":
                resultado=real1 < real2;
                return resultado;
            case ">":
                resultado=real1 > real2;
                return resultado; 
            default:
                return false;
        }
        
            
    }
    
    public String ejecutarAccion(String accion,AST arbol){
        hechos.add(accion);
        arbol.ejecutado=true;
        return accion;
        
    }
    
    
    public void AnalisisSemantico(String vari, String con, String acc, AST arbolito){
        boolean r=false;
        if(!lexe.esIdentificador(vari) || vari.isEmpty() || con.isEmpty() || acc.isEmpty() ){
            System.out.println("Error Semántico");
        }
        else{
            
            if (con.contains(">=")){
                String operador= ">=";
                r=operadores(operador, con, acc);
                if(r){
                    ejecutarAccion(acc, arbolito);
                }
            }else if(con.contains("<=")){
                String operador= "<=";
                r= operadores(operador, con, acc);
                if(r){
                    ejecutarAccion(acc, arbolito);
                }
            }else if(con.contains("==")){
                String operador= "==";
                r= operadores(operador, con, acc);
                if(r){
                    ejecutarAccion(acc, arbolito);
                }
            }else if(con.contains("AND")){
                String operador= "AND";
                String[] estruc_con=con.split(operador);
                String parte1=estruc_con[0].trim();
                String parte2=estruc_con[1].trim();
                
                int verdadero =0;
                boolean r1=false;
                boolean r2=false;
                
                if (parte1.contains(">=")){
                    operador= ">=";
                    r1=operadores(operador, parte1, acc);
                }else if(parte1.contains("<=")){
                    operador= "<=";
                    r1=operadores(operador, parte1, acc);
                }else if(parte1.contains("==")){
                    operador= "==";
                    r1=operadores(operador, parte1, acc);
                }else if(parte1.contains("<")){
                    operador= "<";
                    r1=operadores(operador, parte1, acc);
                }else if(parte1.contains("=")){
                    operador= "=";
                    r1=operadores(operador, parte1, acc);
                }else if(parte1.contains(">")){
                    operador= ">";
                    r1=operadores(operador, parte1, acc);
                } else{
                    System.out.println("Error Semántico");
                }
                
                if (parte2.contains(">=")){
                    operador= ">=";
                    r2=operadores(operador, parte2, acc);
                }else if(parte2.contains("<=")){
                    operador= "<=";
                    r2=operadores(operador, parte2, acc);
                }else if(parte2.contains("==")){
                    operador= "==";
                    r2=operadores(operador, parte2, acc);
                }else if(parte2.contains("<")){
                    operador= "<";
                    r2=operadores(operador, parte2, acc);
                }else if(parte2.contains("=")){
                    operador= "=";
                    r2=operadores(operador, parte2, acc);
                }else if(parte2.contains(">")){
                    operador= ">";
                    r2=operadores(operador, parte2, acc);
                } else{
                    System.out.println("Error Semántico");
                }
                
                if(r1 && r2){
                    ejecutarAccion(acc, arbolito);
                }
               
            }else if(con.contains("=")){
                String operador= "=";
                r= operadores(operador, con, acc);
                if(r){
                    ejecutarAccion(acc, arbolito);
                }
            }else if(con.contains(">")){
                String operador= ">";
                r=operadores(operador, con, acc);
                if(r){
                    ejecutarAccion(acc, arbolito);
                }
            }else if(con.contains("<")){
                String operador= "<";
                r=operadores(operador, con, acc);
                if(r){
                    ejecutarAccion(acc, arbolito);
                }
            }else if (hechos.contains(con.trim())){
                ejecutarAccion(acc, arbolito);
            }else{
                
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
