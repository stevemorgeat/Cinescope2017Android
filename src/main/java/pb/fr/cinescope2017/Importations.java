package pb.fr.cinescope2017;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Importations extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCinemaTransfert;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.importations);

        buttonCinemaTransfert = (Button) findViewById(R.id.buttonCinemaTransfert);
        buttonCinemaTransfert.setOnClickListener(this);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
    }

    @Override
    public void onClick(View v) {
        try {
            if (v == buttonCinemaTransfert) {
                String lsURL = "http://172.26.10.102:8084/Cinescope2017Web/";
                String lsRessource = "CinemaSelectAll";

                Log.i("importations", lsRessource);
                new TacheAsynchroneGenerique(new MyCallback(this, textViewMessage, "cinema")).execute(lsURL, lsRessource);
                Log.i("importations", lsRessource);
            }
        } catch (Exception e) {
            textViewMessage.setText(e.getMessage());
        }
    } /// onClick

    //========================================================================================================================================
    private static class MyCallback implements MyCallBackInterface {
        private Context contexte;
        private TextView textViewMessage;
        private String table;

        public MyCallback(Context contexte, TextView textViewMessage, String table) {
            this.contexte = contexte;
            this.textViewMessage = textViewMessage;
            this.table = table;
        }

        public void onTaskFinished(String result) {
            //TchoukTchouk
            GestionnaireCinemaSQLite gos;
            SQLiteDatabase bd;
            int liAffecte = 0;
            StringBuilder lsbMessage = new StringBuilder();

            //tchouktchouk
            gos = new GestionnaireCinemaSQLite(contexte, null);
            bd = gos.getWritableDatabase();
            ContentValues hmValeurs;

            //if la table est cinema
            if (table.equals("cinema")) {
                try {
                    bd.delete(table, null, null);
                    JSONArray JAResultat = new JSONArray(result);
                    JSONObject cinema;
                    for (int i = 0; i < JAResultat.length(); i++) {
                        cinema = JAResultat.getJSONObject(i);
                        hmValeurs = new ContentValues();
                        hmValeurs.put("ID_CINEMA", cinema.get("ID_CINEMA").toString());
                        hmValeurs.put("ID_VILLE", cinema.get("ID_VILLE").toString());
                        hmValeurs.put("ID_ARRONDISSMENT", cinema.get("ID_ARRONDISSMENT").toString());
                        hmValeurs.put("CODE_CINEMA", cinema.get("CODE_CINEMA").toString());
                        hmValeurs.put("NOM_CINEMA", cinema.get("NOM_CINEMA").toString());
                        long llNum = bd.insert(table, null, hmValeurs);
                        Log.i("nombre d'insertions", Integer.toString((int)llNum));
                        liAffecte++;
                    }
                    lsbMessage.append(Integer.toString(liAffecte) + " insertion(s) dans la base de données SQLite!");
                    lsbMessage.append("\n\n");
                    lsbMessage.append("Liste des cinémas : \n");
                    String[] cols = {"ID_CINEMA", "ID_VILLE", "ID_ARRONDISSMENT","CODE_CINEMA","NOM_CINEMA"};
                    // Visualisation de la table
                    // --- Sans WHERE : une projection

                    Cursor curseur = bd.query(table, cols, null, null, null, null, null);
                    while (curseur.moveToNext()) {

                        lsbMessage.append(curseur.getInt(0));
                        lsbMessage.append("-");
                        lsbMessage.append(curseur.getString(1));
                        lsbMessage.append("-");
                        lsbMessage.append(curseur.getString(2));
                        lsbMessage.append("-");
                        lsbMessage.append(curseur.getString(3));
                        lsbMessage.append("-");
                        lsbMessage.append(curseur.getString(4));
                        lsbMessage.append("\n");
                    }
                    curseur.close();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            gos.close();
            bd = null;
            textViewMessage.setText(lsbMessage.toString());
        }
    }

} //// classe