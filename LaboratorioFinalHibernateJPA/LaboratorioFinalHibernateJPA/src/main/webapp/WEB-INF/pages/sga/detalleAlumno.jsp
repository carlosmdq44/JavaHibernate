<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Detalle del Alumno</title>
  </head>
  <body>
    <div id="wrap">
      <div id="header">
        <jsp:include page="/WEB-INF/pages/commons/header.jsp"/>
      </div>

      <div id="nav">
        <jsp:include page="/WEB-INF/pages/commons/menu.jsp"/>
      </div>

      <div id="msg">
        <jsp:include page="/WEB-INF/pages/commons/messages.jsp"/>
      </div>

      <div id="main">

        <div class="subtitulo">
          Detalle Alumno
        </div>

        <div class="formulario">
          <!--Es importante definir el id de la forma, ya que se validara con JavaScript -->
          <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >

            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
            <input type="hidden" name="accion"  id="accion" value="guardarAlumno"/>

            <!--nos va a servir para que javascript tome el valor dinamicamente
            del nombre de la aplicacion-->
            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>


            <!--Id persona. Si estamos editando, reenviamos el id_persona al servidor
            Esto nos permitirá distinguir si estamos Agregando(insert) o
            modificando (update) -->
            <input type="hidden" name="idAlumno" value="${alumno.idAlumno}" />

            <table align="center" class="elemento">
              <tr>
                <td>Nombre:</td>
                <td><input type="text" name="nombre" value="${alumno.nombre}" /></td>
              </tr>
              <tr>
                <td>Apellido Paterno:</td>
                <td><input type="text" name="apellidoPaterno" value="${alumno.apellidoPaterno}" /></td>
              </tr>
              <tr>
                <td>Apellido Materno:</td>
                <td><input type="text" name="apellidoMaterno" value="${alumno.apellidoMaterno}" /></td>
              </tr>
              <tr>
                <td>Domicilio:</td>
                <td><input type="text" name="calle" value="${alumno.domicilio.calle}" /></td>
              </tr>
              <tr>
                <td>Email:</td>
                <td><input type="text" name="email" value="${alumno.contacto.email1}" /></td>
              </tr>
               <tr>
                <td>Tel&eacute;fono:</td>
                <td><input type="text" name="telefono" value="${alumno.contacto.telefono}" /></td>
              </tr>
            </table>

            <input type="submit" value="Guardar" />
            <input type="button" value="Cancelar"  onclick="cancelar();" />
          </form>
        </div>
      </div>
      <div id="footer">
        <jsp:include page="/WEB-INF/pages/commons/footer.jsp"/>
      </div>
    </div>
  </body>
</html>