package Services;

import Classes.TUserEntity;
import Classes.TVoterEntity;
import Model.TUserModel;
import Model.TVoterModel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class VoterService {


    public void signUp(String nom,String prenom, String tel, String email, String cin, String username, String mdp){
        String idPrime = new Server().giveId(tel).toString();
        TVoterEntity v = new TVoterEntity();
        v.setCin(cin);
        v.setNom(nom);
        v.setPrenom(prenom);
        v.setNumTel(tel);
        v.setEmail(email);
        v.setIdPrime(idPrime);
        TUserEntity u = new TUserEntity();
        u.setUsername(username);
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(mdp.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<hash.length;i++) {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
            u.setMdp(hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Integer idv = new TVoterModel().addVoter(v);
        v.setIdVoter(idv);
        u.settVoterByIdVoter(new TVoterModel().voterByCin(cin));
        System.out.println(u);
        new TUserModel().addUser(u);
    }

    public Integer signIn(String username, String mdp){
        TUserEntity u = new TUserModel().userByName(username);
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(mdp.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<hash.length;i++) {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
            String hashed = (hexString.toString());
            if( u!= null && hashed.compareTo(u.getMdp()) == 0) return u.gettVoterByIdVoter().getIdVoter();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
