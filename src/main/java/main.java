import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void  main(String[] args) {

        System.out.println("Bienvenue sur RPG");
        int rejouerPartie = -1;

        //boucle de la partie
        do {
            rejouerPartie = -1;

            //création des liste de joueurs et perso Mort
            ArrayList<Personnage> listeJoueur = new ArrayList<Personnage>();
            ArrayList<Personnage> listPersoMort = new ArrayList<Personnage>();
            Scanner scan = new Scanner(System.in);
            int nbJoueur =0;

            //Creation du nombre de joueurs
            while (nbJoueur <= 0) {
                try {
                    System.out.println("Nombre de joueur ?");
                    System.out.print("Votre choix : ");
                    nbJoueur = scan.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Rentrer un chiffre entier! ");
                }
                scan.nextLine();
            }

            for (int i=0; i < nbJoueur; i++) {

                //Demande le nom du joueur
                System.out.print("Quel est votre nom :");
                String nomJoueur = scan.nextLine();

                Boolean persoCreer = true;

                //Demande la classe du joueur
                do {
                    persoCreer = true;

                    System.out.println("Quel est la classe de personnage que vous voulez choisir :");
                    System.out.println("1. Guerrier ");
                    System.out.println("2. Mage ");
                    System.out.println("3. Soigneur ");
                    System.out.print("Votre choix :");
                    String ClassePerso = scan.nextLine();

                    if (ClassePerso.equals("1")) {
                        Guerrier joueurGuerrier = new Guerrier(nomJoueur);
                        listeJoueur.add(joueurGuerrier);
                    } else if (ClassePerso.equals("2")) {
                        Mage joueurMage = new Mage(nomJoueur);
                        listeJoueur.add(joueurMage);
                    } else if (ClassePerso.equals("3")) {
                        Soigneur joueurSoigneur = new Soigneur(nomJoueur);
                        listeJoueur.add(joueurSoigneur);
                    } else {
                        System.out.println("Veuillez saisir 1 ou 2 ou 3 !");
                        persoCreer = false;
                    }
                } while (persoCreer == false);
            }

            //création du monstre
            Monstre mechant = new Monstre();

            int numeroJoueur = 0;
            int numJoueur = 0;
            int numJoueurMort=0;
            Personnage aQuiLeTour;
            Personnage vieRestante;

            //Boucle pour chaque tour
            while (mechant.getPointDevie() > 0 && listeJoueur.isEmpty() == false) {²
                aQuiLeTour = listeJoueur.get(numeroJoueur);
                System.out.println(aQuiLeTour.toString() +" à votre tour");

                //récapitulatif des points de vie
                do {
                    vieRestante = listeJoueur.get(numJoueur);
                    System.out.print(vieRestante.toString()+" : ");
                    System.out.println(vieRestante.getPointDevie()+" pv");
                    numJoueur++;
                } while (numJoueur < listeJoueur.size());

                //vie du monstre
                System.out.println("Monstre : "+mechant.getPointDevie()+" pv");

                //choix de l'attaque
                aQuiLeTour.choixAttaque();
                Boolean verif = false;
                do {
                    System.out.print("Votre choix :");
                    String choix = scan.nextLine();

                    //Si perso est un soigneur et le choix est 2
                    if (aQuiLeTour instanceof Soigneur && choix.equals("2")){
                        System.out.println("Qui voulez vous soigner ?");
                        for (int  i=0;i < listeJoueur.size();i++){
                            System.out.println(listeJoueur.get(i)+" taper "+i);
                            if (i >= listeJoueur.size()){
                                break;
                            }
                        }
                        System.out.print("Votre choix :");
                        int joueurSoigner = scan.nextInt();
                        scan.nextLine();
                        Personnage persoSoigner = listeJoueur.get(joueurSoigner);
                        System.out.println(aQuiLeTour.toString() + " soigne " + persoSoigner.toString() + " de 30 pv");
                        Soigneur soigneur = (Soigneur) aQuiLeTour;
                        soigneur.soinJoueur(persoSoigner);
                        verif = true;
                    } else if (choix.equals("1") || choix.equals("2")){

                        //si perso est un  Guerrier et le choix est 2 on redemande
                        if (aQuiLeTour instanceof Guerrier && choix.equals("2")){
                            System.out.println("Veuillez taper 1 !");
                        } else {
                            aQuiLeTour.jouer(mechant,choix);
                            verif = true;
                        }
                    } else {
                        System.out.println("Veuillez taper 1 ou 2 !");
                    }
                } while (verif == false);

                //mechant attaque
                if (mechant.Pv0()){
                    break;
                } else {
                    mechant.attaquer(aQuiLeTour);
                }

                // Suprimer le joueur si il est mort
                if (aQuiLeTour.Pv0() == true) {
                    listPersoMort.add(numJoueurMort,aQuiLeTour);
                    listeJoueur.remove(numeroJoueur);
                    System.out.println("le joueur : "+aQuiLeTour.toString()+" a plus de vie");
                    numJoueurMort++;
                }

                numeroJoueur++;

                //remise à zéro si tous les joueurs ont joué
                if (numeroJoueur >= listeJoueur.size()) {
                    numeroJoueur = 0;
                }
                if (numJoueur >= listeJoueur.size()) {
                    numJoueur =0;
                }

                System.out.println("");
            }

            //message de victoire et les résultats
            if (mechant.Pv0()) {
                System.out.println("Félicitation vous avez battu le monstre !");
                System.out.println("Voici le récapitulatif des pvs des personnages :");
                for (int i=0; i < listeJoueur.size(); i++){
                    aQuiLeTour = listeJoueur.get(i);
                    System.out.print(aQuiLeTour.toString());
                    System.out.println(" : "+aQuiLeTour.getPointDevie()+" pv");
                    if (i >= listeJoueur.size()) {
                        break;
                    }
                }
                //affichage des résultats des morts
                for (int i=0; i < listPersoMort.size(); i++){
                    aQuiLeTour = listPersoMort.get(i);
                    System.out.print(aQuiLeTour.toString());
                    System.out.println(" : "+aQuiLeTour.getPointDevie()+" pv");
                    if (i >= listeJoueur.size()) {
                        break;
                    }
                }
            } else {
                System.out.println("Le "+mechant.toString()+" à gagné");
            }

            while (rejouerPartie < 0 || rejouerPartie > 1) {
                try {
                    System.out.println("Voulez vous refaire une partie ? oui=1|non=0 ");
                    System.out.print("Votre choix : ");
                    rejouerPartie = scan.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Rentrer un chiffre entier! ");
                }
                scan.nextLine();

            }
        } while (rejouerPartie == 1);
    }
}