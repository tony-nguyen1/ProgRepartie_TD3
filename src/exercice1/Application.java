package exercice1;

public class Application {
    public static void main(String[] args)
    {
        Nombres nomb;
        Calcul calc;
        Affiche aff;

        nomb = new Nombres(0);

        calc = new Calcul(nomb);
        aff = new Affiche(nomb);

        calc.start();
        aff.start();

        try {
            aff.join();
            calc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
