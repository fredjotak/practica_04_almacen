<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Producto"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<Producto> listaProductos = (List<Producto>)request.getAttribute("listaProductos");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/styleGlobal.css">
        <link rel="stylesheet" href="css/style.css">
        <title>Productos</title>
    </head>
    <body>
        <section>
            <h1 class="centro">Productos</h1>
            <p><a href="MainController?op=nuevo" class="boton boton-izq fondo-naranja ancho-200p">Nuevo</a></p>
            <table class="tabla-uno">
                <tr>
                    <th>Id</th>
                    <th>Producto</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th colspan="2">Acción</th>
                </tr>
                <c:forEach var="item" items="${listaProductos}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.producto}</td>
                        <td>${item.precio} <b class="color-verde">Bs.</b></td>
                        <td>${item.cantidad}</td>
                        <td><a class="color-azul" href="MainController?op=editar&id=${item.id}">modificar</a></td>
                        <td><a class="color-azul" href="MainController?op=eliminar&id=${item.id}" onclick="return (confirm('¿Estas seguro de eliminar el producto ${item.producto}?'))">eliminar</a></td>
                    </tr>
                </c:forEach>
            </table>
        </section>
        <footer>
            © 2021/11/02 - Todos los derechos reservados
        </footer>
    </body>
</html>
