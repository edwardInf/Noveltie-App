package noveltie.la.noveltie_app.modelo;

public class Conductores {
    private String nombres;
    private String apellidos;
    private String codigo;
    private String clave;

    public Conductores(String nombres, String apellidos, String codigo, String clave) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.codigo = codigo;
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getClave() {
        return clave;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
