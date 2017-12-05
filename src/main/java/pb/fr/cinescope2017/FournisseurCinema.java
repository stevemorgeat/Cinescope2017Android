package pb.fr.cinescope2017;

import android.content.ContentProvider;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteException;
        import android.net.Uri;

/*
 * Classe qui sert de fournisseur de donnees SQL (SQLite en l'occurrence)
 *
 * Methodes : onCreate, query, insert, update, delete, getType
 */
public class FournisseurCinema extends ContentProvider {

    private SQLiteDatabase db;

    public static final Uri CONTENT_URI = Uri.parse("content://pb.fr.cinescope2017.FournisseurCinema");

    @Override
    public boolean onCreate() {
        Context context = getContext();
        // Connexion à la BD
        GestionnaireCinemaSQLite dbHelper = new GestionnaireCinemaSQLite(context, null);
        this.db = dbHelper.getWritableDatabase();
        return (this.db == null) ? false : true;
    } /// onCreate



    @Override
    public Cursor query(Uri uri, String[] projection, String restriction, String[] restrictionArgs, String sortOrder) {
        Cursor curseur = null;
        // --- Tous les enregistrements
        try {
            // --- query(table, tColonnes, sWhere, tParamsWhere, sGroupBy, sHaving, sOrderBy)
            curseur = this.db.query("cinema", projection, restriction, restrictionArgs, null, null, sortOrder);
        }
        catch(SQLiteException e) {
            curseur = null;
        }
        return curseur;
    } /// query



    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // A modifier lors de l'exercice
        return null;
    } /// insert



    @Override
    public int update(Uri uri, ContentValues values, String restriction, String[] restrictionArgs) {
        // A modifier lors de l'exercice
        return 0;
    } /// update



    @Override
    public int delete(Uri uri, String restriction, String[] restrictionArgs) {

        // --- delete(table, sWhere, tWhere)
        int count = this.db.delete("villes", "cp=?", restrictionArgs);
        return count;
    } /// delete



    @Override
    public String getType(Uri uri) {
        // Renvoie le type MIME d'un résultat
        return null;
    } /// getType

} /// FournisseurSQLite
