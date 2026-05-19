public class AST{
    String nombreRegla;
    String condicion;
    String accion;
    boolean ejecutado= false;
    
    public AST(String nombreRegla,String condicion,String accion){
        
        this.nombreRegla = nombreRegla;
        this.condicion = condicion;
        this.accion = accion;
    }
}
