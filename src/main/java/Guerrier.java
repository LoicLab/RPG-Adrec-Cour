public class Guerrier extends Personnage{

    public Guerrier (String name){

       super(
               200,
               10,
               name
       );
    }

    @Override
    public String toString() {
        return this.nomDuJoueur + " (Guerrier)";
    }
}
