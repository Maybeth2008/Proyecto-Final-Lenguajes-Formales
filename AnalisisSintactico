import java.util.ArrayList;

public class AnalisisSintactico {
	int indice;
	ArrayList <Token> tokens;
	Token token_actual;

	public AnalisisSintactico(ArrayList <Token> tokens) {
		this.tokens = tokens;
		indice =0;
		token_actual = tokens.get(0);

	}

	public Token leer(String tokenEsperado) {
		if(token_actual.tipo.equals(tokenEsperado)) {
		    token_leido=token_actual;
			indice++;
			if(indice<tokens.size()) {
				token_actual = tokens.get(indice);
			}
		}
		else {
			System.out.println("Error Sintáctico\n");
			return null;
		}
		return token_leido;
	}

	public void Program() {
		RuleList();
	}

	public void RuleList() {

		if(token_actual.tipo.equals("RULE")) {
			Rule();
			RuleList();
		}
		else {
			//epsilon
		}
	}

	public AST Rule() {
		leer("RULE");
		Token nombre = leer("ID");
		leer("COLON");
		leer("IF");
		String condicion = Cond();
		leer("THEN");
		String accion = Action();

		AST arbol= new AST( nombre.valor, condicion,  accion);
		return arbol;
	}

	public String Cond() {
		String Condicion=Atom();
		String DobleCondicion=CondPrima();
		return Condicion + DobleCondicion;
	}

	public String CondPrima() {
		if(token_actual.tipo.equals("AND")) {
			Token nombre=leer("AND");
			String Condicion=Atom();
			String DobleCondicion=CondPrima();
			return nombre.valor+ Condicion + DobleCondicion;
		}
		else {
			//epsilon
		}
		return null;
	}

	public String Atom() {
		Token nombre=leer("ID");
		String Condicion=AtomPrima();
		return nombre.valor + Condicion;
	}

	public String AtomPrima() {
		if(token_actual.tipo.equals("GREATER") || token_actual.tipo.equals("LESS") || token_actual.tipo.equals("EQUAL")) {
			String Operador=RelOp();
			Token numero=leer("NUMBER");
			return Operador + numero.valor ;
		}
		else {
			//epsilon
		}
		return null;
	}


	public String RelOp() {
		if(token_actual.tipo.equals("GREATER")) {
			Token nombre=leer("GREATER");
			return nombre.valor;
		} else if(token_actual.tipo.equals("LESS")) {
			Token nombre=leer("LESS");
			return nombre.valor;
		} else if( token_actual.tipo.equals("EQUAL")) {
			Token nombre=leer("EQUAL");
			return nombre.valor;
		}
		return null;
	}

	public String Action() {
		Token nombre=leer("ID");
		return nombre.valor;
	}
}
