public class Soigneur extends Personnage {

    private int soin = 30;

    public Soigneur (String name){

        super(
                50,
                5,
                name
        );
    }

    public void soinJoueur(Personnage joueur) {
        joueur.setPoinDeVie(joueur.getPointDevie() + this.soin);
    }

    @Override
    public void choixAttaque() {
        super.choixAttaque();
        System.out.println("2. soin");
    }

    @Override
    public String toString() {
        return this.nomDuJoueur + " (Soigneur)";
    }

    @Override
    public void jouer(Personnage cible, String choix) {
        boolean aJouer = false;
        do {
            if (choix.equals("1")) {
                super.jouer(cible,choix);
                aJouer = true;
            } else {
                aJouer = false;
            }
        }while (aJouer == false);
    }
}