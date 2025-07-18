var id_usuario = new URL(location.href).searchParams.get("id_usuario");
var user;

$(document).ready(function () {


    fillUsuario().then(function () {

        $("#user-saldo").html("$" + user.saldo.toFixed());

        getAlquiladas(user.username);
    });

    $("#reservar-btn").attr("href", `home.html?username=${username}`);

    $("#form-modificar").on("submit", function (event) {

        event.preventDefault();
        modificarUsuario();
    });

    $("#aceptar-eliminar-cuenta-btn").click(function () {

        eliminarCuenta().then(function () {
            location.href = "index.html";
        })
    })

});

async function fillUsuario() {
    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            id_usuario: id_usuario,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;

                $("#input-id_usuario").val(parsedResult.id_usuario);
                $("#input-contrasena").val(parsedResult.contrasena);
                $("#input-nombre").val(parsedResult.nombre);
                $("#input-apellido").val(parsedResult.apellido);
                $("#input-correo").val(parsedResult.correo);
                $("#input-telefono").val(parsedResult.telefono);
                $("#input-direccion").val(parsedResult.direccion);

            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });
}

function getComprar(id_usuario) {


    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletComprarListar",
        data: $.param({
            id_usuario: id_usuario,
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {

                mostrarHistorial(parsedResult)

            } else {
                console.log("Error recuperando los datos de las reservas");
            }
        }
    });
}

function mostrarHistorial(productos) {
    let contenido = "";
    if (productos.length >= 1) {
        $.each(productos, function (index, producto) {
            producto = JSON.parse(producto);

            contenido += '<tr><th scope="row">' + producto.id_producto + '</th>' +
                    '<td>' + producto.nombre + '</td>' +
                    '<td>' + producto.descripcion + '</td>' +
                    '<td><input type="checkbox" name="novedad" id="novedad' + producto.id_producto
                    + '" disabled ';
            if (producto.novedad) {
                contenido += 'checked'
            }
            contenido += '></td><td>' + producto.fechaCompra + '</td>' +
                    '<td><button id="devolver-btn" onclick= "devolverproducto(' + producto.id_producto
                    + ');" class="btn btn-danger">Devolver producto</button></td></tr>';

        });
        $("#historial-tbody").html(contenido);
        $("#historial-table").removeClass("d-none");
        $("#historial-vacio").addClass("d-none");

    } else {
        $("#historial-vacio").removeClass("d-none");
        $("#historial-table").addClass("d-none");
    }
}


function devolverProducto(id) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletProductoDevolver",
        data: $.param({
            id_usuario: id_usuario,
            id_producto: id_producto,
        }),
        success: function (result) {

            if (result != false) {

                location.reload();

            } else {
                console.log("Error devolviendo el Producto");
            }
        }
    });

}

function modificarUsuario() {

    let username = $("#input-username").val();
    let contrasena = $("#input-contrasena").val();
    let nombre = $("#input-nombre").val();
    let apellido = $("#input-apellido").val();
    let email = $("#input-email").val();
    let saldo = $("#input-saldo").val();
    let premium = $("#input-premium").prop('checked');
    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioModificar",
        data: $.param({
            id_usuario: id_usuario,
            contrasena: contrasena,
            nombre: nombre,
            apellido: apellido,
            correo: correo,
            telefono: telefono,
            direccion: direccion,
        }),
        success: function (result) {

            if (result != false) {
                $("#modificar-error").addClass("d-none");
                $("#modificar-exito").removeClass("d-none");
            } else {
                $("#modificar-error").removeClass("d-none");
                $("#modificar-exito").addClass("d-none");
            }

            setTimeout(function () {
                location.reload();
            }, 3000);

        }
    });

}

async function eliminarCuenta() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioEliminar",
        data: $.param({
            id_producto: id_producto
        }),
        success: function (result) {

            if (result != false) {

                console.log("Usuario eliminado")

            } else {
                console.log("Error eliminando el usuario");
            }
        }
    });
}