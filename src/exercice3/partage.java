package exercice3;

import java.util.concurrent.Semaphore;

public class partage extends Thread {
    private static String chaine = "";

    /** Nombre suivant le nb de maj */
    private static int cpt = 0;

    /** Nom de this */
    private String nom;

    /** Semaphore pour réaliser l'exclusion mutuelle sur la maj de partage.cpt
     * et partage.chaine */
    Semaphore semaphore;

    /** Constructeur */
    public partage (String s, Semaphore se) {
        nom = s;
        semaphore = se;
    }

    @Override
    public void run() {
        for (int i = 0; i<10; i++) {
            try {
                /** Solutions pour maj avec exclusions mutuelles par semaphore */
                semaphore.acquire();
                maj2(this.nom);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }

            try {
                Thread.sleep(100); // milliseconds
            } catch(InterruptedException e) {}
        }
    }

    /** Solutions pour maj avec exclusions mutuelles par synchronized */
    public static synchronized void maj(String nn){
        // mises a jour
        chaine = chaine + nn;
        cpt++;
    }

    public  void maj2(String nn){
        chaine = chaine + nn;
        cpt++;
    }


    public static void main(String args[]) {
        Semaphore s = new Semaphore(1);

        Thread T1 = new partage( "T1 ", s); //important !
        Thread T2 = new partage( "T2 ", s); // le semaphore doit être le même
        Thread T3 = new partage( "T3 ", s); // objet pour les 3 threads

        T1.start();
        T2.start();
        T3.start();

        try {
            T1.join();
            T2.join();
            T3.join();
        } catch (InterruptedException e) {}

        System.out.println( chaine );
        System.out.println( cpt );
    }
}

/*
*
* 1. cpt = 25, 21 nom dans la chaîne
* cpt attendu = 30, 30 nom attendu dans la chaîne
*
* 2. section critique, solution possible : ajouté synchronized sur la méthode
* de mise à jour. Si la fct de majs manipule des attributs static, alors
* fct de majs doit avoir synchronized & static
*
*
*/
