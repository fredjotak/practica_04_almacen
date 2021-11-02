package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
          String op = (request.getParameter("op")!=null)? request.getParameter("op"): "vista";
          ArrayList<Producto> listaProductos = new ArrayList<Producto>();
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            
            if (op.equals("nuevo")){
                Producto producto = new Producto();
                
                request.setAttribute("miProducto", producto);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } else if (op.equals("editar")) {
                Producto producto = new Producto();
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "SELECT * FROM productos WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                if(rs.next()){
                    producto.setId(rs.getInt("id"));
                    producto.setProducto(rs.getString("producto"));
                    producto.setPrecio(rs.getFloat("precio"));
                    producto.setCantidad(rs.getInt("cantidad"));
                }
                
                request.setAttribute("miProducto", producto);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } else if(op.equals("eliminar")){
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "DELETE FROM productos WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                
                response.sendRedirect("MainController");
            } else {
                String sql = "SELECT * FROM productos";
                
                // Consulta de selección y almacenarlos  e una colección
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while(rs.next()){
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setProducto(rs.getString("producto"));
                    producto.setPrecio(rs.getFloat("precio"));
                    producto.setCantidad(rs.getInt("cantidad"));
                    listaProductos.add(producto);
                }
                request.setAttribute("listaProductos", listaProductos);
                
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR AL CONECTAR: "+ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("hdnId"));
            
            String nombreProducto = request.getParameter("txtProducto");
            Float precio = Float.parseFloat(request.getParameter("nroPrecio"));
            int cantidad = Integer.parseInt(request.getParameter("nroCantidad"));
            
            Producto producto = new Producto();
            producto.setId(id);
            producto.setProducto(nombreProducto);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            
            if(id==0) { // Nuevo registro si es 0
                String sql = "INSERT INTO productos(producto, precio, cantidad) VALUES(?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, producto.getProducto());
                ps.setFloat(2, producto.getPrecio());
                ps.setInt(3, producto.getCantidad());
                ps.executeUpdate();
            } else { // Caso contrario edicion del producto
                String sql = "UPDATE productos SET producto = ?, precio = ?, cantidad = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, producto.getProducto());
                ps.setFloat(2, producto.getPrecio());
                ps.setInt(3, producto.getCantidad());
                ps.setInt(4, producto.getId());
                ps.executeUpdate();
            }
            response.sendRedirect("MainController");
        } catch(SQLException ex){
            System.out.println("ERRRO EN SQL: "+ex.getMessage());
        }
    }
}