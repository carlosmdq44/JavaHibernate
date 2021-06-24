package com.gm.sga.capadatos.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "contacto")
@NamedQueries({
    @NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c")})
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contacto")
    private Integer idContacto;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "celular")
    private String celular;

    @Column(name = "oficina")
    private String oficina;

    @Column(name = "extension")
    private String extension;

    @Basic(optional = false)
    @Column(name = "email1")
    private String email1;

    @Column(name = "email2")
    private String email2;

    @Basic(optional = false)
    @Column(name = "version")
    private int version;

    @Basic(optional = false)
    @Column(name = "deleted")
    private int deleted;

    @OneToMany(mappedBy = "contacto")
    private List<Instructor> instructorList;

    @OneToMany(mappedBy = "contacto")
    private List<Alumno> alumnoList;

    public Contacto() {
    }

    public Contacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public Contacto(Integer idContacto, String email1, int version, int deleted) {
        this.idContacto = idContacto;
        this.email1 = email1;
        this.version = version;
        this.deleted = deleted;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
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

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContacto != null ? idContacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        if ((this.idContacto == null && other.idContacto != null) || (this.idContacto != null && !this.idContacto.equals(other.idContacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contacto{" + "idContacto=" + idContacto + ", telefono=" + telefono + ", celular=" + celular + ", oficina=" + oficina + ", extension=" + extension + ", email1=" + email1 + ", email2=" + email2 + ", version=" + version + ", deleted=" + deleted + '}';
    }

}