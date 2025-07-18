package beans;

import java.math.BigDecimal;
import java.sql.Date;
public class Compra {
    private int id_compra;
    private String id_usuario;
    private Date fecha;
    private BigDecimal total;

    public Compra(int id_compra, String id_usuario, Date fecha, BigDecimal total) {
        this.id_compra = id_compra;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Compra{" + "id_compra=" + id_compra + ", id_usuario=" + id_usuario + ", fecha=" + fecha + ", total=" + total + '}';
    }
        
}
