public class Mage extends Personnage  {

    private int soin = 5;

    public Mage (String name){

        super(
                100,
                20,
                name
        );
    }

    public void setSoin() {
        if (this.getPointDevie() < 100 ){
            System.out.println(this.toString() + " ce soigne de 5pv ");
            setPoinDeVie(this.soin + this.getPointDevie());
        } else {
            System.out.println("Vous avez le maximum de pv ");
            setPoinDeVie(this.getPointDevie());
        }
    }

    @Override
    public String toString() {
        return this.nomDuJoueur + " (Mage)";
    }

    @Override
    public void choixAttaque() {
        super.choixAttaque();
        System.out.println("2. soin");
    }

    @Override
    public void jouer(Personnage cible,String choix) {
        boolean aJouer = false;
        do {
            if (choix.equals("1")) {
                super.jouer(cible,choix);
                aJouer = true;
            } else if (choix.equals("2")) {
                this.setSoin();
                aJouer = true;
            } else {
                aJouer = false;
            }
        }while (aJouer == false);
    }
}
