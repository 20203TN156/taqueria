package dao;

public class Persona {
    String id_persona;
    String usuario;
    String contrasenia;
    String nombre;
    String tel;
    String rol;

    public Persona() {
    }

    public Persona(String id_persona, String usuario, String contrasenia, String nombre, String tel, String rol) {
        this.id_persona = id_persona;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.tel = tel;
        this.rol = rol;
    }

    public Persona(String id_persona, String nombre, String tel, String rol) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.tel = tel;
        this.rol = rol;
    }

    public Persona(String id_persona, String usuario, String contrasenia) {
        this.id_persona = id_persona;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String s) {
        this.id_persona = id_persona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
