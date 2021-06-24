package com.gm.sga.capaweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gm.sga.capadatos.dto.Alumno;
import com.gm.sga.capaservicio.ServicioAlumnos;

@WebServlet(name = "ServletModificar", urlPatterns = {"/ServletModificar"})
public class ServletModificar extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Este metodo get se va a encargar de recuperar
        // el objeto a modificar para mostrarlo en el formulario
        //1. Recuperamos el idAlumno de la peticion
        String idAlumno = request.getParameter("idAlumno");

        //2. Nos apoyamos de la clase de servicio para recuperar
        //el objeto a modificar
        ServicioAlumnos servicioAlumnos = new ServicioAlumnos();
        Alumno alumno = servicioAlumnos.encontrarAlumno(Integer.parseInt(idAlumno));

        //3. Compartimos el objeto encontrado con la vista
        //Vamos a compartirlo en la session
        HttpSession session = request.getSession();
        session.setAttribute("alumno", alumno);

        //4. Redireccionamos a la vista de modificar
        request.getRequestDispatcher("/WEB-INF/modificarAlumno.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Este metodo post se va encargar de modificar
        //los valores que se envien del formulario
        //Revisamos si se presiono el boton de modificar o eliminar
        String accion = request.getParameter("modificar");
        if ("modificar".equals(accion)) {

            //1. Recuperamos los parametros
            String nombre = request.getParameter("nombre");
            String apellidoPaterno = request.getParameter("apellidoPaterno");
            String apellidoMaterno = request.getParameter("apellidoMaterno");
            String calle = request.getParameter("calle");
            String email1 = request.getParameter("email");
            String telefono = request.getParameter("telefono");

            //2. Recuperamos el objeto de la sesion
            HttpSession session = request.getSession();
            Alumno alumno = (Alumno) session.getAttribute("alumno");

            //3. Actualizamos los valores de la modificacion del alumno
            alumno.setNombre(nombre);
            alumno.setApellidoPaterno(apellidoPaterno);
            alumno.setApellidoMaterno(apellidoMaterno);
            //Actualizamos los valores de los objetos embebidos
            //de contacto y domicilio
            alumno.getDomicilio().setCalle(calle);
            alumno.getContacto().setEmail1(email1);
            alumno.getContacto().setTelefono(telefono);

            //4. Nos comunicamos con la capa de servicio para guardar
            //los cambios
            ServicioAlumnos servicioAlumnos = new ServicioAlumnos();
            servicioAlumnos.guardarAlumno(alumno);

            //5. Eliminamos el objeto modificado de la sesion
            //ya que solo lo utilizamos mientras modificabamos el alumno
            session.removeAttribute("alumno");

        } //else if("eliminar".equals( accion )){
        else { //se selecciono eliminar
            //Eliminamos el objeto de alumno

            //1. Recuperamos el id del alumno a eliminar
            String idAlumno = request.getParameter("idAlumno");

            //2. Nos apoyamos de la capa de servicio
            //para eliminar el objeto de Alumno
            ServicioAlumnos servicioAlumnos = new ServicioAlumnos();
            servicioAlumnos.eliminarAlumno(Integer.parseInt(idAlumno));
        }

        //6.Redireccionamos a la pagina de inicio
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

}