package com.dam2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.dam2.connect.Conexion;
import com.dam2.daoImpl.VentaDaoImpl;
import com.dam2.model.Venta;
import com.dam2.service.VentasService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//NO ES LO HABITUAL UN SERVLET POR ACCIÓN DEL CRUD, SINO UNO POR ENTIDAD
//Y DENTRO DE ÉL DISTINGUIR

@WebServlet("/ventas")
public class VentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VentasService service = 
			new VentasService(new VentaDaoImpl(Conexion.getConnection()));
	
    public VentasServlet() {
        
    }

    //IMPORTANTE UTILIZAR GET PARA CONSULTA, POST CUANDO SE ACTUALIZAN DATOS
    //EL NAVEGADOR LAS TRATA DE FORMA DISTINTA:
    //GET PUEDE CACHEARSE, CON LO QUE LA PETICIÓN NO LLEGARÍA AL SERVIDOR REAL
    //POST NO SE CACHEA
    //CUANDO HACES F5 CON POST TE PIDE CONFIRMAR REENVÍO, CON GET NO
    //SELECT -> GET
    //DELETE-UPDATE-INSERT -> POST
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("action").equals("listar")) {
			
			 List<Venta> lista = service.getVentas();
	            
	            response.setContentType("text/html;charset=UTF-8");
	           
	            out.println("<html><body>");
	            out.println("<h1>Listado de ventas</h1>");
	            out.println("<table border='2'>");

	            for (Venta v : lista) {
	                out.println("<tr>");
	                out.println("<td>" + v.getId() + "</td>");
	                out.println("<td>" + v.getCoche() + "</td>");
	                out.println("<td>" + v.getColor() + "</td>");
	                out.println("</tr>");
	            }
	            
	            out.println("</table>");
	            out.println("</body></html>");
		}//listar
		else if(request.getParameter("action").equals("eliminar")) {
			
			boolean eliminado = 
        			service.deleteVenta(Integer.parseInt(request.getParameter("id")));
        			
        	 out.println("<!DOCTYPE html>");
        	 out.println("<html lang='es'>");
        	 out.println("<head>");
        	 out.println("<meta charset='UTF-8'>");
        	 out.println("<title>Resultado Eliminación</title>");
        	 out.println("<style>");
        	 out.println("body { font-family: Arial; background:#f4f4f4; text-align:center; padding-top:50px; }");
        	 out.println(".box { background:white; padding:30px; display:inline-block; border-radius:8px; box-shadow:0 0 10px rgba(0,0,0,0.2); }");
        	 out.println("button { padding:10px 20px; margin-top:20px; font-size:16px; cursor:pointer; }");
        	 out.println("</style>");
        	 out.println("</head>");
        	 out.println("<body>");

        	 out.println("<div class='box'>");

        	 if (eliminado) {
        		 out.println("<h2 style='color:green;'>✅ Venta eliminada correctamente</h2>");
        	     out.println("<p>ID eliminado: <b>" + request.getParameter("id") + "</b></p>");
        	 } else {
        	     out.println("<h2 style='color:red;'>❌ No se pudo eliminar la venta</h2>");
        	     out.println("<p>Puede que no exista una venta con ese ID.</p>");
        	 }

        	 out.println("<form action='index.html' method='get'>");
        	 out.println("<button type='submit'>⬅ Volver al menú principal</button>");
        	 out.println("</form>");

        	 out.println("</div>");
        	 out.println("</body>");
        	 out.println("</html>");
			
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
