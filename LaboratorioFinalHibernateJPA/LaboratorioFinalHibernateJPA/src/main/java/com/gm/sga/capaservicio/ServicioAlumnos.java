package com.gm.sga.capaservicio;

import com.gm.sga.capadatos.dao.AlumnoDAO;
import com.gm.sga.capadatos.dto.Alumno;
import java.util.List;
import java.util.Map;

public class ServicioAlumnos {

    // El Dao se asocia con la clase
    AlumnoDAO alumnoDao = new AlumnoDAO();

    // Esta clase se comunica con los DAO's que necesite
    public List<Alumno> listarAlumnos() {
        return alumnoDao.listarAlumnos();
    }

    public boolean guardarAlumno(Alumno alumno) {
        if (alumno != null && alumno.getIdAlumno() == null) {
            //insert
            alumnoDao.insertar(alumno);
        } else {
            //update
            alumnoDao.actualizar(alumno);
        }

        return true;// si nada fallo, regresamos verdadero
    }

    public boolean eliminarAlumno(Integer idAlumno) {
        //delete
        alumnoDao.eliminar(new Alumno(idAlumno));
        return true;// si nada falla regresamos verdadero
    }

    public Alumno encontrarAlumno(Integer idAlumno) {
        return alumnoDao.buscarPorId(new Alumno(idAlumno));
    }

    public List<Alumno> encontrarAlumnosPorCriterios(Map criterios) {
        alumnoDao = new AlumnoDAO();
        return alumnoDao.buscarAlumnosPorCriterios(criterios);
    }

    public boolean eliminarAlumnos(List<Integer> idAlumnos) {
        for (Integer idAlumno : idAlumnos) {
            this.eliminarAlumno(idAlumno);
        }
        return true;
    }
}