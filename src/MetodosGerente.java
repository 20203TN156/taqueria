import dao.Alimento;
import dao.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.ConnectionMySQL.getConnection;

public class MetodosGerente {

    String menu, menu2;
    Scanner sc = new Scanner(System.in);
    //Sesion sesion = new Sesion();
    Alimento alimento;
    String id;
    String usuario;
    String contrasenia;
    String nombre;
    String tel;
    String rol;
    String repetir;

    String ingredientes;
    String precio;

    public void rolGerente() throws SQLException {
        System.out.println("****MENÚ GESTIÓN - GERENTE****");
        System.out.println("1. ALIMENTOS");
        System.out.println("2. BEBIDAS");
        System.out.println("3. EMPLEADOS");
        System.out.println("4. VER ESTADO DE PEDIDOS ");
        System.out.println("5. VER SUCURSALES");

        menu = sc.nextLine();

        switch (menu) {
            case "1":
                System.out.println("****GESTIÓN DE ALIMENTOS****");
                System.out.println("1. MOSTRAR");
                System.out.println("2. AGREGAR");
                System.out.println("3. MODIFICAR");
                System.out.println("4. ELIMINAR");
                System.out.println("5. VOLVER AL MENÚ ANTERIOR");

                menu2 = sc.nextLine();

                switch (menu2) {
                    case "1":
                        mostrarAlimentos();
                        rolGerente();
                        break;
                    case "2":
                        agregarAlimento();
                        break;
                    case "3":
                        modificarAlimento();
                        break;
                    case "4":
                        eliminarAlimento();
                        break;
                    case "5":
                        rolGerente();
                        break;
                    default:
                        System.out.println("Ingresa una opción correcta");
                        rolGerente();
                        break;
                }

            case "2":
                System.out.println("****GESTIÓN DE BEBIDAS****");
                System.out.println("1. MOSTRAR");
                System.out.println("2. AGREGAR");
                System.out.println("3. MODIFICAR");
                System.out.println("4. ELIMINAR");
                System.out.println("5. VOLVER AL MENÚ ANTERIOR");
                menu2 = sc.nextLine();
                switch (menu2) {
                    case "1":
                        mostrarBebidas();
                        rolGerente();
                        break;
                    case "2":
                        agregarBebida();
                        break;
                    case "3":
                        modificarBebida();
                        break;
                    case "4":
                        eliminarBebida();
                        break;
                    case "5":
                        rolGerente();
                        break;
                    default:
                        System.out.println("Ingresa una opción correcta");
                        break;
                }
                break;
            case "3":
                System.out.println("****GESTIÓN DE EMPLEADOS****");
                System.out.println("1. MOSTRAR");
                System.out.println("2. AGREGAR");
                System.out.println("3. MODIFICAR");
                System.out.println("4. ELIMINAR");
                System.out.println("5. VOLVER AL MENÚ ANTERIOR");
                menu2 = sc.nextLine();
                switch (menu2) {
                    case "1":
                        mostrarEmpleados();
                        rolGerente();
                        break;
                    case "2":
                        agregarEmpleado();
                        break;
                    case "3":
                        modificarEmpleado();
                        break;
                    case "4":
                        eliminarEmpleado();
                        break;
                    case "5":
                        rolGerente();
                        break;
                    default:
                        System.out.println("Ingresa una opción correcta");
                        break;
                }
                break;
            case "4":
                break;
            case "5":
                mostrarSucurales();
                rolGerente();
                break;
        }
    }


    //Alimentos
    public void mostrarAlimentos() throws SQLException {
        System.out.println("****MOSTRAR ALIMENTOS****");

        List<String> itemList = new ArrayList<>();
        Statement smt = null;
        try {
            smt = getConnection().createStatement();

            String q = "SELECT * FROM alimento";

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

                itemList.forEach(System.out::println);
                //System.out.println(itemList);
            } else {
                System.out.println("...");
            }
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
        smt.close();
    }

