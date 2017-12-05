package pb.fr.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class BoxOffice extends AppCompatActivity implements View.OnClickListener {

    private GridView gridViewBoxOffice;
    private Button buttonValider;
    private Button buttonAnnuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_office);

        buttonValider = (Button)findViewById(R.id.buttonValider);
        buttonAnnuler = (Button)findViewById(R.id.buttonAnnuler);
        gridViewBoxOffice = (GridView)findViewById(R.id.gridViewBoxOffice);

        buttonValider.setOnClickListener(this);
        buttonAnnuler.setOnClickListener(this);

        String lsURL = "http://172.26.10.102:8084/Cinescope2017Web/";
        String lsRessource = "BoxOffice";
        //new TacheAsynchrone().execute(lsURL, lsRessource);
        TAExterne tae = new TAExterne();
        tae.setGVBO(this,"BoxOffice",gridViewBoxOffice);
        tae.execute(lsURL, lsRessource);
    }

    @Override
    public void onClick(View v) {

        if (v == buttonValider){
            setResult(RESULT_OK);
            finish();
        }
        if (v == buttonAnnuler){
            setResult(RESULT_CANCELED);
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