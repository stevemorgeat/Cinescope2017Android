package pb.fr.cinescope2017;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.*;

public class PermissionsCheck extends Activity implements View.OnClickListener {

    private final int PERMISSION_REQUEST_ALL = 99;

    private CheckBox checkBoxCamera;
    private CheckBox checkBoxRecordAudio;
    private CheckBox checkBoxStorage;
    private boolean lbEtatCheckBoxCameraInitial;
    private boolean lbEtatcheckBoxRecordAudioInitial;
    private boolean lbEtatcheckBoxStorageInitial;

    private Button buttonValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions_check);

        checkBoxCamera = (CheckBox) findViewById(R.id.checkBoxCamera);
        checkBoxRecordAudio = (CheckBox) findViewById(R.id.checkBoxRecordAudio);
        checkBoxStorage = (CheckBox) findViewById(R.id.checkBoxStorage);

        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(this);


        etatCheckBox();

    } /// onCreate

    public void etatCheckBox (){


        /*
        Permet de connaître l'état d'une permission dans les réglages du téléphone
         */

        int permissionCheckCamera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        if (permissionCheckCamera == PackageManager.PERMISSION_GRANTED) {
            // affectation de la checkBox
            checkBoxCamera.setChecked(true);
            Toast.makeText(this, "Camera Granted", Toast.LENGTH_LONG).show();
        } else if (permissionCheckCamera == PackageManager.PERMISSION_DENIED) {
            // affectation de la checkBox
            checkBoxCamera.setChecked(false);
            Toast.makeText(this, "Camera Denied", Toast.LENGTH_LONG).show();
        }

        int permissionCheckRecordAudio = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);

        if (permissionCheckRecordAudio == PackageManager.PERMISSION_GRANTED) {
            // affectation de la checkBox
            checkBoxRecordAudio.setChecked(true);
            Toast.makeText(this, "RECORD_AUDIO Granted", Toast.LENGTH_LONG).show();
        } else if (permissionCheckRecordAudio == PackageManager.PERMISSION_DENIED) {
            // affectation de la checkBox
            checkBoxRecordAudio.setChecked(false);
            Toast.makeText(this, "RECORD_AUDIO Denied", Toast.LENGTH_LONG).show();
        }

        int permissionCheckStorage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheckStorage == PackageManager.PERMISSION_GRANTED) {
            // affectation de la checkBox
            checkBoxStorage.setChecked(true);
            Toast.makeText(this, "READ_EXTERNAL_STORAGE Granted", Toast.LENGTH_LONG).show();
        } else if (permissionCheckStorage == PackageManager.PERMISSION_DENIED) {
            // affectation de la checkBox
            checkBoxStorage.setChecked(false);
            Toast.makeText(this, "READ_EXTERNAL_STORAGE Denied", Toast.LENGTH_LONG).show();
        }

        /*
        Après lecture et checkage ou non des checkbox, on sauvegarde l'état initial des checkBoxS
         */
        lbEtatCheckBoxCameraInitial = checkBoxCamera.isChecked();
        lbEtatcheckBoxRecordAudioInitial = checkBoxRecordAudio.isChecked();
        lbEtatcheckBoxStorageInitial = checkBoxStorage.isChecked();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.i("Granted", Integer.toString(grantResults.length));
        /*
        Cette méthode est appelée par ActivityCompat.requestPermissions()
        */
        switch (requestCode) {
            case PERMISSION_REQUEST_ALL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++) {
                        Toast.makeText(this, permissions[i], Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "La requête a été annulée", Toast.LENGTH_SHORT).show();
                }
                // pour afficher le bon checkage des checkboxs
                etatCheckBox();
                return;
            }
        } /// switch
    } /// onRequestPermissionsResult

    @Override
    public void onClick(View v) {

        /*
        Toutes les permissions en une seule fois avec une seule boite de dialogue et un phenomeme de "diapo"
         */
        List<String> listePermissions = new ArrayList();
        if (checkBoxCamera.isChecked()!=lbEtatCheckBoxCameraInitial) {
            listePermissions.add(Manifest.permission.CAMERA);
        }
        if (checkBoxRecordAudio.isChecked() != lbEtatcheckBoxRecordAudioInitial) {
            listePermissions.add(Manifest.permission.RECORD_AUDIO);
        }
        if (checkBoxStorage.isChecked() != lbEtatcheckBoxStorageInitial) {
            listePermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        /*
        une fois la liste créée ou pas on sauvegarde les états des checkBoxS pour un futur "Click" du bouton "Valider"
         */
        lbEtatCheckBoxCameraInitial = checkBoxCamera.isChecked();
        lbEtatcheckBoxRecordAudioInitial = checkBoxRecordAudio.isChecked();
        lbEtatcheckBoxStorageInitial = checkBoxStorage.isChecked();

        /*
        Si la liste est pas vide on lance la requete de RequestPermission
         */
        if (listePermissions.size() > 0) {
            String[] tPermissions = listePermissions.toArray(new String[listePermissions.size()]);
            ActivityCompat.requestPermissions(this, tPermissions, PERMISSION_REQUEST_ALL);
        }


    } /// onClick
} /// class
