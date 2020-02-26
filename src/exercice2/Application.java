package exercice2;

import java.util.concurrent.Semaphore;

public class Application {
    public static void main(String[] args)
    {
        Thread th1, th2, th3;
        Semaphore s1, s2, s3, semfin;

        s1 = new Semaphore(1);
        s2 = new Semaphore(1);
        s3 = new Semaphore(1);

        semfin = new Semaphore(3);//1 permit par thread
        semfin.drainPermits();//au début aucun des threads n'a terminé

        th1 = new Thread(new ATache(1, s3, s1, semfin));
        th2 = new Thread(new ATache(2, s2, s3, semfin));
        th3 = new Thread(new ATache(3, s1, s2, semfin));

        s1.release();
        th1.start();
        th2.start();
        th3.start();

        try {
            semfin.acquire(3);//on attend que tous les threads soient finits
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fin tâche principale");
    }
}
