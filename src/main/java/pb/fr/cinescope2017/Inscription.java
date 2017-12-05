package pb.fr.cinescope2017;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View;
import android.view.View.*;

/**
 *
 */
public class Inscription extends ActionBarActivity implements OnClickListener {
    // Attributs pour les widgets
    private EditText editTextNom;
    private EditText editTextMdp;
    private EditText editTextEmail;
    private Button buttonValider;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        initInterface();
        initEvents();
    } /// onCreate

    private void initInterface() {
        // Liaison widget <--> Attribut
        editTextNom = (EditText) findViewById(R.id.editTextNom);
        editTextMdp = (EditText) findViewById(R.id.editTextMdp);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
    } /// initInterface

    private void initEvents() {
        // Liaison widget <--> Events
        buttonValider.setOnClickListener(this);
    } /// initEvents

    @Override
    public void onClick(View v) {
        if (v == buttonValider) {
            String lsURL = "http://172.26.10.102:8084/Cinescope2017Web/";
            String lsRessource = "UtilisateurInsert";
            new TAInscription(textViewMessage).execute(lsURL, lsRessource, editTextNom.getText().toString(), editTextMdp.getText().toString(), editTextEmail.getText().toString());
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
} /// class
