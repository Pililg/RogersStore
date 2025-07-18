package controller;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import beans.Producto;
import connection.DBConnection;

public class ProductoController implements IProductoController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from producto";

        if (ordenar == true) {
            sql += " order by nombre " + orden;
        }

        List<String> productos = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id_producto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                BigDecimal precio = rs.getBigDecimal("precio");
                String color = rs.getString("color");
                String talla = rs.getString("talla");
                String imagen = rs.getString("imagen");
                int stock = rs.getInt("stock");

                Producto producto = new Producto(id_producto, nombre, descripcion, precio, color, talla, imagen, stock);

                productos.add(gson.toJson(producto));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(productos);

    }
    
     @Override
    public String devolver(int id_producto, String id_usuario) {

        DBConnection con = new DBConnection();
        String sql = "Delete from compra where id_compra= " + id_producto + " and id_usuario = '" 
                + id_usuario + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeQuery(sql);

            this.sumarCantidad(id_producto);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String sumarCantidad(int id_producto) {

        DBConnection con = new DBConnection();

        String sql = "Update producto set stock = (Select stock from producto where id_producto = " 
                + id_producto + ") + 1 where id_producto = " + id_producto;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
    
    
    @Override
    public String compra(int id_producto, String id_usuario) {

        Timestamp fecha = new Timestamp(new Date().getTime());
        DBConnection con = new DBConnection();
        String sql = "Insert into compra values ('" + id_producto + "', '" + id_usuario + "', '" + fecha + "')";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id_producto);

            if (modificar.equals("true")) {
                return "true";
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";
    }

     @Override
    public String modificar(int id_producto) {

        DBConnection con = new DBConnection();
        String sql = "Update producto set stock = (stock - 1) where id_producto = " + id_producto;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }
}
