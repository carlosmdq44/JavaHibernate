package com.gm.sga.capadatos.dto;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "pago")
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p")})
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pago")
    private Integer idPago;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private Float monto;

    @Column(name = "tipo_forma_pago")
    private String tipoFormaPago;

    @Column(name = "pago_parcial")
    private String pagoParcial;

    @Basic(optional = false)
    @Column(name = "version")
    private int version;

    @Basic(optional = false)
    @Column(name = "deleted")
    private int deleted;

    @JoinColumn(name = "id_asignacion", referencedColumnName = "id_asignacion")
    @ManyToOne
    private Asignacion asignacion;

    public Pago() {
    }

    public Pago(Integer idPago) {
        this.idPago = idPago;
    }

    public Pago(Integer idPago, int version, int deleted) {
        this.idPago = idPago;
        this.version = version;
        this.deleted = deleted;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getTipoFormaPago() {
        return tipoFormaPago;
    }

    public void setTipoFormaPago(String tipoFormaPago) {
        this.tipoFormaPago = tipoFormaPago;
    }

    public String getPagoParcial() {
        return pagoParcial;
    }

    public void setPagoParcial(String pagoParcial) {
        this.pagoParcial = pagoParcial;
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

    public Asignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pago{" + "idPago=" + idPago + ", monto=" + monto + ", tipoFormaPago=" + tipoFormaPago + ", pagoParcial=" + pagoParcial + ", version=" + version + ", deleted=" + deleted + ", asignacion=" + asignacion + '}';
    }
}