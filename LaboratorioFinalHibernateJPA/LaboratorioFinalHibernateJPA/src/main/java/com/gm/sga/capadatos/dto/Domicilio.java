package com.gm.sga.capadatos.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "domicilio")
@NamedQueries({
    @NamedQuery(name = "Domicilio.findAll", query = "SELECT d FROM Domicilio d")})
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_domicilio")
    private Integer idDomicilio;

    @Basic(optional = false)
    @Column(name = "calle")
    private String calle;

    @Column(name = "no_externo")
    private String noExterno;

    @Column(name = "no_interno")
    private String noInterno;

    @Column(name = "cp")
    private Integer cp;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @Column(name = "version")
    private Integer version;

    @Column(name = "deleted")
    private Integer deleted;

    @OneToMany(mappedBy = "domicilio")
    private List<Instructor> instructorList;

    @OneToMany(mappedBy = "domicilio")
    private List<Alumno> alumnoList;

    public Domicilio() {
    }

    public Domicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public Domicilio(Integer idDomicilio, String calle) {
        this.idDomicilio = idDomicilio;
        this.calle = calle;
    }

    public Integer getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(Integer idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExterno() {
        return noExterno;
    }

    public void setNoExterno(String noExterno) {
        this.noExterno = noExterno;
    }

    public String getNoInterno() {
        return noInterno;
    }

    public void setNoInterno(String noInterno) {
        this.noInterno = noInterno;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
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
        hash += (idDomicilio != null ? idDomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Domicilio)) {
            return false;
        }
        Domicilio other = (Domicilio) object;
        if ((this.idDomicilio == null && other.idDomicilio != null) || (this.idDomicilio != null && !this.idDomicilio.equals(other.idDomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Domicilio{" + "idDomicilio=" + idDomicilio + ", calle=" + calle + ", noExterno=" + noExterno + ", noInterno=" + noInterno + ", cp=" + cp + ", municipio=" + municipio + ", estado=" + estado + ", pais=" + pais + ", version=" + version + ", deleted=" + deleted + '}';
    }
}