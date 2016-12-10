package Classes;

import javax.persistence.*;

/**
 * Created by root on 6/10/16.
 */
@Entity
@Table(name = "TOption", schema = "pfa", catalog = "")
public class TOptionEntity {
    private int idOption;
    private String idPrime;
    private String libOption;
    private TBallotEntity tBallotByIdBallot;

    @Id
    @Column(name = "idOption")
    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
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
    @Column(name = "libOption")
    public String getLibOption() {
        return libOption;
    }

    public void setLibOption(String libOption) {
        this.libOption = libOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TOptionEntity that = (TOptionEntity) o;

        if (idOption != that.idOption) return false;
        if (idPrime != null ? !idPrime.equals(that.idPrime) : that.idPrime != null) return false;
        if (libOption != null ? !libOption.equals(that.libOption) : that.libOption != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOption;
        result = 31 * result + (idPrime != null ? idPrime.hashCode() : 0);
        result = 31 * result + (libOption != null ? libOption.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idBallot", referencedColumnName = "idBallot")
    public TBallotEntity gettBallotByIdBallot() {
        return tBallotByIdBallot;
    }

    public void settBallotByIdBallot(TBallotEntity tBallotByIdBallot) {
        this.tBallotByIdBallot = tBallotByIdBallot;
    }

    @Override
    public String toString() {
        return "TOptionEntity{" +
                "idOption=" + idOption +
                ", idPrime='" + idPrime + '\'' +
                ", libOption='" + libOption + '\'' +
                '}';
    }
}
