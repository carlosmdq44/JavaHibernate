package com.gm.sga.capaweb;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.gm.sga.capadatos.dto.*;
import com.gm.sga.capaservicio.ServicioAlumnos;

@WebServlet(name = "ServletBuscar", urlPatterns = {"/ServletBuscar"})
public class ServletBuscar extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1. Recuperamos los parametros de busqueda
        //Recuperamos los datos del alumno
        String nombreAlumno = request.getParameter("nombreAlumno");
        nombreAlumno = "".equals(nombreAlumno) ? null : nombreAlumno.trim();

        String apellidoPaterno = request.getParameter("apellidoPaterno");
        apellidoPaterno = "".equals(apellidoPaterno) ? null : apellidoPaterno.trim();

        String apellidoMaterno = request.getParameter("apellidoMaterno");
        apellidoMaterno = "".equals(apellidoMaterno) ? null : apellidoMaterno.trim();

        Alumno alumnoDTO = null;
        if (nombreAlumno != null || apellidoPaterno != null || apellidoMaterno != null) {
            alumnoDTO = new Alumno();
            alumnoDTO.setNombre(nombreAlumno);
            alumnoDTO.setApellidoPaterno(apellidoPaterno);
            alumnoDTO.setApellidoMaterno(apellidoMaterno);
        }

        //Recuperamos los datos de Contacto
        String telefono = request.getParameter("telefono");
        telefono = "".equals(telefono) ? null : telefono.trim();

        String email = request.getParameter("email");
        email = "".equals(email) ? null : email.trim();

        Contacto contactoDTO = null;
        if (telefono != null || email != null) {
            contactoDTO = new Contacto();
            contactoDTO.setTelefono(telefono);
            contactoDTO.setEmail1(email);
        }

        //Recuperamos los datos del Domicilio
        String calle = request.getParameter("calle");
        calle = "".equals(calle) ? null : calle.trim();

        Domicilio domicilioDTO = null;
        if (calle != null) {
            domicilioDTO = new Domicilio();
            domicilioDTO.setCalle(calle);
        }

        String nombreCurso = request.getParameter("nombreCurso");
        nombreCurso = "".equals(nombreCurso) ? null : nombreCurso.trim();
        Curso cursoDTO = null;
        if (nombreCurso != null) {
            cursoDTO = new Curso();
            cursoDTO.setNombre(nombreCurso);
        }

        //2. Agregamos los parametros a un mapa
        //esto permite ir agregando mas filtros si se necesitaran
        Map criterios = new HashMap();
        criterios.put("alumno", alumnoDTO);
        criterios.put("contacto", contactoDTO);
        criterios.put("domicilio", domicilioDTO);
        criterios.put("curso", cursoDTO);

        //3. Nos comunicamos con la capa de servicio
        ServicioAlumnos servicioAlumnos = new ServicioAlumnos();

        //4. Enviamos el mapa de parametros para crear el filtro
        List<Alumno> alumnos = servicioAlumnos.encontrarAlumnosPorCriterios(criterios);

        //3. Compartimos la informacion (MOdelo) con la vista
        request.setAttribute("alumnos", alumnos);

        //4. Seleccionamos la vista a mostrar la info de alumnos
        request.getRequestDispatcher("/WEB-INF/listarAlumnos.jsp").forward(request, response);
    }
}