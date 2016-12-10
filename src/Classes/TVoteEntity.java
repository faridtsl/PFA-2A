package Classes;

import javax.persistence.*;

/**
 * Created by root on 6/10/16.
 */
@Entity
@Table(name = "TVote", schema = "pfa", catalog = "")
public class TVoteEntity {
    private int idVote;
    private TBallotEntity tBallotByIdBallot;
    private TOptionEntity tOptionByIdOption;
    private TVoterEntity tVoterByIdVoter;

    @Id
    @Column(name = "idVote")
    public int getIdVote() {
        return idVote;
    }

    public void setIdVote(int idVote) {
        this.idVote = idVote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TVoteEntity that = (TVoteEntity) o;

        if (idVote != that.idVote) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idVote;
    }

    @ManyToOne
    @JoinColumn(name = "idBallot", referencedColumnName = "idBallot")
    public TBallotEntity gettBallotByIdBallot() {
        return tBallotByIdBallot;
    }

    public void settBallotByIdBallot(TBallotEntity tBallotByIdBallot) {
        this.tBallotByIdBallot = tBallotByIdBallot;
    }

    @ManyToOne
    @JoinColumn(name = "idOption", referencedColumnName = "idOption")
    public TOptionEntity gettOptionByIdOption() {
        return tOptionByIdOption;
    }

    public void settOptionByIdOption(TOptionEntity tOptionByIdOption) {
        this.tOptionByIdOption = tOptionByIdOption;
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
        return "TVoteEntity{" +
                "idVote=" + idVote +
                ", tBallotByIdBallot=" + tBallotByIdBallot +
                ", tOptionByIdOption=" + tOptionByIdOption +
                ", tVoterByIdVoter=" + tVoterByIdVoter +
                '}';
    }
}
