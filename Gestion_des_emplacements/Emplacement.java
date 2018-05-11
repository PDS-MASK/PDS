/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_des_emplacements;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Pc-Pc
 */
@Entity
@Table(name = "EMPLACEMENT", catalog = "", schema = "SAMI")
@NamedQueries({
    @NamedQuery(name = "Emplacement.findAll", query = "SELECT e FROM Emplacement e")
    , @NamedQuery(name = "Emplacement.findByIdEmplacement", query = "SELECT e FROM Emplacement e WHERE e.idEmplacement = :idEmplacement")
    , @NamedQuery(name = "Emplacement.findByNomEmplacement", query = "SELECT e FROM Emplacement e WHERE e.nomEmplacement = :nomEmplacement")
    , @NamedQuery(name = "Emplacement.findByDisponibilite", query = "SELECT e FROM Emplacement e WHERE e.disponibilite = :disponibilite")
    , @NamedQuery(name = "Emplacement.findBySuperficie", query = "SELECT e FROM Emplacement e WHERE e.superficie = :superficie")
    , @NamedQuery(name = "Emplacement.findByRedevance", query = "SELECT e FROM Emplacement e WHERE e.redevance = :redevance")
    , @NamedQuery(name = "Emplacement.findByEtage", query = "SELECT e FROM Emplacement e WHERE e.etage = :etage")})
public class Emplacement implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EMPLACEMENT")
    private BigDecimal idEmplacement;
    @Column(name = "NOM_EMPLACEMENT")
    private String nomEmplacement;
    @Basic(optional = false)
    @Column(name = "DISPONIBILITE")
    private BigInteger disponibilite;
    @Basic(optional = false)
    @Column(name = "SUPERFICIE")
    private double superficie;
    @Basic(optional = false)
    @Column(name = "REDEVANCE")
    private double redevance;
    @Basic(optional = false)
    @Column(name = "ETAGE")
    private BigInteger etage;

    public Emplacement() {
    }

    public Emplacement(BigDecimal idEmplacement) {
        this.idEmplacement = idEmplacement;
    }

    public Emplacement(BigDecimal idEmplacement, BigInteger disponibilite, double superficie, double redevance, BigInteger etage) {
        this.idEmplacement = idEmplacement;
        this.disponibilite = disponibilite;
        this.superficie = superficie;
        this.redevance = redevance;
        this.etage = etage;
    }

    public BigDecimal getIdEmplacement() {
        return idEmplacement;
    }

    public void setIdEmplacement(BigDecimal idEmplacement) {
        BigDecimal oldIdEmplacement = this.idEmplacement;
        this.idEmplacement = idEmplacement;
        changeSupport.firePropertyChange("idEmplacement", oldIdEmplacement, idEmplacement);
    }

    public String getNomEmplacement() {
        return nomEmplacement;
    }

    public void setNomEmplacement(String nomEmplacement) {
        String oldNomEmplacement = this.nomEmplacement;
        this.nomEmplacement = nomEmplacement;
        changeSupport.firePropertyChange("nomEmplacement", oldNomEmplacement, nomEmplacement);
    }

    public BigInteger getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(BigInteger disponibilite) {
        BigInteger oldDisponibilite = this.disponibilite;
        this.disponibilite = disponibilite;
        changeSupport.firePropertyChange("disponibilite", oldDisponibilite, disponibilite);
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        double oldSuperficie = this.superficie;
        this.superficie = superficie;
        changeSupport.firePropertyChange("superficie", oldSuperficie, superficie);
    }

    public double getRedevance() {
        return redevance;
    }

    public void setRedevance(double redevance) {
        double oldRedevance = this.redevance;
        this.redevance = redevance;
        changeSupport.firePropertyChange("redevance", oldRedevance, redevance);
    }

    public BigInteger getEtage() {
        return etage;
    }

    public void setEtage(BigInteger etage) {
        BigInteger oldEtage = this.etage;
        this.etage = etage;
        changeSupport.firePropertyChange("etage", oldEtage, etage);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmplacement != null ? idEmplacement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emplacement)) {
            return false;
        }
        Emplacement other = (Emplacement) object;
        if ((this.idEmplacement == null && other.idEmplacement != null) || (this.idEmplacement != null && !this.idEmplacement.equals(other.idEmplacement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestion_des_emplacements.Emplacement[ idEmplacement=" + idEmplacement + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
