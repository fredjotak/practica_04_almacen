<%@page import="com.emergentes.modelo.Producto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/styleGlobal.css">
        <link rel="stylesheet" href="css/style.css">
        <title>
            <c:if test="${miProducto.id==0}">Nuevo Producto</c:if>
            <c:if test="${miProducto.id!=0}">Editar Producto</c:if>
        </title>
    </head>
    <body>
        <section>
            <div class="contenedor-formulario">
                <h1 class="formulario-titulo">
                    <c:if test="${miProducto.id==0}">Nuevo Producto</c:if>
                    <c:if test="${miProducto.id!=0}">Editar Producto</c:if>
                </h1>
                <div class="formulario-logo"><img src="css/imagen/caja.png" alt=""></div>
                <form action="MainController" class="formulario fondo-azul" method="POST">
                    <div class="formulario-campos">
                        <input type="hidden" name="hdnId" value="${miProducto.id}" />
                        <label for="txtProducto">Producto:</label>
                        <input type="text" id="txtProducto" name="txtProducto" required placeholder="Nombre del producto" value="${miProducto.producto}" />
                        <br>
                        <label for="nroPrecio">Precio:</label>
                        <input type="number" id="nroPrecio" name="nroPrecio" required placeholder="Precio del producto" min="0" step="0.1" max="10000000" value="${miProducto.precio}" />
                        <br>
                        <label for="nroCantidad">Cantidad:</label>
                        <input type="number" id="nroCantidad" name="nroCantidad" required placeholder="Cantidad del producto" min="0" step="1" max="10000000" value="${miProducto.cantidad}" />
                        <br>
                    </div>
                    <input class="boton fondo-naranja" type="submit" value='<c:if test="${miProducto.id==0}">Registrar</c:if><c:if test="${miProducto.id!=0}">Actualizar</c:if>'>
                </form>
            </div>
        </section>
        <footer>
            © 2021/11/02 - Todos los derechos reservados
        </footer>
    </body>
</html>
