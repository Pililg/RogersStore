package controller;

public interface IProductoController {

    public String listar(boolean ordenar, String orden);

    public String devolver(int id_producto, String id_usuario);

    public String sumarCantidad(int id_producto);

    public String compra(int id_producto, String id_usuario);

    public String modificar(int id_producto);

}


