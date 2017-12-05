package pb.fr.cinescope2017;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/*
 * Classe Helper pour la gestion de l'ouverture de la BD
 * La connexion a la BD en somme ou la creation et connexion si elle n'existe pas
 * Methodes : constructeur, onCreate, onUpgrade
 */

// -------------------------------------
public class GestionnaireCinemaSQLite extends SQLiteOpenHelper {
    private static final int DB_VERSION = 6;
    private static final String DB_NAME = "cinescope.db";

    private static final String DROP_TABLE_CINEMA = "DROP TABLE IF EXISTS CINEMA";
    private static final String CREATE_TABLE_CINEMA = "CREATE TABLE IF NOT EXISTS cinema(ID_CINEMA INTEGER PRIMARY KEY AUTOINCREMENT, ID_VILLE INTEGER, ID_ARRONDISSMENT INTEGER, CODE_CINEMA VARCHAR(10), NOM_CINEMA VARCHAR(50) NOT NULL)";

    // --- Constructeur
    // -------------------------------
    public GestionnaireCinemaSQLite(Context contexte, SQLiteDatabase.CursorFactory fabrique) {
        // --- Cree la BD si elle n'existe pas
        // --- et de ce fait execute le code de onCreate()
        // --- Se connecte si elle existe
        super(contexte, DB_NAME, fabrique, DB_VERSION);
    } /// GestionnaireOuvertureSQLite()

    @Override
    // -----------------
    public void onCreate(SQLiteDatabase abd) {
        // --- Creation de la table pays
        abd.execSQL(CREATE_TABLE_CINEMA);
    } /// onCreate()

    @Override
    // ------------------
    public void onUpgrade(SQLiteDatabase abd, int ancienneVersion, int nouvelleVersion) {
        // --- Suppression de la table puis appel a la creation des tables
        abd.execSQL(DROP_TABLE_CINEMA);
        onCreate(abd);
    } /// onUpgrade()

} /// class GestionnaireOuvertureSQLite
