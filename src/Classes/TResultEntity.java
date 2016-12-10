package Classes;

import javax.persistence.*;

/**
 * Created by root on 6/10/16.
 */
@Entity
@Table(name = "TResult", schema = "pfa", catalog = "")
public class TResultEntity {
    private int idResult;
    private String result;
    private TBallotEntity tBallotByIdBallot;

    @Id
    @Column(name = "idResult")
    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TResultEntity that = (TResultEntity) o;

        if (idResult != that.idResult) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = idResult;
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
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
        return "TResultEntity{" +
                "idResult=" + idResult +
                ", result='" + result + '\'' +
                '}';
    }
}
