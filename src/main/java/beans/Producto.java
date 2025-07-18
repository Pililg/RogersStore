package beans;

import java.math.BigDecimal;
public class Producto {
    private int id_producto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String color;
    private String talla;
    private String imagen;
    private int stock;

    public Producto(int id_producto, String nombre, String descripcion, BigDecimal precio, String color, String talla, String imagen, int stock) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.color = color;
        this.talla = talla;
        this.imagen = imagen;
        this.stock = stock;
    }

    public int getId_Producto() {
        return id_producto;
    }

    public void setId_Producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", color=" + color + ", talla=" + talla + ", imagen=" + imagen + ", stock=" + stock + '}';
    }
    
    
    
}  
