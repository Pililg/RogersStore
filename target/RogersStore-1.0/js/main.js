var id_usuario = new URL(location.href).searchParams.get("id_usuario");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?id_usuario=" + id_usuario);
        
        $("#user-saldo").html(user.saldo.toFixed(2) + "$");

        getProductos(false, "ASC");

        $("#ordenar-nombre").click(ordenarProductos);
    });
});


async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            id_usuario: id_usuario
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}
function getProductos(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletProductoListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarProductos(parsedResult);
            } else {
                console.log("Error recuperando los datos de los productos");
            }
        }
    });
}
function mostrarProductos(productos) {

    let contenido = "";

    $.each(productos, function (index, producto) {

        producto = JSON.parse(producto);
        let precio;

        if (producto.stock > 0) {

            if (user.premium) {

                if (producto.novedad) {
                    precio = (2 - (2 * 0.1));
                } else {
                    precio = (1 - (1 * 0.1));
                }
            } else {
                if (producto.descripcion) {
                    precio = 2;
                } else {
                    precio = 1;
                }
            }

            contenido += '<tr><th scope="row">' + producto.id_producto + '</th>' +
                    '<td>' + producto.nombre + '</td>' +
                    '<td>' + producto.descripcion + '</td>' +
                    '<td>' + producto.precio + '</td>' +
                    '<td>' + producto.color + '</td>' +
                    '<td>' + producto.talla + '</td>' +
                    '<td>' + producto.imagen + '</td>' +
                    '<td>' + producto.stock + '</td>' +
                    '<td><button onclick="comprarProducto(' + producto.id_producto + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Comprar</button></td></tr>'

        }
    });
    $("#productos-tbody").html(contenido);
}

function ordenarProductos() {

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getProductos(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getProductos(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getProductos(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}
function comprarProducto(id_producto, precio) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletProductoComprar",
        data: $.param({
            id_producto: id_producto,
            id_usuario: id_usuario

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                restarDinero(precio).then(function () {
                    location.reload();
                })
            } else {
                console.log("Error en la compra de la producto");
            }
        }
    });
}


async function restarDinero(precio) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioRestarDinero",
        data: $.param({
            id_producto: id_producto,
            saldo: parseFloat(user.saldo - precio)

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                console.log("Saldo actualizado");
            } else {
                console.log("Error en el proceso de pago");
            }
        }
    });
}


