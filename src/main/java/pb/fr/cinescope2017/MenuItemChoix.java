package pb.fr.cinescope2017;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

// -----------------------
public class MenuItemChoix {

    public static boolean menuItemChoix(Activity activite, int choix) {

        Intent intention;

        // Détermine quelle entrée a été sélectionnée.
        switch (choix) {

            // Aiguille
            case (R.id.action_settings):
                Toast.makeText(activite, "Configuration", Toast.LENGTH_SHORT).show();
                return true;

            case (R.id.itemAide):
                // Une activite avec une WebView et l'aide en HTML/CSS
                intention = new Intent(activite, Aide.class);
                activite.startActivityForResult(intention, 1);
                return true;

            case (R.id.itemAPropos):
                /*
                 * LA BOITE DE DIALOGUE
                 */
                // --- L'écouteur pour le clic
                // Le code peut être en dehors de la méthode utilisatrice
                DialogInterface.OnClickListener ecouteurClicBD = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int codeBouton) {

                    }
                };
                String lsTitre = "A propos";
                String lsMessage = "Android Contacts\nNovembre 2017\nVersion 0.9\nAuthor : MS & Co";
                String lsOK = "OK";

                AlertDialog.Builder ad = new AlertDialog.Builder(activite);
                ad.setTitle(lsTitre);
                ad.setMessage(lsMessage);
                ad.setNeutralButton(lsOK, ecouteurClicBD);
                ad.show();
                return true;

            case (R.id.itemAccueil):
                //
                intention = new Intent();
                intention.setClass(activite, PageAccueil.class);
                activite.startActivityForResult(intention, 3);
                return true;

            case (R.id.itemTousLesFilms):
                //
                intention = new Intent();
                intention.setClass(activite, TousLesFilms.class);
                activite.startActivityForResult(intention, 4);
                return true;


            case (R.id.itemBoxOffice):
                //
                intention = new Intent();
                intention.setClass(activite, BoxOffice.class);
                activite.startActivityForResult(intention, 5);
                return true;

            case (R.id.itemHPP):
                //
                intention = new Intent();
                intention.setClass(activite, HitParadeDuPublic.class);
                activite.startActivityForResult(intention, 6);
                return true;

            case (R.id.itemAvisDesCritiques):
                //
                intention = new Intent();
                intention.setClass(activite, AvisDesCritiques.class);
                activite.startActivityForResult(intention, 7);
                return true;

            case (R.id.itemRechercheAvancee):
                //
                intention = new Intent();
                intention.setClass(activite, RechercheAvancee.class);
                intention.putExtra("motRecherche", "machintosh");
                activite.startActivityForResult(intention, 8);
                return true;

            case (R.id.itemSalleDeParis):
                //
                intention = new Intent();
                intention.setClass(activite, SalleDeParis.class);
                activite.startActivityForResult(intention, 9);
                return true;

            case (R.id.itemAvantPremiere):
                //
                intention = new Intent();
                intention.setClass(activite, AvantPremiere.class);
                activite.startActivityForResult(intention, 10);
                return true;

            case (R.id.itemFestivals):
                //
                intention = new Intent();
                intention.setClass(activite, Festivals.class);
                activite.startActivityForResult(intention, 11);
                return true;

            case (R.id.itemArticle):
                //
                intention = new Intent();
                intention.setClass(activite, Articles.class);
                activite.startActivityForResult(intention, 12);
                return true;
            case (R.id.itemInscription):
                //
                intention = new Intent();
                intention.setClass(activite, Inscription.class);
                activite.startActivityForResult(intention, 13);
                return true;
            case (R.id.itemAuthentification):
                //
                intention = new Intent();
                intention.setClass(activite, Authentification.class);
                activite.startActivityForResult(intention, 14);
                return true;
            case (R.id.itemMonCompte):
                //
                intention = new Intent();
                String lsNom = Globale.getNom();
                if(lsNom.isEmpty()  || lsNom.equals("inconnu")){
                    intention.setClass(activite, Authentification.class);
                    activite.startActivityForResult(intention, 14);
                } else {
                    intention.setClass(activite, MonCompte.class);
                    intention.putExtra("id", Globale.getId());
                    intention.putExtra("nom", Globale.getNom());
                    intention.putExtra("mdp", Globale.getMdp());
                    intention.putExtra("email", Globale.getEmail());
                    activite.startActivityForResult(intention, 15);
                }
                return true;

            case (R.id.itemImportations):
                //
                intention = new Intent();
                intention.setClass(activite, Importations.class);
                activite.startActivityForResult(intention, 16);
                return true;

            default:
                // Renvoie false si les entrées n’ont pas été gérées.
                return false;
        }
    } /// menuItemChoix

} /// class
