package Classes;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by root on 6/10/16.
 */
@Entity
@Table(name = "TBallot", schema = "pfa")
public class TBallotEntity {
    private int idBallot;
    private String libBallot;
    private String publicKey;
    private Integer isPublic;
    private Date endDate;
    private TAdministratorEntity tAdministratorByIdAdmin;
    private Collection<TOptionEntity> tOptionsByIdBallot;
    private Collection<TResultEntity> tResultsByIdBallot;
    private Collection<TVoteEntity> tVotesByIdBallot;

    @Id
    @Column(name = "idBallot")
    public int getIdBallot() {
        return idBallot;
    }

    public void setIdBallot(int idBallot) {
        this.idBallot = idBallot;
    }

    @Basic
    @Column(name = "libBallot")
    public String getLibBallot() {
        return libBallot;
    }

    public void setLibBallot(String libBallot) {
        this.libBallot = libBallot;
    }

    @Basic
    @Column(name = "publicKey")
    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Basic
    @Column(name = "isPublic")
    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    @Basic
    @Column(name = "endDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TBallotEntity that = (TBallotEntity) o;

        if (idBallot != that.idBallot) return false;
        if (libBallot != null ? !libBallot.equals(that.libBallot) : that.libBallot != null) return false;
        if (publicKey != null ? !publicKey.equals(that.publicKey) : that.publicKey != null) return false;
        if (isPublic != null ? !isPublic.equals(that.isPublic) : that.isPublic != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBallot;
        result = 31 * result + (libBallot != null ? libBallot.hashCode() : 0);
        result = 31 * result + (publicKey != null ? publicKey.hashCode() : 0);
        result = 31 * result + (isPublic != null ? isPublic.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idAdmin", referencedColumnName = "idAdmin")
    public TAdministratorEntity gettAdministratorByIdAdmin() {
        return tAdministratorByIdAdmin;
    }

    public void settAdministratorByIdAdmin(TAdministratorEntity tAdministratorByIdAdmin) {
        this.tAdministratorByIdAdmin = tAdministratorByIdAdmin;
    }

    @OneToMany(mappedBy = "tBallotByIdBallot")
    public Collection<TOptionEntity> gettOptionsByIdBallot() {
        return tOptionsByIdBallot;
    }

    public void settOptionsByIdBallot(Collection<TOptionEntity> tOptionsByIdBallot) {
        this.tOptionsByIdBallot = tOptionsByIdBallot;
    }

    @OneToMany(mappedBy = "tBallotByIdBallot")
    public Collection<TResultEntity> gettResultsByIdBallot() {
        return tResultsByIdBallot;
    }

    public void settResultsByIdBallot(Collection<TResultEntity> tResultsByIdBallot) {
        this.tResultsByIdBallot = tResultsByIdBallot;
    }

    @OneToMany(mappedBy = "tBallotByIdBallot")
    public Collection<TVoteEntity> gettVotesByIdBallot() {
        return tVotesByIdBallot;
    }

    public void settVotesByIdBallot(Collection<TVoteEntity> tVotesByIdBallot) {
        this.tVotesByIdBallot = tVotesByIdBallot;
    }

    @Override
    public String toString() {
        return "TBallotEntity{" +
                "idBallot=" + idBallot +
                ", libBallot='" + libBallot + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", isPublic=" + isPublic +
                ", endDate=" + endDate +
                '}';
    }
}
