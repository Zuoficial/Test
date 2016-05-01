package smoowy.recycleviewrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class DataManager extends RealmObject {

    private String Nombre;
    @PrimaryKey
    private
    String Apellido;


    public DataManager() {
    }

    public DataManager(String nombre, String apellido) {
        Nombre = nombre;
        Apellido = apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }
}
