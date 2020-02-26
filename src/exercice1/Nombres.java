package exercice1;

public class Nombres {
    private int n;
    private int carre;
    private boolean pret; //true -> calcul déjà effectué
    private boolean calculFinit = false;
    private boolean affichageFinit = false;

    public Nombres(int n) {
        this.n = n;
        carre = n*n;
        pret = false;
    }

    public synchronized void calcul() {
        System.out.println("calculating");
        n++;
        carre = n*n;
    }
    public synchronized void affiche() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("n=");
        stringBuilder.append(n);
        stringBuilder.append(" n²=");
        stringBuilder.append(carre);
        System.out.println(stringBuilder.toString());
    }

    public synchronized void finitCalcul() {
        calculFinit = true;
    }
    public synchronized void demandeNouveauCalcul() {
        calculFinit = false;
        notifyAll();
    }
    public synchronized void finitAffichage() {
        affichageFinit = true;
    }
    public synchronized void demandeNouvelleAffichage() {
        affichageFinit = false;
        notifyAll();
    }
    public synchronized void attendFinAffichage() {
        if (!affichageFinit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void attendFinCalcul() {
        if (!calculFinit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
