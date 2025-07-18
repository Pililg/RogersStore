package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gson.Gson;
import beans.Usuario;
import connection.DBConnection;
import java.util.HashMap;
import java.util.Map;

public class UsuarioController implements IUsuarioController {

    
    @Override
    public String login(String id_usuario, String contrasena) {
        Gson gson = new Gson();
        DBConnection con = new DBConnection();
        String sql = "Select * from usuario where id_usuario = '" + id_usuario
                + "' and contrasena = '" + contrasena + "'";

        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                
                Usuario usuario = new Usuario(id_usuario, contrasena, nombre, apellido, correo, telefono, direccion);
                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String register(String id_usuario, String contrasena, String nombre, String apellido, String correo,
            String telefono, String direccion) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
    String sql = "INSERT INTO usuario VALUES('" + id_usuario + "', '" + contrasena + "', '" + nombre
        + "', '" + apellido + "', '" + correo + "', '" + telefono + "', '" + direccion + "')";
            try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            Usuario usuario = new Usuario(id_usuario, contrasena, nombre, apellido, correo, telefono, direccion);


            st.close();

            return gson.toJson(usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String pedir(String id_usuario) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from usuario where id_usuario = '" + id_usuario + "'";

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String contrasena = rs.getString("contrasena");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");

                Usuario usuario = new Usuario(id_usuario, contrasena,
                        nombre, apellido, correo, telefono, direccion);

                return gson.toJson(usuario);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String modificar(String id_usuario, String nuevaContrasena,
            String nuevoNombre, String nuevoApellido,
            String nuevoCorreo, String nuevoTelefono, String nuevaDireccion) {

        DBConnection con = new DBConnection();

        String sql = "Update usuario set contrasena = '" + nuevaContrasena
                + "', nombre = '" + nuevoNombre + "', "
                + "apellido = '" + nuevoApellido + "', "
                + "correo = '" + nuevoCorreo + "', "
                + "telefono = '" + nuevoTelefono + "', " 
                + "direccion = '" + nuevaDireccion;

      
        sql += " where id_usuario = '" + id_usuario + "'";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String verStock(String id_usuario) {

        DBConnection con = new DBConnection();
        String sql = "Select id,count(*) as num_stock from compra where id_usuario = '"
                + id_usuario + "' group by id_usuario;";

        Map<Integer, Integer> stock = new HashMap<Integer, Integer>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int num_stock = rs.getInt("num_stock");

                stock.put(id, num_stock);
            }

            devolverProducto(id_usuario, stock);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String devolverProducto(String id_usuario, Map<Integer, Integer> stock) {

        DBConnection con = new DBConnection();

        try {
            for (Map.Entry<Integer, Integer> producto : stock.entrySet()) {
                int id_producto = producto.getKey();
                int num_stock = producto.getValue();

                String sql = "Update producto set stock = (Select stock + " + num_stock
                        + " from producto where id_producto = " + id_producto + ") where id_producto = " + id_producto;

                Statement st = con.getConnection().createStatement();
                st.executeUpdate(sql);

            }

            this.eliminar(id_usuario);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return "false";
    }

    @Override
    public String eliminar(String id_usuario) {

        DBConnection con = new DBConnection();

        String sql1 = "Delete from compra where id_usuario = '" + id_usuario + "'";
        String sql2 = "Delete from usuario where id_usuario = '" + id_usuario + "'";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return "false";
    }
    
  
}