    public void agregarAlimento() {
        try {
            Connection conn = getConnection();
            do {
                System.out.println("****AGREGAR ALIMENTO****");
                System.out.print("Nombre: ");
                nombre = sc.nextLine();
                System.out.print("Ingredientes: ");
                ingredientes = sc.nextLine();
                System.out.print("Precio: ");
                precio = sc.nextLine();
                alimento = new Alimento(nombre, ingredientes, precio);

                String query = "INSERT INTO alimento (nombre, ingredientes, precio) VALUES (?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, alimento.getNombre());
                preparedStmt.setString(2, alimento.getIngredientes());
                preparedStmt.setString(3, alimento.getPrecio());
                preparedStmt.execute();
                System.out.println("Ingresa 'r' para agregar más alimentos o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();
            } while (repetir.equals("r"));
            if (repetir != "r") {
                rolGerente();
            }
            conn.close();

        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());

        }
    }

    public void modificarAlimento() {
        try {
            Connection conn = getConnection();
            do {
                System.out.println("****MODIFICAR ALIMENTO****");
                mostrarAlimentos();
                System.out.print("ID: ");
                id = sc.nextLine();
                System.out.print("Nombre: ");
                nombre = sc.nextLine();
                System.out.print("Ingredientes: ");
                ingredientes = sc.nextLine();
                System.out.print("Precio: ");
                precio = sc.nextLine();

                String query = "UPDATE alimento SET nombre = ?, ingredientes = ?, precio = ? WHERE (id_alimento = ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, nombre);
                preparedStmt.setString(2, ingredientes);
                preparedStmt.setString(3, precio);
                preparedStmt.setString(4, id);
                preparedStmt.executeUpdate();
                System.out.println("Ingresa 'r' para modificar más alimentos o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();
            } while (repetir.equals("r"));
            if (repetir != "r") {
                rolGerente();
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }

    public void eliminarAlimento() {
        try {
            Connection conn = getConnection();
            do {
                System.out.println("****ELIMINAR ALIMENTO****");
                mostrarAlimentos();
                System.out.print("ID: ");
                id = sc.nextLine();
                String query = "delete from alimento where id_alimento = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, id);
                preparedStmt.execute();
                System.out.println("Ingresa 'r' para eliminar más alimentos o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();
            } while (repetir.equals("r"));
            if (repetir != "r") {
                rolGerente();
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }

    //Bebidas

    public void mostrarBebidas() throws SQLException {
        System.out.println("****MOSTRAR BEBIDAS****");

        List<String> itemList = new ArrayList<>();
        Statement smt = null;
        try {
            smt = getConnection().createStatement();

            String q = "SELECT * FROM bebida";

            ResultSet rs = smt.executeQuery(q);

            if (rs.next()) {
                do {
                    itemList.add(rs.getString(1));
                    itemList.add(rs.getString(2));
                    itemList.add(rs.getString(3));
                } while (rs.next());

                itemList.forEach(System.out::println);
                //System.out.println(itemList);
            } else {
                System.out.println("...");
            }
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
        smt.close();
    }

    public void agregarBebida() {
        try {
            Connection conn = getConnection();
            do {
                System.out.println("****AGREGAR BEBIDA****");
                System.out.print("Nombre: ");
                nombre = sc.nextLine();
                System.out.print("Precio: ");
                precio = sc.nextLine();

                String query = "INSERT INTO bebida (nombre, precio) VALUES (?, ?)";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, nombre);
                preparedStmt.setString(2, precio);
                preparedStmt.execute();

                System.out.println("Ingresa 'r' para agregar más bebida o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();

            } while (repetir.equals("r"));

            if (repetir != "r") {
                rolGerente();
            }
            conn.close();

        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }

    public void modificarBebida() {
        try {

            Connection conn = getConnection();
            do {
                System.out.println("****MODIFICAR BEBIDA****");
                mostrarBebidas();
                System.out.print("ID: ");
                id = sc.nextLine();
                System.out.print("Nombre: ");
                nombre = sc.nextLine();
                System.out.print("Precio: ");
                precio = sc.nextLine();

                String query = "UPDATE bebida SET nombre = ?, precio = ? WHERE (id_bebida = ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, nombre);
                preparedStmt.setString(2, precio);
                preparedStmt.setString(3, id);
                preparedStmt.executeUpdate();
                System.out.println("Ingresa 'r' para modificar más bebida o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();

            } while (repetir.equals("r"));

            if (repetir != "r") {
                rolGerente();
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }

    public void eliminarBebida() {
        try {
            Connection conn = getConnection();

            do {
                System.out.println("****ELIMINAR BEBIDA****");
                mostrarBebidas();
                System.out.print("ID: ");
                id = sc.nextLine();

                String query = "delete from bebida where id_bebida = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, id);
                preparedStmt.execute();
                System.out.println("Ingresa 'r' para eliminar más bebida o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();
            } while (repetir.equals("r"));

            if (repetir != "r") {
                rolGerente();
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }

    public void mostrarEmpleados() throws SQLException {
        System.out.println("****MOSTRAR EMPLEADOS****");

        List<String> itemList = new ArrayList<>();
        Statement smt = null;
        try {
            smt = getConnection().createStatement();

            String q = "SELECT * FROM persona where rol = '1'";

            ResultSet rs = smt.executeQuery(q);

            if (rs.next()) {
                do {
                    itemList.add(rs.getString(1));
                    itemList.add(rs.getString(2));
                    itemList.add(rs.getString(4));
                    itemList.add(rs.getString(5));
                } while (rs.next());

                itemList.forEach(System.out::println);
                //System.out.println(itemList);
            } else {
                System.out.println("...");
            }
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
        smt.close();
    }


    public void agregarEmpleado() {
        try {
            Connection conn = getConnection();
            do {
                System.out.println("****AGREGAR EMPLEADO****");
                System.out.print("Nombre: ");
                nombre = sc.nextLine();
                System.out.print("Número de teléfono: ");
                tel = sc.nextLine();
                System.out.print("Usuario: ");
                usuario = sc.nextLine();
                System.out.println("***Contraseña provisional es 'empleado'**** ");

                String query = "INSERT INTO persona (usuario, contrasenia, nombre, tel, rol) VALUES (?, 'provisional', ?, ?, '1')";

                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, usuario);
                preparedStmt.setString(2, nombre);
                preparedStmt.setString(3, tel);
                preparedStmt.execute();
                System.out.println("Ingresa 'r' para agregar más empleados o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();
            } while (repetir.equals("r"));

            if (repetir != "r") {
                rolGerente();
            }
            conn.close();

        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }

    public void modificarEmpleado() {
        try {

            Connection conn = getConnection();
            do {
                System.out.println("****MODIFICAR EMPLEADO****");
                mostrarEmpleados();
                System.out.print("ID: ");
                id = sc.nextLine();
                System.out.print("Usuario: ");
                usuario = sc.nextLine();
                System.out.print("Nombre: ");
                nombre = sc.nextLine();
                System.out.print("Teléfono: ");
                tel = sc.nextLine();

                String query = "UPDATE persona SET usuario = ?, nombre = ?, tel = ? WHERE (id_persona = ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, usuario);
                preparedStmt.setString(2, nombre);
                preparedStmt.setString(3, tel);
                preparedStmt.setString(4, id);

                preparedStmt.executeUpdate();
                System.out.println("Ingresa 'r' para modificar más empleados o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();

            } while (repetir.equals("r"));

            if (repetir != "r") {
                rolGerente();
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }

    public void eliminarEmpleado() {
        try {
            Connection conn = getConnection();

            do {
                System.out.println("****ELIMINAR EMPLEADO****");
                mostrarEmpleados();
                System.out.print("ID: ");
                id = sc.nextLine();

                String query = "delete from persona where id_persona = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, id);
                preparedStmt.execute();
                System.out.println("Ingresa 'r' para eliminar más empleados o cualquier otra letra para volver al menú principal");
                repetir = sc.nextLine();
            } while (repetir.equals("r"));

            if (repetir != "r") {
                rolGerente();
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
    }

    public void mostrarSucurales() throws SQLException {
        System.out.println("****MOSTRAR SUCURSALES****");

        List<String> itemList = new ArrayList<>();
        Statement smt = null;
        try {
            smt = getConnection().createStatement();

            String q = "SELECT * FROM sucursal";

            ResultSet rs = smt.executeQuery(q);

            if (rs.next()) {
                do {
                    itemList.add(rs.getString(1));
                    itemList.add(rs.getString(2));
                } while (rs.next());

                itemList.forEach(System.out::println);
                //System.out.println(itemList);
            } else {
                System.out.println("...");
            }
        } catch (Exception e) {
            System.err.println("Excepción: " + e.getMessage());
        }
        smt.close();
    }
}
