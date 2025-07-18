$(document).ready(function () {

    $("#form-login").submit(function (event) {

        event.preventDefault();
        autenticarUsuario();
    });

    $("#form-register").submit(function (event) {

        event.preventDefault();
        registrarUsuario();
    });


});

function autenticarUsuario() {

    let id_usuario = $("#usuario").val();
    let contrasena = $("#contrasena").val();

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioLogin",
        data: $.param({
            id_usuario: id_usuario,
            contrasena: contrasena
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);
            if (parsedResult != false) {
                $("#login-error").addClass("d-none");
                let id_usuario = parsedResult['id_usuario'];
                document.location.href = "home.html?id_usuario=" + id_usuario;
            } else {
                $("#login-error").removeClass("d-none");
            }
        }
    });
}
function registrarUsuario() {

    let id_usuario = $("#input-id_usuario").val();
    let contrasena = $("#input-contrasena").val();
    let contrasenaConfirmacion = $("#input-contrasena-repeat").val();
    let nombre = $("#input-nombre").val();
    let apellido = $("#input-apellido").val();
    let correo = $("#input-correo").val();
    let telefono = $("#input-telefono").val();
    let direccion = $("#input-direccion").val();

    if (contrasena == contrasenaConfirmacion) {

        $.ajax({
            type: "GET",
            dataType: "html",
            url: "./ServletUsuarioRegister",
            data: $.param({
                id_usuario: id_usuario,
                contrasena: contrasena,
                nombre: nombre,
                apellido: apellido,
                correo: correo,
                telefono: telefono,
                direccion: direccion
            }),
            success: function (result) {
                let parsedResult = JSON.parse(result);

                if (parsedResult != false) {
                    $("#register-error").addClass("d-none");
                    let id_usuario = parsedResult['id_usuario'];
                    document.location.href = "home.html?id_usuario=" + id_usuario;
                } else {
                    $("#register-error").removeClass("d-none");
                    $("#register-error").html("Error en el registro del usuario");
                }
            }
        });
    } else {
        $("#register-error").removeClass("d-none");
        $("#register-error").html("Las contraseñas no coinciden");
    }
}