<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busqueda Alumnos</title>
    </head>
    <body>
        <div id="wrap">
            <div id="header">
                <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
            </div>

            <div id="nav">
                <jsp:include page="/WEB-INF/pages/commons/menu.jsp" />
            </div>

            <div id="msg">
                <jsp:include page="/WEB-INF/pages/commons/messages.jsp" />
            </div>

            <div id="main">

                <div class="subtitulo">
                    Buscar Alumnos
                </div>

                <div class="formulario">
                    <!--Es importante definir el id de la forma, ya que se validara con JavaScript -->
                    <form id="form1" name="form1"
                          action="${pageContext.request.contextPath}/ServletControlador"
                          method="post">

                        <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                        <input type="hidden" name="accion" id="accion"
                               value="ejecutarBusquedaAlumnos" />

                        <!--nos va a servir para que javascript tome el valor dinamicamente
del nombre de la aplicacion-->
                        <input type="hidden" name="contexto" id="contexto"
                               value="${pageContext.request.contextPath}" />

                        B&uacute;squeda Avanzada de Alumnos
                        <br />
                        <br />
                        <fieldset>
                            <legend>
                                B&uacute;squeda por Alumno
                            </legend>
                            Nombre Alumno:
                            <input type="text" name="nombreAlumno" size="50">
                            <br>
                            Apellido Paterno:
                            <input type="text" name="apellidoPaterno" size="50">
                            <br>
                            Apellido Materno:
                            <input type="text" name="apellidoMaterno" size="50">
                        </fieldset>
                        <fieldset>
                            <legend>
                                <br>
                                B&uacute;squeda por datos de Contacto
                            </legend>
                            Tel&eacute;fono:
                            <input type="text" name="telefono" size="50">
                            <br>
                            Email:
                            <input type="text" name="email" size="50">
                        </fieldset>
                        <fieldset>
                            <legend>
                                <br>
                                B&uacute;squeda por datos de Domicilio
                            </legend>
                            Calle:
                            <input type="text" name="calle" size="50">
                        </fieldset>
                        <fieldset>
                            <legend>
                                <br>
                                B&uacute;squeda por Curso
                            </legend>
                            Nombre Curso:
                            <input type="text" name="nombreCurso" size="50">
                        </fieldset>
                        <br />
                        <input type="submit" value="Buscar">

                        <input type="button" value="Cancelar" onclick="cancelar();" />
                    </form>
                </div>
            </div>
            <div id="footer">
                <jsp:include page="/WEB-INF/pages/commons/footer.jsp" />
            </div>
        </div>
    </body>
</html>