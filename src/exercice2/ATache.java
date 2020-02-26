package exercice2;

import java.util.concurrent.Semaphore;

class ATache implements Runnable// implementation de l’interface Runnable
{
    int nom; // nom de la tache
    int index; // index de la boucle d’affichage
    Semaphore prive;
    Semaphore voisin;
    Semaphore semfin;

    public ATache(int nom, Semaphore prive, Semaphore voisin, Semaphore semfin) {
        this.nom = nom;
        this.index = 1;
        this.prive = prive;
        this.voisin = voisin;
        this.semfin = semfin;

        try {
            voisin.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run () {
        System.out.println(" debut tache T"+this.nom);

        for (; index <= 30; index++) {

            //on attend notre tour
            try {
                prive.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(nom);

            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //on dit à la tâche suivante que il peut prendre un jeton
            voisin.release();
        }

        System.out.println("fin tache T"+this.nom);
        semfin.release();//lorsque le thread a terminé, on libère semfin
    }
}