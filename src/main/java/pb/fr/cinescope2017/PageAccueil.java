package pb.fr.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


public class PageAccueil extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /*
     * Attributs
     */
    private ListView listeOngletAccueil;
    /*
     * Methodes
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        On utilise la vue (layout)
         */
        setContentView(R.layout.page_accueil);

        /*
        J'instancie mes 3 tableaux
        2 enregistrés dans mes ressources et un autre ici
         */
        String[] tNoms = getResources().getStringArray(R.array.NomOngletAccueil);
        String[] tDesciption = getResources().getStringArray(R.array.descriptionOngletAccueil);
        String[] tImages = new String[13];
        // on recupère une string qui correspond a l'image
        tImages[0] = String.valueOf(R.drawable.tous_les_films);
        tImages[1] = String.valueOf(R.drawable.box_office);
        tImages[2] = String.valueOf(R.drawable.hit_parade);
        tImages[3] = String.valueOf(R.drawable.critique_film);
        tImages[4] = String.valueOf(R.drawable.recherche_avanee);
        tImages[5] = String.valueOf(R.drawable.salle_cinema);
        tImages[6] = String.valueOf(R.drawable.avant_premiere);
        tImages[7] = String.valueOf(R.drawable.festival_cannes);
        tImages[8] = String.valueOf(R.drawable.article);
        tImages[9] = String.valueOf(R.drawable.hit_parade);
        tImages[10] = String.valueOf(R.drawable.hit_parade);
        tImages[11] = String.valueOf(R.drawable.hit_parade);
        tImages[12] = String.valueOf(R.drawable.festival_cannes);

        /*
        on instancie notre liste d'onglet avec celle du layout et on met sur écoute le click de celui ci
         */
        listeOngletAccueil = (ListView) findViewById(R.id.listeOngletAccueil);
        listeOngletAccueil.setOnItemClickListener(this);

        //déclaration de la liste accueil
        List<Map<String, String>> listeAccueil = new ArrayList<>();
        // declaration de mon hashMap ou on affectera les tableaux d'images, noms et descriptions que j'incorporerai a la liste accueil
        Map<String, String> hm;
        //boucle sur les tableaux
        for (int i = 0; i < tNoms.length; i++) {
            //new hashMap à chaque tour
            hm = new HashMap<>();
            hm.put("image", tImages[i]);
            hm.put("nom", tNoms[i]);
            hm.put("description", tDesciption[i]);

            // on incorpore le HM à la liste
            listeAccueil.add(hm);
        }

        /*
         on instancie un nouveau modèle SimplAdapter,
          this pour le context
          la liste à affecter
          le layout affecté( ligne de l'accueil)
          tableau des cles du HM
          tableau des id des widget de mon layout
          */
        SimpleAdapter sa = new SimpleAdapter(
                this,
                listeAccueil,
                R.layout.ligne_accueil,
                new String[]{"image", "nom", "description"},
                new int[]{R.id.imageViewCinescope2017, R.id.nomOnglet, R.id.desciptionOnglet}
        );

        // Affectation le modèle du Simple Adapter à la ListView
        listeOngletAccueil.setAdapter(sa);


        Globale.setId("");
        Globale.setNom("");
        Globale.setMdp("");
        Globale.setMdp("");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // on récupère le hashmap clické
        Map<String, String> map = (Map<String, String>) parent.getAdapter().getItem(position);
        // toast avec la value de la cle du hashmap en question
        Toast.makeText(this, map.get("nom"), Toast.LENGTH_SHORT).show();

        Intent intention = new Intent();
        if (position == 0) {
            intention.setClass(this, TousLesFilms.class);
            startActivityForResult(intention, 0);
        }

        if (position == 1) {
            intention.setClass(this, BoxOffice.class);
            startActivityForResult(intention, 1);
        }

        if (position == 2) {
            intention.setClass(this, HitParadeDuPublic.class);
            startActivityForResult(intention, 2);
        }
        if (position == 3) {
            intention.setClass(this, AvisDesCritiques.class);
            startActivityForResult(intention, 3);
        }
        if (position == 4) {
            intention.setClass(this, RechercheAvancee.class);
            intention.putExtra("motRecherche", "machintosh");
            startActivityForResult(intention, 4);

        }
        if (position == 5) {
            intention.setClass(this, SalleDeParis.class);
            startActivityForResult(intention, 5);
        }
        if (position == 6) {
            intention.setClass(this, AvantPremiere.class);
            startActivityForResult(intention, 6);
        }
        if (position == 7) {
            intention.setClass(this, Festivals.class);
            startActivityForResult(intention, 7);
        }
        if (position == 8) {
            intention.setClass(this, Articles.class);
            startActivityForResult(intention, 8);
        }
        if (position == 9) {
            intention.setClass(this, Inscription.class);
            startActivityForResult(intention, 9);
        }
        if (position == 10) {
            intention.setClass(this, Authentification.class);
            startActivityForResult(intention, 10);
        }
        if (position == 11) {
            String lsNom = Globale.getNom();
            if(lsNom.isEmpty()  || lsNom.equals("inconnu")){
                intention.setClass(this, Authentification.class);
                startActivityForResult(intention, 10);
            } else {
                intention.setClass(this, MonCompte.class);
                intention.putExtra("id", Globale.getId());
                intention.putExtra("nom", Globale.getNom());
                intention.putExtra("mdp", Globale.getMdp());
                intention.putExtra("email", Globale.getEmail());
                startActivityForResult(intention, 11);
            }
        }
        if (position == 12) {
            intention.setClass(this, Importations.class);
            startActivityForResult(intention, 12);
        }


    }


    // -------------------------
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0: // TousLesFilms
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK TousLesFilms", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO TousLesFilms", Toast.LENGTH_SHORT).show();
                        return;
                } // / switch (resultCode)

            case 1: // BoxOffice
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK BoxOffice", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO BoxOffice", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 2: // HitParadeDuPublic
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK HitParadeDuPublic", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO HitParadeDuPublic", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 3: // AvisDesCritiques
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK AvisDesCritiques", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO AvisDesCritiques", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 4: // RechercheAvancee
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        //Toast.makeText(this, "OKKK RechercheAvancee", Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, data.getStringExtra("dataRetour"), Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, data.getStringExtra("dataRetour"), Toast.LENGTH_SHORT).show();
                        return;
                }
            case 5: // SalleDeParis
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK SalleDeParis", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO SalleDeParis", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 6: // AvantPremiere
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK AvantPremiere", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO AvantPremiere", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 7: // Festivals
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK Festivals", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO Festivals", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 8: // Articles
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK Articles", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO Articles", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 9: // Inscription
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK Inscription", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO Inscription", Toast.LENGTH_SHORT).show();
                        return;


                }
            case 10: // Authentification
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK Authentification", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO Authentification", Toast.LENGTH_SHORT).show();
                        return;
                }

            case 11: // Mon Compte
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OKKK Mon Compte", Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "KKOOOO Mon Compte", Toast.LENGTH_SHORT).show();
                        return;

                }
        } // / switch (requestCode)

    } // / onActivityResult


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuItemChoix.menuItemChoix(this, item.getItemId());
    } // onOptionsItemSelected


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
