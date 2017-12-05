package pb.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MonCompte extends AppCompatActivity implements View.OnClickListener {

    // Attributs pour les widgets
    private TextView textViewId;
    private EditText editTextNom;
    private EditText editTextMdp;
    private EditText editTextEmail;
    private Button buttonModifier;
    private Button buttonSupprimer;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mon_compte);
        initInterface();
        initEvents();

        Bundle params = this.getIntent().getExtras();
        String id = params.getString("id");
        String nom = params.getString("nom");
        String mdp = params.getString("mdp");
        String email = params.getString("email");

        textViewId.setText(id);
        editTextNom.setText(nom);
        editTextMdp.setText(mdp);
        editTextEmail.setText(email);
        textViewMessage.setText("Bonjour " + nom);



    } /// onCreate

    private void initInterface() {
        // Liaison widget <--> Attribut
        textViewId = (TextView) findViewById(R.id.textViewId);
        editTextNom = (EditText) findViewById(R.id.editTextNom);
        editTextMdp = (EditText) findViewById(R.id.editTextMdp);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonModifier = (Button) findViewById(R.id.buttonModifier);
        buttonSupprimer = (Button) findViewById(R.id.buttonSupprimer);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
    } /// initInterface

    private void initEvents() {
        // Liaison widget <--> Events
        buttonModifier.setOnClickListener(this);
        buttonSupprimer.setOnClickListener(this);
    } /// initEvents

    @Override
    public void onClick(View v) {
        if (v == buttonModifier) {
            String lsURL = "http://172.26.10.102:8084/Cinescope2017Web/";
            String lsRessource = "UtilisateurUpdate";
            new TAMonCompteModif(textViewMessage).execute(lsURL, lsRessource, textViewId.getText().toString() ,editTextNom.getText().toString(), editTextMdp.getText().toString(), editTextEmail.getText().toString());
            Globale.setId(textViewId.getText().toString());
            Globale.setNom(editTextNom.getText().toString());
            Globale.setMdp(editTextMdp.getText().toString());
            Globale.setEmail(editTextEmail.getText().toString());
        }
        if (v == buttonSupprimer) {
            String lsURL = "http://172.26.10.102:8084/Cinescope2017Web/";
            String lsRessource = "UtilisateurDelete";
            new TAMonCompteSuppr(textViewMessage).execute(lsURL, lsRessource, textViewId.getText().toString());
            Globale.setId("");
            Globale.setNom("");
            Globale.setMdp("");
            Globale.setEmail("");
        }
    } /// onClick


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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
