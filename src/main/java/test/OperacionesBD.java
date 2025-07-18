package test;
import beans.Producto;
import connection.DBConnection;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesBD {
    
    public static void main(String[] args) {
        actualizarProducto(1, "Camisa Azul - Talla S");
        listarProducto();  
      }
    public static void actualizarProducto(int id_producto, String nombre){
        DBConnection con = new DBConnection();
        String sql = "UPDATE producto SET nombre= '"+ nombre + "'WHERE id_producto= "+id_producto;
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }
    }   
     public static void listarProducto(){
        DBConnection con = new DBConnection();
        String sql = "SELECT*FROM producto";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
             int id_producto = rs.getInt("id_producto");
             String nombre = rs.getString("nombre");
             String descripcion = rs.getString("descripcion");
             BigDecimal precio = rs.getBigDecimal("precio");
             String color = rs.getString("color");
             String talla = rs.getString("talla");
             String imagen = rs.getString("imagen");
             int stock = rs.getInt("stock");
             
            Producto productos = new Producto(id_producto, nombre, descripcion, precio, color, talla, imagen, stock);
            System.out.println(productos.toString());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }   
    }  
}
