package com.gm.sga.capadatos.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "curso")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "horario")
    private String horario;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Float precio;

    @Basic(optional = false)
    @Column(name = "version")
    private int version;

    @Basic(optional = false)
    @Column(name = "deleted")
    private int deleted;

    @OneToMany(mappedBy = "curso")
    private List<Asignacion> asignacionList;

    @JoinColumn(name = "id_instructor", referencedColumnName = "id_instructor")
    @ManyToOne
    private Instructor instructor;

    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(Integer idCurso, String nombre, int version, int deleted) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.version = version;
        this.deleted = deleted;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public List<Asignacion> getAsignacionList() {
        return asignacionList;
    }

    public void setAsignacionList(List<Asignacion> asignacionList) {
        this.asignacionList = asignacionList;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curso{" + "idCurso=" + idCurso + ", nombre=" + nombre + ", horario=" + horario + ", precio=" + precio + ", version=" + version + ", deleted=" + deleted + ", instructor=" + instructor + '}';
    }
}