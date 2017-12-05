package pb.fr.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RechercheAvancee extends AppCompatActivity implements View.OnClickListener {

    private Button buttonValider;
    private Button buttonAnnuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_avancee);

        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonAnnuler = (Button)findViewById(R.id.buttonAnnuler);

        buttonValider.setOnClickListener(this);
        buttonAnnuler.setOnClickListener(this);

        Bundle params = this.getIntent().getExtras();
        String valeur = params.getString("motRecherche");
        Toast.makeText(this, valeur, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if (v == buttonValider){
            Intent intention = new Intent();
            intention.putExtra("dataRetour", "100 résultat trouvé");
            setResult(RESULT_OK,intention);
            finish();
        }
        if (v == buttonAnnuler){
            Intent intention = new Intent();
            intention.putExtra("dataRetour", "Annulation de la recherche");
            setResult(RESULT_CANCELED,intention);
            finish();
        }
    }
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