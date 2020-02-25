package exercice1;

public class Affiche extends Thread{
    private Nombres nb;

    public Affiche(Nombres nb) {
        this.nb = nb;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            //System.out.println("in affiche.run() " + i);
            try {
                sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            nb.attendFinCalcul(); //attend jusqu'à ce que le calcul soit fait
            nb.affiche();
            nb.finitAffichage(); //dit que ça a été affiché
            nb.demandeNouveauCalcul(); //débloque le calcul
        }
    }
}
