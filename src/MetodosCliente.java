import dao.Alimento;
import dao.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.ConnectionMySQL.getConnection;

public class MetodosCliente {
    Scanner sc = new Scanner(System.in);
    Persona persona = new Persona();
    MetodosGerente metodosGerente = new MetodosGerente();
    List<String> bebidaList = new ArrayList<>();
    List<String> alimentoList = new ArrayList<>();
    List<String> personaList = new ArrayList<>();

    String menu, repetir;
    String id_platillo;
    String tamaño;
    String id_bebida;
    String id_alimento;
    //String id_persona;
    String id_ingrediente;

    public void rolCliente(String id_persona) throws SQLException {
        System.out.println("****MENÚ****");
        System.out.println("1. ORDENAR PLATILLO");
        System.out.println("2. VER ESTADO DE PEDIDOS ");
        menu = sc.nextLine();

        switch (menu) {
            case "1":
                ordenar(id_persona);
                break;
            case "2":
                verPedidos(id_persona);
                break;
        }

    }

    public void verPedidos(String id_persona) throws SQLException {

        List<String> platillo = new ArrayList<>();
        Statement smt = null;
        try {
            smt = getConnection().createStatement();


            String q = "select bebida.nombre, bebida.precio, alimento.nombre, tamaño, alimento.ingredientes, alimento.precio, persona.nombre, tel from platillo\n" +
                    "inner join  bebida on bebida.id_bebida = platillo.bebida_id_bebida inner join alimento on alimento.id_alimento = alimento_id_alimento\n" +
                    "inner join persona on persona.id_persona = platillo.persona_id_persona where persona_id_persona = '" + id_persona + "'";

            ResultSet rs = smt.executeQuery(q);

            if (rs.next()) {
                do {
                    platillo.add(rs.getString(1));
                    platillo.add(rs.getString(2));
                    platillo.add(rs.getString(3));
                    platillo.add(rs.getString(4));
                    platillo.add(rs.getString(5));
                    platillo.add(rs.getString(6));
                    platillo.add(rs.getString(7));

                } while (rs.next());

                platillo.forEach(System.out::println);
                //System.out.println(itemList);
            } else {
                System.out.println("...");
            }
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
        smt.close();
    }

    public void ordenar(String id_persona){
        try {
            Connection conn = getConnection();
            do {
                System.out.println("****AGREGAR ALIMENTO****");
                System.out.print("1. Mediano\n2. Grande\nTamaño: ");
                tamaño = sc.nextLine();
                metodosGerente.mostrarAlimentos();
                System.out.print("Alimento: ");
                id_alimento = sc.nextLine();
                metodosGerente.mostrarBebidas();
                System.out.print("Bebida: ");
                metodosGerente.mostrarBebidas();
                id_bebida = sc.nextLine();


                String query ="INSERT INTO platillo (tamaño, bebida_id_bebida, alimento_id_alimento, persona_id_persona) VALUES (?, ?, ?, ?);";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, tamaño);
                preparedStmt.setString(2, id_bebida);
                preparedStmt.setString(3, id_alimento);
                preparedStmt.setString(4, id_persona);
                //preparedStmt.setString(5, id_ingrediente);
                preparedStmt.execute();
                System.out.println("Ingresa 'r' para ordenar más alimentos y/o beidas; o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();
            } while (repetir.equals("r"));
            if (repetir != "r") {
                rolCliente(id_persona);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());

        }
    }

    /*public void ordenarPlatillo() throws SQLException {

        Statement smt = null, smt2 = null, smt3 = null;

        try {
            smt = getConnection().createStatement();
            smt2 = getConnection().createStatement();
            smt3 = getConnection().createStatement();

            String q = "SELECT * FROM bebida";
            String q2 = "SELECT * FROM alimento";
            String q3 = "SELECT id_persona, usuario, nombre, tel, rol FROM persona";

            ResultSet rs = smt.executeQuery(q);
            ResultSet rs2 = smt2.executeQuery(q2);
            ResultSet rs3 = smt3.executeQuery(q3);
            System.out.println("****BEBIDAS****");

            if (rs.next()) {
                do {
                    bebidaList.add(rs.getString(1));
                    bebidaList.add(rs.getString(2));
                    bebidaList.add(rs.getString(3));
                } while (rs.next());
                bebidaList.forEach(System.out::println);
            } else {
                System.out.println("...");
            }
            System.out.println("****ALIMENTOS****");

            if (rs2.next()) {
                do {
                    alimentoList.add(rs2.getString(1));
                    alimentoList.add(rs2.getString(2));
                    alimentoList.add(rs2.getString(3));
                    alimentoList.add(rs2.getString(4));
                } while (rs2.next());
                alimentoList.forEach(System.out::println);
            } else {
                System.out.println("...");
            }
            System.out.println("****PERSONA****");


            if (rs3.next()) {
                do {
                    personaList.add(rs3.getString(1));
                    personaList.add(rs3.getString(2));
                    personaList.add(rs3.getString(3));
                    personaList.add(rs3.getString(4));
                    personaList.add(rs3.getString(5));

                    //personaList.add(rs3.getString(6));

                } while (rs3.next());
                //bebida = new Bebida(itemList.get(0), itemList.get(1), itemList.get(2));
                personaList.forEach(System.out::println);
                //System.out.println(itemList);
            } else {
                System.out.println("...");
            }
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
        System.out.println("********");

        smt.close();
        smt2.close();
        smt3.close();
    }*/
}
