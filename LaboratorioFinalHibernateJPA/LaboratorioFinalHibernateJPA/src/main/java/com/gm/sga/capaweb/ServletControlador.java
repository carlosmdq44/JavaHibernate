package com.gm.sga.capaweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gm.sga.capadatos.dto.Alumno;
import com.gm.sga.capadatos.dto.Contacto;
import com.gm.sga.capadatos.dto.Curso;
import com.gm.sga.capadatos.dto.Domicilio;
import com.gm.sga.capadatos.dto.Usuario;
import com.gm.sga.capaservicio.ServicioAlumnos;
import com.gm.sga.capaservicio.ServicioUsuarios;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ServletControlador", urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Revisamos los casos de uso del sistema SGA
        String accion = request.getParameter("accion");

        if ("listarAlumnos".equals(accion)) {
            this.confirmarUsuarioEnSession(request, response);
        } else if ("validarUsuario".equals(accion)) {
            this.validarUsuario(request, response);
        } else if ("agregarAlumno".equals(accion)) {
            this.agregarAlumno(request, response);
        } else if ("editarAlumno".equals(accion)) {
            this.editarAlumno(request, response);
        } else if ("eliminarAlumno".equals(accion)) {
            this.eliminarAlumno(request, response);
        } else if ("guardarAlumno".equals(accion)) {
            //Revisamos si es inserciÃ³n o modificaciÃ³n
            HttpSession session = request.getSession();
            Alumno alumno = (Alumno) session.getAttribute("alumno");
            if (alumno == null) {
                this.insertarAlumno(request, response);
            } else {
                this.guardarAlumno(request, response);
            }
        } else if ("buscarAlumno".equals(accion)) {
            this.buscarAlumno(request, response);
        } else if ("ejecutarBusquedaAlumnos".equals(accion)) {
            this.ejecutarBusquedaAlumnos(request, response);
        } else if ("salir".equals(accion)) {
            this.salir(request, response);
        } else {
            this.accionPorDefault(request, response);
        }

    }

    private void ejecutarBusquedaAlumnos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //1. Recuperamos los parÃ¡metros de bÃºsqueda

        //Revisamos si ya estÃ¡ el usuario en la sesion
        HttpSession session = request.getSession();

        String usuario = (String) session.getAttribute("usuario");

        //Si el usuario ya esta en session, lo redireccionamos al listado de alumnos
        if (usuario != null) {
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
            //esto permite ir agregando mÃ¡s filtros si se necesitaran
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
            request.getRequestDispatcher("/WEB-INF/pages/sga/listarAlumnos.jsp").forward(request, response);

        } else {
            this.salir(request, response);
        }

    }

    private void buscarAlumno(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //4. Seleccionamos la vista a mostrar la info de alumnos
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarAlumnos.jsp").forward(request, response);
    }

    //Metodo para procesar validar si el usuario ya inicio sesion
    private void confirmarUsuarioEnSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Revisamos si ya estÃ¡ el usuario en la sesion
        HttpSession session = request.getSession();

        String usuario = (String) session.getAttribute("usuario");
        if (usuario != null) {
            //Si ya existe un usuario en session, lo redireccionamos a la lista de alumno
            this.listarAlumnos(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/pages/sga/login.jsp").forward(request, response);
        }
    }

    //Metodo para procesar el caso de uso de listarAlumnos
    private void listarAlumnos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Revisamos si ya estÃ¡ el usuario en la sesion
        HttpSession session = request.getSession();

        String usuario = (String) session.getAttribute("usuario");

        //Si el usuario ya esta en session, lo redireccionamos al listado de alumnos
        if (usuario != null) {

            //1. Nos comunicamos con la capa de servicio
            ServicioAlumnos servicioAlumnos = new ServicioAlumnos();

            //2. Recuperamos todos los alumnos
            List<Alumno> alumnos = servicioAlumnos.listarAlumnos();

            //3. Compartimos la informacion (MOdelo) con la vista
            request.setAttribute("alumnos", alumnos);

            //4. Seleccionamos la vista a mostrar la info de alumnos
            request.getRequestDispatcher("/WEB-INF/pages/sga/listarAlumnos.jsp").forward(request, response);
        } else {
            //Lo regresamos al inicio
            this.salir(request, response);
        }
    }

    //Metodo para validar si el usuario y password proporcinados son correctos
    private void validarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recuperamos los parametros del formulario
        String usuarioParam = request.getParameter("username");
        String passwordParam = request.getParameter("password");

        //Creamos el objeto DTO a enviar a la capa de servicio
        Usuario usuarioDTO = new Usuario();
        usuarioDTO.setUsername(usuarioParam);
        usuarioDTO.setPassword(passwordParam);

        //Revisamos si existen el usuario y el password en la BD
        //Utilizamos el servicio de Usuarios
        ServicioUsuarios usuarioService = new ServicioUsuarios();
        boolean usuarioValido = usuarioService.usuarioExistente(usuarioDTO);

        //Si el usuario es vÃ¡lido, lo redireccionamos al caso de listarAlumnos
        if (usuarioValido) {
            //Agregamos el usuario a la session
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuarioDTO.getUsername());

            this.listarAlumnos(request, response);
        } else {
            //si el usuario no es valido, lo mandamos a la pagina de login nuevamente
            request.setAttribute("mensaje", "El usuario o password son incorrectos");
            request.getRequestDispatcher("/WEB-INF/pages/sga/login.jsp").forward(request, response);
        }
    }

    private void salir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Eliminamos la session del servidor y redireccionamos a la pagina de inicio
        request.getSession().invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    private void accionPorDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Redireccionamos a la pagina de inicio
        String mensaje = "Acci&oacute;n no proporcionada o desconocida";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    private void agregarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //No procesamos ningun parametro, sino que solo redireccionamos a la vista
        //para agregar un nuevo alumno
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleAlumno.jsp").forward(request, response);
    }

    private void insertarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;

        //1. Recuperamos los parametros del formulario
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String calle = request.getParameter("calle");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        //2. Llenamos el objeto DTO de Alumno a insertar
        //Creamos el objeto Domicilio
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(calle);
        domicilio.setVersion(0);
        domicilio.setDeleted(0);

        //Creamos el objeto de Contacto
        Contacto contacto = new Contacto();
        contacto.setEmail1(email);
        contacto.setTelefono(telefono);
        contacto.setVersion(0);
        contacto.setDeleted(0);

        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);
        alumno.setVersion(0);//llenamos con valores por default
        alumno.setDeleted(0);//estos son campos requeridos
        //Inyectamos las depedencias de los objetos Domicilio y Contacto
        alumno.setDomicilio(domicilio);
        alumno.setContacto(contacto);

        //3. Nos apoyamos de la capa de servicio
        ServicioAlumnos servicioAlumnos = new ServicioAlumnos();
        boolean elementoGuardado = servicioAlumnos.guardarAlumno(alumno);

        //4. Redireccionamos dependiendo del resultado
        if (elementoGuardado) {
            mensaje = "Se guard&oacute; el elemento correctamente";
            request.setAttribute("mensaje", mensaje);

        } else {
            mensaje = "No se guardo correctamente el elemento";
            request.setAttribute("mensaje", mensaje);
        }

        //5. Reutilizamos el caso de listarAlumnos
        request.setAttribute("mensaje", mensaje);
        this.listarAlumnos(request, response);
    }

    private void editarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mensaje = null;

        //Recuperamos el identificador a procesar y reenviamos a la pagina de detalle
        String idAlumnoParam = request.getParameter("alumnos");
        Integer idAlumno = null;

        if (idAlumnoParam != null && !idAlumnoParam.trim().equals("")) {
            idAlumno = new Integer(idAlumnoParam);

            //Utilizamos el servicio de alumno para recuperar el objeto de la BD
            ServicioAlumnos alumnoService = new ServicioAlumnos();
            Alumno alumno = alumnoService.encontrarAlumno(idAlumno);

            //compartimos el objeto alumno obtenido, para poderlo modificar
            //utilizamos la sesion
            HttpSession session = request.getSession();
            session.setAttribute("alumno", alumno);
            request.getRequestDispatcher("/WEB-INF/pages/sga/detalleAlumno.jsp").forward(request, response);

        } else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("mensaje", mensaje);
            //Reutilizamos el caso de listarAlumnos
            this.listarAlumnos(request, response);
        }

    }

    //Este metodo nos permite insertar o modificar un alumno
    private void guardarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mensaje = null;

        //1. Revisamos si tenemos un objeto Alumno en la sesion
        //En caso de que ya estuviera en sesion,
        //es una modificaciÃ³n de un alumno (con idAlumno ya incluido)
        //de lo contrario estamos agregando un nuevo alumno
        HttpSession session = request.getSession();
        Alumno alumno = (Alumno) session.getAttribute("alumno");

        //2. Recuperamos los parametros del formulario
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellidoPaterno");
        String apellidoMaterno = request.getParameter("apellidoMaterno");
        String calle = request.getParameter("calle");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        //3. Llenamos el objeto DTO de Alumno a insertar
        //Vaciamos los valores en el objeto
        alumno.setNombre(nombre);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);
        alumno.setVersion(0);//llenamos con valores por default
        alumno.setDeleted(0);//estos son campos requeridos
        //Inyectamos las depedencias de los objetos Domicilio y Contacto
        alumno.getDomicilio().setCalle(calle);
        alumno.getContacto().setEmail1(email);
        alumno.getContacto().setTelefono(telefono);

        //4. Nos apoyamos de la capa de servicio
        ServicioAlumnos servicioAlumnos = new ServicioAlumnos();
        boolean elementoGuardado = servicioAlumnos.guardarAlumno(alumno);

        //5. Redireccionamos dependiendo del resultado
        if (elementoGuardado) {
            mensaje = "Se guard&oacute; el elemento correctamente";
            request.setAttribute("mensaje", mensaje);

        } else {
            mensaje = "No se guardo correctamente el elemento";
            request.setAttribute("mensaje", mensaje);
        }

        //6. Eliminamos el objeto modificado de la sesion
        //ya que solo lo utilizamos mientras modificabamos el alumno
        session.removeAttribute("alumno");

        //7. Reutilizamos el caso de listarAlumnos
        request.setAttribute("mensaje", mensaje);
        this.listarAlumnos(request, response);

    }

    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mensaje = null;

        //1. Procesamos los parametros
        //Recuperamos TODOS los objetos seleccionados
        String[] idAlumnoParams = request.getParameterValues("alumnos");
        List<Integer> idAlumnos = new ArrayList<Integer>();

        //2. Utilizamos los objetos de Modelo (Alumno)
        //Validamos los parametros a eliminar
        if (idAlumnoParams != null && idAlumnoParams.length > 0) {
            for (String alumno : idAlumnoParams) {
                idAlumnos.add(new Integer(alumno));
            }

            //3.Utilizamos la capa de servicio para eliminar los objetos
            ServicioAlumnos alumnoService = new ServicioAlumnos();
            boolean registrosEliminados = alumnoService.eliminarAlumnos(idAlumnos);

            if (registrosEliminados) {
                mensaje = "Se eliminaron correctamente los registros";
            }
        } else {
            mensaje = "Debe seleccionar uno o varios elementos a Eliminar";
        }

        //4. Redireccionamos al listado de alumnos (ya no debe de mostrar los registros eliminados)
        request.setAttribute("mensaje", mensaje);
        this.listarAlumnos(request, response);
    }

}