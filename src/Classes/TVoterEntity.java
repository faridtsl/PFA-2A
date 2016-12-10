package Classes;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by root on 6/10/16.
 */
@Entity
@Table(name = "TVoter", schema = "pfa", catalog = "")
public class TVoterEntity {
    private int idVoter;
    private String idPrime;
    private String cin;
    private String nom;
    private String prenom;
    private String numTel;
    private String email;
    private Collection<TAdministratorEntity> tAdministratorsByIdVoter;
    private Collection<TVoteEntity> tVotesByIdVoter;

    @Id
    @Column(name = "idVoter")
    public int getIdVoter() {
        return idVoter;
    }

    public void setIdVoter(int idVoter) {
        this.idVoter = idVoter;
    }

    @Basic
    @Column(name = "idPrime")
    public String getIdPrime() {
        return idPrime;
    }

    public void setIdPrime(String idPrime) {
        this.idPrime = idPrime;
    }

    @Basic
    @Column(name = "cin")
    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Basic
    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "numTel")
    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TVoterEntity that = (TVoterEntity) o;

        if (idVoter != that.idVoter) return false;
        if (idPrime != null ? !idPrime.equals(that.idPrime) : that.idPrime != null) return false;
        if (cin != null ? !cin.equals(that.cin) : that.cin != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (numTel != null ? !numTel.equals(that.numTel) : that.numTel != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVoter;
        result = 31 * result + (idPrime != null ? idPrime.hashCode() : 0);
        result = 31 * result + (cin != null ? cin.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (numTel != null ? numTel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tVoterByIdVoter")
    public Collection<TAdministratorEntity> gettAdministratorsByIdVoter() {
        return tAdministratorsByIdVoter;
    }

    public void settAdministratorsByIdVoter(Collection<TAdministratorEntity> tAdministratorsByIdVoter) {
        this.tAdministratorsByIdVoter = tAdministratorsByIdVoter;
    }

    @OneToMany(mappedBy = "tVoterByIdVoter")
    public Collection<TVoteEntity> gettVotesByIdVoter() {
        return tVotesByIdVoter;
    }

    public void settVotesByIdVoter(Collection<TVoteEntity> tVotesByIdVoter) {
        this.tVotesByIdVoter = tVotesByIdVoter;
    }

    @Override
    public String toString() {
        return "TVoterEntity{" +
                "email='" + email + '\'' +
                ", numTel='" + numTel + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", cin='" + cin + '\'' +
                ", idPrime='" + idPrime + '\'' +
                ", idVoter=" + idVoter +
                '}';
    }
}
