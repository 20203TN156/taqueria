import dao.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.ConnectionMySQL.getConnection;

public class Sesion {
    Scanner sc = new Scanner(System.in);
    MetodosGerente metodosGerente = new MetodosGerente();
    MetodosCliente metodosCliente = new MetodosCliente();
    String usuario, contrasenia;

    Persona persona;
    public void inicioSesion() throws SQLException {


        System.out.println("****INICIO DE SESIÓN****");
        System.out.print("Usuario: ");
        usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        contrasenia = sc.nextLine();

        List<String> itemList = new ArrayList<>();

        Statement smt = null;
        try {
            smt = getConnection().createStatement();

            String q = "SELECT id_persona, nombre, tel, rol FROM persona where usuario = '" + usuario + "' and contrasenia = '" + contrasenia + "'";

            ResultSet rs = smt.executeQuery(q);

            if (rs.next()) {
                do {
                    /*id_persona */
                    itemList.add(rs.getString(1));
                    /*nombre*/
                    itemList.add(rs.getString(2));
                    /*tel*/
                    itemList.add(rs.getString(3));
                    /*rol*/
                    itemList.add(rs.getString(4));
                } while (rs.next());
                if (itemList.get(3).equals("0")) {
                    metodosGerente.rolGerente();
                    //persona = new Persona(itemList.get(0), usuario, contrasenia);
                    persona.setId_persona(itemList.get(0));
                    //persona = new Persona(itemList.get(0), itemList.get(1), itemList.get(2), itemList.get(3));
                } else if (itemList.get(3).equals("1")) {
                    //rolEmpleado();
                } else {
                    metodosCliente.rolCliente(itemList.get(0));
                }
                //System.out.println(itemList);
            } else {
                System.out.println("Usuario o contraseña incorrecta...");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        smt.close();
    }


}



