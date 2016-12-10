package Services;


import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private static List <BigInteger> lPrimes;

    private static void init(){
        int i = 0;
        BigInteger a = new BigInteger("131");
        lPrimes = new LinkedList();
        while(i < 10){
            lPrimes.add(a);
            a = a.nextProbablePrime();
            i++;
        }
    }

    private BigInteger transform(String s){
        if(s.length()==1) return lPrimes.get(s.charAt(0) - '0');
        BigInteger first = transform(s.substring(0, s.length()/2));
        BigInteger second = transform(s.substring(s.length()/2,s.length()));
        if(first.compareTo(second) == 0) second =  second.subtract(BigInteger.ONE);
        BigInteger ret;
        int n=1;
        do{
            ret = first.add(second.multiply(BigInteger.valueOf(n)));
            n++;
        }while(!ret.isProbablePrime(100));
        return ret;
    }

    public BigInteger giveId(String phoneNumber){
        init();
        return this.transform(phoneNumber);
    }

}