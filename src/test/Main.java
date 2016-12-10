package test;

import Classes.TBallotEntity;
import Classes.TOptionEntity;
import Classes.TUserEntity;
import Classes.TVoterEntity;
import Model.TBallotModel;
import Model.TOptionModel;
import Model.TUserModel;
import Model.TVoterModel;
import Services.BallotService;
import Services.RSA;
import Services.VoterService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by root on 6/10/16.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new VoterService().signIn("faridtsl", "170793"));
    }

    public static void testPublish(){
        List<String> l = new LinkedList<>();
        l.add("Me");
        l.add("Other");
        l.add("Him");
        Map<String,Integer> m = new BallotService().publishResult(12);
        for(String s : l){
            System.out.println(s + " : " + m.get(s));
        }
    }

    public static void testCast(){
        Integer id = new BallotService().createBallot("Vote 1",0,8,new Date(2016,8,23));
        List<String> l = new LinkedList<>();
        l.add("Me");
        l.add("Other");
        l.add("Him");
        new BallotService().addOptions(id,l);

        TBallotEntity b = new TBallotModel().getBallotById(id);

        List le = new TOptionModel().getOptionsByBallot(id);
        String s[] = b.getPublicKey().split(";");
        RSA rsa = new RSA(new BigInteger(s[1]),new BigInteger(s[0]));
        TVoterEntity v = new TVoterModel().getVoterById(8);
        BigInteger vote = new BigInteger(((List< TOptionEntity>)le).get(1).getIdPrime()).multiply(new BigInteger(v.getIdPrime()));
        new BallotService().castVote(id,8,rsa.encrypt(vote));
        vote = new BigInteger(((List< TOptionEntity>)le).get(1).getIdPrime()).multiply(new BigInteger(v.getIdPrime()));
        new BallotService().castVote(id,8,rsa.encrypt(vote));
        vote = new BigInteger(((List< TOptionEntity>)le).get(1).getIdPrime()).multiply(new BigInteger(v.getIdPrime()));
        new BallotService().castVote(id,8,rsa.encrypt(vote));
        vote = new BigInteger(((List< TOptionEntity>)le).get(2).getIdPrime()).multiply(new BigInteger(v.getIdPrime()));
        new BallotService().castVote(id,8,rsa.encrypt(vote));
        vote = new BigInteger(((List< TOptionEntity>)le).get(0).getIdPrime()).multiply(new BigInteger(v.getIdPrime()));
        new BallotService().castVote(id,8,rsa.encrypt(vote));
        vote = new BigInteger(((List< TOptionEntity>)le).get(0).getIdPrime()).multiply(new BigInteger(v.getIdPrime()));
        new BallotService().castVote(id,8,rsa.encrypt(vote));
    }

}
