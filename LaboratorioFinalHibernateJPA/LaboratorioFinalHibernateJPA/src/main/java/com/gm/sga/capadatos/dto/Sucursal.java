package com.gm.sga.capadatos.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "sucursal")
@NamedQueries({
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s")})
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private Integer idSucursal;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "version")
    private int version;

    @Basic(optional = false)
    @Column(name = "deleted")
    private int deleted;

    @OneToMany(mappedBy = "sucursal")
    private List<Asignacion> asignacionList;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Sucursal(Integer idSucursal, String nombre, int version, int deleted) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.version = version;
        this.deleted = deleted;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSucursal != null ? idSucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.idSucursal == null && other.idSucursal != null) || (this.idSucursal != null && !this.idSucursal.equals(other.idSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sucursal{" + "idSucursal=" + idSucursal + ", nombre=" + nombre + ", version=" + version + ", deleted=" + deleted + '}';
    }

}