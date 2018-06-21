package modelo;

public class Filtro {
     private int id;
    private String  nombres;
    private String apellidos;
    private int AFP;
    private int edad;
    private String profesion;
    private boolean estado;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAFP() {
        return AFP;
    }

    public void setCarnet(int AFP) {
        this.AFP = AFP;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setUniversidad(String profesion) {
        this.profesion = profesion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
        
    
    public Filtro(){}
    public Filtro(int id, int AFP, String nombre, String apellido, int edad, String profesion, boolean estado){
        this.id = id;
        this.AFP = AFP;
        this.nombres = nombre;
        this.edad = edad;
        this.estado = estado;
        this.apellidos = apellido;
        this.profesion = profesion;
    }
    
    public Filtro(String nombre, String apellido, String profesion, int edad, boolean estado, int AFP){
        this.AFP = AFP;
        this.nombres = nombre;
        this.edad = edad;
        this.estado = estado;
        this.apellidos = apellido;
        this.profesion = profesion;
    }
    
    
}
