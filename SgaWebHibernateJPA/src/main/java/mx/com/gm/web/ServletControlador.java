package mx.com.gm.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import mx.com.gm.domain.Alumno;
import mx.com.gm.servicio.ServicioAlumno;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        ServicioAlumno servicioAlumno = new ServicioAlumno();
        
        List<Alumno> alumnos = servicioAlumno.listarAlumnos();
        
        request.setAttribute("alumnos", alumnos);
        
        try {
            request.getRequestDispatcher("/WEB-INF/listarAlumnos.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
