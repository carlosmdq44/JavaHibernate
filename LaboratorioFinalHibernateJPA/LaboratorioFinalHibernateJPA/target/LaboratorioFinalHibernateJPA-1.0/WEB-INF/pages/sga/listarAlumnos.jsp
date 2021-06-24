<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Listado de Alumnos</title>
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

                <!--Es importante definir el id de la forma, ya que se validara con JavaScript -->
                <form id="form1" name="form1"
                      action="${pageContext.request.contextPath}/ServletControlador"
                      method="post">

                    <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                    <input type="hidden" name="accion" id="accion" />

                    <div class="subtitulo">
                        Listado de Alumnos
                    </div>

                    <c:if test="${not empty alumnos}">
                        <div class="tabla">
                            <table align="center" class="elemento">
                                <tr>
                                    <th>
                                        <input type="checkbox" name="controladorCheckbox"
                                               onclick="selectAllCheckboxes(this);" />
                                    </th>
                                    <th>Nombre</th>
                                    <th>Tel&eacute;fono</th>
                                    <th>Email</th>
                                    <th>Domicilio</th>
                                    <th>Cursos Asignados</th>
                                </tr>

                                <c:forEach var="alumno" items="${alumnos}"
                                           varStatus="row">

                                    <tr class="${ (row.count % 2) == 0 ? "row1" : "row2" }" >
                                        <td align="center">
                                            <input type="checkbox" name="alumnos" id="alumnos"
                                                   value="${alumno.idAlumno}">
                                        </td>
                                        <td onclick="editaRegistro(${row.count});">
                                            ${alumno.nombre} ${alumno.apellidoPaterno} ${alumno.apellidoMaterno}
                                        </td>
                                        <td>
                                            ${alumno.contacto.telefono }
                                        </td>
                                        <td>
                                            ${alumno.contacto.email1 }
                                        </td>
                                         <td>
                                            ${alumno.domicilio.calle }
                                        </td>
                                        <td>
                                            <ul>
                                                <c:forEach var="asig" items="${alumno.asignaciones}">
                                                    <li>
                                                        ${asig.curso.nombre}
                                                    </li>
                                                </c:forEach>
                                                &nbsp;
                                            </ul>
                                        </td>
                                    </tr>

                                </c:forEach>

                            </table>

                            <input id="botonAgregar" type="button" value="Agregar"
                                   onclick="validaFormularioListadoAlumnos(this);">
                            <input id="botonEditar" type="button" value="Editar"
                                   onclick="validaFormularioListadoAlumnos(this);">
                            <input id="botonEliminar" type="button" value="Eliminar"
                                   onclick="validaFormularioListadoAlumnos(this);">
                            <input id="botonBuscar" type="button" value="Buscar"
                                   onclick="validaFormularioListadoAlumnos(this);">

                        </div>
                    </c:if>
                    <c:if test="${empty alumnos}">
                        <br />
                        No se encontraron alumnos
                        <br/>
                        <input id="botonBuscar" type="button" value="Buscar"
                               onclick="validaFormularioListadoAlumnos(this);">
                    </c:if>
                </form>
            </div>
            <div id="footer">
                <jsp:include page="/WEB-INF/pages/commons/footer.jsp" />
            </div>
        </div>
    </body>
</html>