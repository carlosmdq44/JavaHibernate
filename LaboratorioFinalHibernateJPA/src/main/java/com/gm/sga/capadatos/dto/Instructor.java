package com.gm.sga.capadatos.dto;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "instructor")
@NamedQueries({
    @NamedQuery(name = "Instructor.findAll", query = "SELECT i FROM Instructor i")})
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_instructor")
    private Integer idInstructor;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "genero")
    private String genero;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    @Basic(optional = false)
    @Column(name = "version")
    private int version;

    @Basic(optional = false)
    @Column(name = "deleted")
    private int deleted;

    @JoinColumn(name = "id_contacto", referencedColumnName = "id_contacto")
    @ManyToOne
    private Contacto contacto;

    @JoinColumn(name = "id_domicilio", referencedColumnName = "id_domicilio")
    @ManyToOne
    private Domicilio domicilio;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "instructor")
    private List<Curso> cursoList;

    public Instructor() {
    }

    public Instructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
    }

    public Instructor(Integer idInstructor, String nombre, String apellidoPaterno, int version, int deleted) {
        this.idInstructor = idInstructor;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.version = version;
        this.deleted = deleted;
    }

    public Integer getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public Contacto getIdContacto() {
        return contacto;
    }

    public void setIdContacto(Contacto idContacto) {
        this.contacto = idContacto;
    }

    public Domicilio getIdDomicilio() {
        return domicilio;
    }

    public void setIdDomicilio(Domicilio idDomicilio) {
        this.domicilio = idDomicilio;
    }

    public Usuario getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.usuario = idUsuario;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstructor != null ? idInstructor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) object;
        if ((this.idInstructor == null && other.idInstructor != null) || (this.idInstructor != null && !this.idInstructor.equals(other.idInstructor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Instructor{" + "idInstructor=" + idInstructor + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", genero=" + genero + ", fechaNacimiento=" + fechaNacimiento + ", version=" + version + ", deleted=" + deleted + ", contacto=" + contacto + ", domicilio=" + domicilio + ", usuario=" + usuario + '}';
    }
}