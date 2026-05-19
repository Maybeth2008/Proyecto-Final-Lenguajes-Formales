import java.util.ArrayList;

public class AnalisisLexico{
    
    String lexema="";
    ArrayList <Token> Tokens = new ArrayList<>();
    
	public ArrayList<Token> lexema(String[] entrada){
	    for(int i=0; i<entrada.length; i++){
	        
	        String actual = entrada[i];
	        for(int j=0; j< actual.length(); j++){
	            
	            char simbolo = actual.charAt(j);
	            
	            if (simbolo != ' '){
	                
	                if(simbolo != '>' && simbolo != '<' && simbolo != '=' && simbolo != ':'){
	                    
    	                lexema += simbolo;
	                }
	                else{
	                    ProcesarLexema(lexema);
	                    lexema=String.valueOf(simbolo);
	                    ProcesarLexema(lexema); 
	                    lexema = "";
	                    
	                }
   
	            }
	            else if(simbolo ==' '){
	                 ProcesarLexema(lexema);
	                 lexema = "";
	            }
  
	        }
	        
	    }
	    if(!lexema.isEmpty()){
	        ProcesarLexema(lexema);
	    }
	    return Tokens;
	    
	}
	
	// funcion para procesar los lexemas
	public void ProcesarLexema(String lexe){
	    
        switch(lexe){
            case "rule":
                String tipo0 = "RULE";
                String valor0= "rule";
                Token TokenRule = new Token(tipo0, valor0);
                Tokens.add(TokenRule);
                break;
                
            case "if":
                String tipo1 = "IF";
                String valor1= "if";
                Token TokenIf = new Token(tipo1, valor1);
                Tokens.add(TokenIf);
                break;
                
            case "then":
                String tipo2= "THEN";
                String valor2= "then";
                Token TokenThen = new Token(tipo2, valor2);
                Tokens.add(TokenThen);
                break;
                
            case "AND":
                String tipo3 = "AND";
                String valor3= "AND";
                Token TokenAnd = new Token(tipo3, valor3);
                Tokens.add(TokenAnd);
                break;
                
            case ":":
                String tipo4 = "COLON";
                String valor4= ":";
                Token Token1 = new Token(tipo4, valor4);
                Tokens.add(Token1);
                break;
                
            case ">":
                String tipo5 = "GREATER";
                String valor5= ">";
                Token TokenGreater = new Token(tipo5, valor5);
                Tokens.add(TokenGreater);
                break;
                
            case "<":
                String tipo6= "LESS";
                String valor6= "<";
                Token TokenLess = new Token(tipo6, valor6);
                Tokens.add(TokenLess);
                break;
                
            case "=":
                String tipo7 = "EQUAL";
                String valor7= "=";
                Token TokenEqual = new Token(tipo7, valor7);
                Tokens.add(TokenEqual);
                break;
                
            default:
                if(esNumero(lexe)){
                    
                    String tipo8 = "NUMBER";
                    String valor8= lexe;
                    Token TokenNumber = new Token(tipo8, valor8); 
                    Tokens.add(TokenNumber);
                
                }
                else if(esIdentificador(lexe)){
                    
                    String tipo9 = "ID";
                    String valor9= lexe;
                    Token TokenId = new Token(tipo9, valor9); 
                    Tokens.add(TokenId);
                    
                }
            }
            lexema =    "";
	    
	} 
	 // funcion para identificador
    public boolean esIdentificador(String s) {

        int estado = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (estado) {

                case 0:
                    if (Character.isLetter(c)) {
                        estado = 1;
                    } else {
                        return false;
                    }
                    break;

                case 1:
                    if (Character.isLetter(c) || Character.isDigit(c) || c == '_') {
                        estado = 1;
                    } else {
                        return false;
                    }
                    break;
            }
        }

        return estado == 1;
    }
    
    // funcion para numeros
    public boolean esNumero(String s) {

        int estado = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (estado) {

                case 0:
                    if (Character.isDigit(c)) {
                        estado = 1;
                    } else {
                        return false;
                    }
                    break;

                case 1:
                    if (Character.isDigit(c) ){
                        estado = 1;
                    } else {
                        return false;
                    }
                    break;
            }
        }

        return estado == 1;
    }
	
}	                              
