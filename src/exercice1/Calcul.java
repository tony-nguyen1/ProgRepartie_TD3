package exercice1;

public class Calcul extends Thread {
    private Nombres nb;

    public Calcul(Nombres nb) {
        this.nb = nb;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++) {
            //System.out.println("in calcul.run() " + i);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            nb.calcul(); //effectue le calcul
            nb.finitCalcul(); //dit que le calcul est fait
            nb.demandeNouvelleAffichage(); //débloque l'affichage
            nb.attendFinAffichage(); //attend jusqu'à ce que le calcul soit affiché
        }
    }
}
