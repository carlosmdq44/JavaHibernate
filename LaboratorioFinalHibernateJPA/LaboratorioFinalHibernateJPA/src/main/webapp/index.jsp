<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema SGA</title>
  </head>
  <body>
    <div id="wrap">
      <div id="header">
        <jsp:include page="/WEB-INF/pages/commons/header.jsp"/>
      </div>
      <div id="nav" >
        <jsp:include page="/WEB-INF/pages/commons/menu.jsp"/>
      </div>
      <div id="main">
        <div class="subtitulo">
          <a href="${pageContext.request.contextPath}/ServletControlador?accion=listarAlumnos">
            Entrar al Sistema
          </a>
        </div>
      </div>
      <div id="footer">
        <jsp:include page="/WEB-INF/pages/commons/footer.jsp"/>
      </div>
    </div>
  </body>
</html>