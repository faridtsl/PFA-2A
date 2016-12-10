package Services;

import Classes.*;
import Model.*;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by root on 6/11/16.
 */
public class BallotService {

    public Integer createBallot(String lib, int isPub, int idUser, Date endDate){
        RSA r = new RSA(1024);
        TBallotEntity b = new TBallotEntity();
        b.setEndDate(new java.sql.Date(endDate.getTime()));
        b.setIsPublic(isPub);
        b.setLibBallot(lib);
        TAdministratorEntity a = new TAdministratorEntity();
        a.settVoterByIdVoter(new TVoterModel().getVoterById(idUser));
        a.setSecretKey(r.getD().toString());
        int idAdmin = new TAdministratorModel().addAdministrator(a);
        b.settAdministratorByIdAdmin(new TAdministratorModel().getAdminById(idAdmin));
        b.setPublicKey(r.getE()+";"+r.getN());
        return new TBallotModel().addBallot(b);
    }

    public void addOptions(Integer idBallot, List<String> options){
        BigInteger oP = BigInteger.valueOf(3);
        TBallotEntity b = new TBallotModel().getBallotById(idBallot);
        TOptionModel opMod = new TOptionModel();
        for(String s : options){
            TOptionEntity o = new TOptionEntity();
            o.setIdPrime(oP.toString());
            o.setLibOption(s);
            o.settBallotByIdBallot(b);
            oP = oP.nextProbablePrime();
            opMod.addOption(o);
        }
    }

    public void castVote(Integer ballotId,Integer voterId, BigInteger vote){
        TBallotEntity b = new TBallotModel().getBallotById(ballotId);
        TVoterEntity v = new TVoterModel().getVoterById(voterId);
        TAdministratorEntity a = new TAdministratorModel().getAdminById(b.gettAdministratorByIdAdmin().getIdAdmin());
        Collection<TResultEntity> lh =  new TResultModel().getResultsByBallot(b.getIdBallot());
        if(lh == null || lh.size() == 0){
            TResultEntity r = new TResultEntity();
            r.settBallotByIdBallot(b);
            r.setResult(vote.toString());
            new TResultModel().addResult(r);
        }else{
            List l = Arrays.asList(lh.toArray());
            TResultEntity r = ((List<TResultEntity>)l).get(l.size()-1);
            if(r.getResult().length() > 800){
                r = new TResultEntity();
                r.settBallotByIdBallot(b);
                r.setResult(vote.toString());
                new TResultModel().addResult(r);
            }else {
                BigInteger o = new BigInteger(r.getResult());
                o = o.multiply(vote);
                r.setResult(o.toString());
                new TResultModel().updateResult(r.getIdResult(),r);
            }
        }
    }

    public Map<String , Integer> publishResult(Integer idBallot){
        TBallotEntity b = new TBallotModel().getBallotById(idBallot);
        List<TResultEntity> lr = new TResultModel().getResultsByBallot(idBallot);
        List<TOptionEntity> lo = new TOptionModel().getOptionsByBallot(idBallot);
        Map<String, Integer> res = new TreeMap<String, Integer>();
        for(TOptionEntity o : lo){
            res.put(o.getLibOption(),0);
        }
        TAdministratorEntity a = new TAdministratorModel().getAdminById(new TBallotModel().getAdmin(idBallot));
        String s[] = b.getPublicKey().split(";");
        RSA rsa = new RSA(1024);
        rsa.setD(new BigInteger(a.getSecretKey()));
        rsa.setE(new BigInteger(s[0]));
        rsa.setN(new BigInteger(s[1]));
        for(TResultEntity r : lr) {
            BigInteger result = rsa.decrypt(new BigInteger(r.getResult()));
            for(TOptionEntity o : lo){
                Integer count = countVotes(result,new BigInteger(o.getIdPrime()));
                res.put(o.getLibOption(),res.get(o.getLibOption()) + count);
            }
        }
        return res;
    }

    private Integer countVotes(BigInteger r, BigInteger o){
        int i = 0;
        while(r.mod(o) == BigInteger.ZERO){
            r = r.divide(o);
            i++;
        }
        return i;
    }

}
