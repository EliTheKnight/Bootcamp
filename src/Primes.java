import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Primes {
    public static boolean isPrime(long n, ArrayList<Long> primes) {
        long highest = primes.get(primes.size()-1);
        long cap = (long) (Math.sqrt(n)) + 1;
        for(Long i: primes){
            if (n % i == 0)
                return false;
        }
        for(long i = highest; i<cap; i++){
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static boolean goodIsPrime(long n, ArrayList<Long> primes){
        long bigEnough = (long)Math.sqrt(n) + 1;
        long biggest = primes.get(primes.size() - 1);
        if (biggest >= n){
            return primes.contains(n);
        }
        if (biggest < bigEnough){
                buildList(bigEnough);
                primes = read();
        }
        for(Long num: primes){
            if (n % num == 0)
                return false;
            if (num > bigEnough)
                return true;
        }
        return true;
    }

    public static void main(String[] args) {
//        buildList(1000000);
        long start = System.currentTimeMillis();
        ArrayList<Long> primes = read();
        System.out.println(goodIsPrime(5600748293801L, primes));
//        System.out.println(goodIsPrime(7857, primes));
        long finish = System.currentTimeMillis();
        System.out.print(finish - start);
    }

    public static void buildList(long upto){
        ArrayList<Long> primes = read();
        long highest = 2;
        if (primes.size() > 0)
            highest = primes.get(primes.size() - 1);
        for(long i = highest + 1; i < highest + upto; i++){
            if (goodIsPrime(i, primes) && !primes.contains(i)){
                primes.add(i);
            }
        }
        write(primes);
    }

    public static void write(ArrayList<Long> list) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./PrimeList.txt"), "utf-8"));
            for(Long i: list) {
                writer.write(i + " ");
            }
            writer.close();
        }catch (Exception e){e.printStackTrace();}
    }

    public static ArrayList<Long> read(){
        ArrayList<Long> list = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File("./PrimeList.txt"));
            while (reader.hasNextLong()){
                list.add(reader.nextLong());
            }
        }catch (Exception e){e.printStackTrace();}
        return list;
    }
}