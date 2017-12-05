package pb.fr.cinescope2017;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by allth on 06/11/2017.
 */

public class TacheAsynchroneGenerique extends AsyncTask<String, Integer, String> {

    private MyCallBackInterface callback;

    public TacheAsynchroneGenerique(MyCallBackInterface callback) {
        this.callback = callback;
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
            while ((lsLigne = br.readLine()) != null) {
                lsb.append(lsLigne);
            }
            lsReturn = lsb.toString();
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
    // -------------------------
    protected void onPostExecute(String s) {
        // do something
        // EXECUTION DE LA METHODE DEFINIE
        callback.onTaskFinished(s);

    } /// onPostExecute
} /// TacheAsynchrone