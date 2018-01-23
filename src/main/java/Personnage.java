public class Personnage {

    protected int poinDevie ;
    protected int degatAttaque;
    protected String nomDuJoueur;

    public Personnage(int pointDevie, int degat,String nom){

        this.poinDevie=pointDevie;
        this.degatAttaque =degat;
        this.nomDuJoueur=nom;
    }

    public int getDegatAttaque(){
        return this.degatAttaque;
    }

    public int getPointDevie(){
        return this.poinDevie;
    }

    public void setPoinDeVie(int poinDevie){
        if (poinDevie < 0){
            this.poinDevie = 0;
        } else {
            this.poinDevie = poinDevie;
        }
    }

    @Override
    public String toString(){
        return this.nomDuJoueur;
    }

    public void attaquer(Personnage cible){
        System.out.println(this.toString() + " fait une attaque de : "
                + this.getDegatAttaque() + " sur " + cible.toString());
        cible.setPoinDeVie(cible.poinDevie - this.degatAttaque);
    }

    public boolean Pv0() {
        return this.getPointDevie() <= 0;
    }

    public void choixAttaque() {
        System.out.println("Que voulez vous faire : ");
        System.out.println("1. Attaquer ");
    }

    public void jouer(Personnage cible,String choix){
        boolean aJouer = false;
        do {
            if (choix.equals("1")){
                this.attaquer(cible);
                aJouer = true;
            } else {
                aJouer = false;
            }
         }while (aJouer == false);
        }
}
