package Classes;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by root on 6/10/16.
 */
@Entity
@Table(name = "TAdministrator", schema = "pfa")
public class TAdministratorEntity {
    private int idAdmin;
    private String secretKey;
    private TVoterEntity tVoterByIdVoter;
    private Collection<TBallotEntity> tBallotsByIdAdmin;

    @Id
    @Column(name = "idAdmin")
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Basic
    @Column(name = "secretKey")
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TAdministratorEntity that = (TAdministratorEntity) o;

        if (idAdmin != that.idAdmin) return false;
        if (secretKey != null ? !secretKey.equals(that.secretKey) : that.secretKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdmin;
        result = 31 * result + (secretKey != null ? secretKey.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idVoter", referencedColumnName = "idVoter")
    public TVoterEntity gettVoterByIdVoter() {
        return tVoterByIdVoter;
    }

    public void settVoterByIdVoter(TVoterEntity tVoterByIdVoter) {
        this.tVoterByIdVoter = tVoterByIdVoter;
    }

    @OneToMany(mappedBy = "tAdministratorByIdAdmin")
    public Collection<TBallotEntity> gettBallotsByIdAdmin() {
        return tBallotsByIdAdmin;
    }

    public void settBallotsByIdAdmin(Collection<TBallotEntity> tBallotsByIdAdmin) {
        this.tBallotsByIdAdmin = tBallotsByIdAdmin;
    }

    @Override
    public String toString() {
        return "TAdministratorEntity{" +
                "idAdmin=" + idAdmin +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
