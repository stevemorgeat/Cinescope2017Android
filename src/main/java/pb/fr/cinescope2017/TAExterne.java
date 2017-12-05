package pb.fr.cinescope2017;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrateur on 31/10/2017.
 */
public class TAExterne extends AsyncTask<String, Integer, String> {

    private TextView tv;
    private String pages;
    private GridView gv;
    private Context contexte;


    public TAExterne() {
    }

    public TAExterne(TextView tv) {
        this.tv = tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    public void setGVBO(Context contexte, String pages, GridView gv) {
        this.contexte = contexte;
        this.pages = pages;
        this.gv = gv;

    }

    @Override
    // ----------------------------
    protected String doInBackground(String... asParametres) {
        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan
        StringBuilder lsb = new StringBuilder();
        String lsReturn = "";
        String lsURL;
        String lsRessource;

        lsURL = asParametres[0];
        lsRessource = asParametres[1];

        URL url = null;
        HttpURLConnection httpConnection = null;

        try {
            // Instanciation de HttpURLConnection avec l'objet url
            url = new URL(lsURL + lsRessource);
            httpConnection = (HttpURLConnection) url.openConnection();

            // Connexion
            httpConnection.connect();
            // EXECUTION DE LA REQUETE ET RESPONSE
            InputStream is = httpConnection.getInputStream();
            // Comme l'on recoit un flux Text ASCII
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lsLigne = "";
            if (pages.equals("HPP")) {
                int i = 0;
                while ((lsLigne = br.readLine()) != null) {
                    lsb.append(lsLigne);
                    lsb.append("\n");
                    i++;
                    publishProgress(i);
                }
                lsReturn = lsb.toString();
            } else if (pages.equals("BoxOffice")) {
                lsLigne = br.readLine();
                lsReturn = lsLigne;
            }
            br.close();
            is.close();
        } catch (IOException e) {
            lsb.append(e.getMessage());
        } finally {
            // Deconnexion
            httpConnection.disconnect();
        }
        // Renvoie la valeur a onPostExecute
        return lsReturn;
    } /// doInBackground

    @Override
    // ----------------------------
    protected void onProgressUpdate(Integer... aiProgressions) {
        // Synchronisation avec le thread de l'UI
        // MAJ de la barre de progression
        if (tv != null) {
            tv.setText(Integer.toString(aiProgressions[0]));
        }

    } // / onProgressUpdate


    @Override
    // -------------------------
    protected void onPostExecute(String s) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final
        if (tv != null) {
            tv.setText(s);
        } else if (pages.equals("BoxOffice")) {

            List<String> items = new ArrayList<>();
            try {
                JSONArray tableauJSON = new JSONArray(s);
                JSONObject itemObjet;
                for(int i = 0 ; i < tableauJSON.length(); i++){
                    itemObjet = tableauJSON.getJSONObject(i);
                    items.add(itemObjet.get("titre").toString());
                    items.add(itemObjet.get("entrees").toString());
                    items.add(itemObjet.get("position").toString());
                }
                gv.setAdapter(new ArrayAdapter<>(contexte, R.layout.cellule_text, items));
            } catch (JSONException e) {
                e.printStackTrace();
            }


//            List<String> items = new ArrayList<>();
//            String[] film = s.split("\n");
//            for (int i = 0; i < film.length; i++) {
//                String[] item = film[i].trim().split(";");
//                for (int j = 0; j < item.length; j++) {
//                    items.add(item[j].trim());
//                    Log.i("item", item[j].trim());
//                }
//            }
//            gvTitre.setAdapter(new ArrayAdapter<>(contexte, R.layout.cellule_text, items));
        }
    } /// onPostExecute
} /// TacheAsynchrone
