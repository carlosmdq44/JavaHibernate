<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Listado de Alumnos</title>
    </head>
    <body>
        Listar Alumnos
        <table border="1">
            <caption>
                Listar Alumnos
            </caption>
            <tr>
                <th> Alumno ID </tr>
            <th> Nombre </tr>
            <th>Domicilio </tr>
            <th> Email </tr>
            <td> Telefono </td>
        </tr>
        <c:forEach var = "alumno" items="${alumnos}">
            <<tr>
                <<td>${alumno.idAlumno}</td>
                <<td>${alumno.idAlumno} ${alumno.apellido}</td>
                <<td>${alumno.domicilio.calle} ${alumno.domicilio.noCalle} ${alumno.domicilio.pais}</td>
                <<td>${alumno.contacto.email}</td>
                <<td>${alumno.contacto.telefono}</td>
            </tr>   
    </body>
</body>
</html>
