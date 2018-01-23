public class Monstre extends Personnage {

    private int degatCritique = this.degatAttaque *2;

    public Monstre (){

        super(
            (int) (Math.random() * ((300 - 50) + 1)+ 50),
                (int) (Math.random() * ((20 - 5) + 1)+ 5),
            "Monstre"
        );
    }

    @Override
    public void attaquer(Personnage cible) {
        double nb = (Math.random() * ((100 - 1) + 1)+ 1);
        if (nb < 10) {
            System.out.println("Attaque critique du " + this.toString() + " de : "
                    + this.degatCritique + " sur " + cible.toString());
            cible.setPoinDeVie(cible.poinDevie - this.degatCritique);
        } else {
            super.attaquer(cible);
        }
    }
}