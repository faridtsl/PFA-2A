package Classes;

import javax.persistence.*;

/**
 * Created by root on 6/10/16.
 */
@Entity
@Table(name = "TUser", schema = "pfa", catalog = "")
public class TUserEntity {
    private int idUser;
    private String username;
    private String mdp;
    private TVoterEntity tVoterByIdVoter;

    @Id
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "mdp")
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TUserEntity that = (TUserEntity) o;

        if (idUser != that.idUser) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (mdp != null ? !mdp.equals(that.mdp) : that.mdp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (mdp != null ? mdp.hashCode() : 0);
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

    @Override
    public String toString() {
        return "TUserEntity{" +
                "mdp='" + mdp + '\'' +
                ", username='" + username + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
