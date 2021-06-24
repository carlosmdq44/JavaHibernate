package com.gm.sga.capadatos.dto;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "asignacion")
@NamedQueries({
    @NamedQuery(name = "Asignacion.findAll", query = "SELECT a FROM Asignacion a")})
public class Asignacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignacion")
    private Integer idAsignacion;

    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Column(name = "fecha_termino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTermino;

    @Column(name = "horario")
    private String horario;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_alumno")
    private Float precioAlumno;

    @Column(name = "tipo_cuota")
    private String tipoCuota;

    @Column(name = "tipo_esquema")
    private String tipoEsquema;

    @Column(name = "tipo_promocion")
    private String tipoPromocion;

    @Column(name = "tipo_descuento")
    private String tipoDescuento;

    @Column(name = "tipo_metodologia")
    private String tipoMetodologia;

    @Column(name = "fecha_pago_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPagoInicial;

    @Column(name = "equipo_computo")
    private Short equipoComputo;

    @Basic(optional = false)
    @Column(name = "version")
    private int version;

    @Basic(optional = false)
    @Column(name = "deleted")
    private int deleted;

    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    @ManyToOne
    private Alumno alumno;

    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne
    private Curso curso;

    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne
    private Sucursal sucursal;

    @OneToMany(mappedBy = "asignacion")
    private List<Pago> pagoList;

    public Asignacion() {
    }

    public Asignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Asignacion(Integer idAsignacion, int version, int deleted) {
        this.idAsignacion = idAsignacion;
        this.version = version;
        this.deleted = deleted;
    }

    public Integer getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(Integer idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Float getPrecioAlumno() {
        return precioAlumno;
    }

    public void setPrecioAlumno(Float precioAlumno) {
        this.precioAlumno = precioAlumno;
    }

    public String getTipoCuota() {
        return tipoCuota;
    }

    public void setTipoCuota(String tipoCuota) {
        this.tipoCuota = tipoCuota;
    }

    public String getTipoEsquema() {
        return tipoEsquema;
    }

    public void setTipoEsquema(String tipoEsquema) {
        this.tipoEsquema = tipoEsquema;
    }

    public String getTipoPromocion() {
        return tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public String getTipoMetodologia() {
        return tipoMetodologia;
    }

    public void setTipoMetodologia(String tipoMetodologia) {
        this.tipoMetodologia = tipoMetodologia;
    }

    public Date getFechaPagoInicial() {
        return fechaPagoInicial;
    }

    public void setFechaPagoInicial(Date fechaPagoInicial) {
        this.fechaPagoInicial = fechaPagoInicial;
    }

    public Short getEquipoComputo() {
        return equipoComputo;
    }

    public void setEquipoComputo(Short equipoComputo) {
        this.equipoComputo = equipoComputo;
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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignacion != null ? idAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Asignacion)) {
            return false;
        }
        Asignacion other = (Asignacion) object;
        if ((this.idAsignacion == null && other.idAsignacion != null) || (this.idAsignacion != null && !this.idAsignacion.equals(other.idAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Asignacion{" + "idAsignacion=" + idAsignacion + ", fechaInicio=" + fechaInicio + ", fechaTermino=" + fechaTermino + ", horario=" + horario + ", precioAlumno=" + precioAlumno + ", tipoCuota=" + tipoCuota + ", tipoEsquema=" + tipoEsquema + ", tipoPromocion=" + tipoPromocion + ", tipoDescuento=" + tipoDescuento + ", tipoMetodologia=" + tipoMetodologia + ", fechaPagoInicial=" + fechaPagoInicial + ", equipoComputo=" + equipoComputo + ", version=" + version + ", deleted=" + deleted + ", alumno=" + alumno + ", curso=" + curso + ", sucursal=" + sucursal + '}';
    }

}