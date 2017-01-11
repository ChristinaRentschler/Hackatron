package com.example.bobby.obluetoothreceiver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Bobby on 04.01.2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = DbHelper.class.getSimpleName();

    public static final String DB_NAME = "beacon_list.db";
    public static final int DB_VERSION = 1;


    public static final String TABLE_BEACON_LIST = "beacon_list";

    public static final String COLUMN_MACADRESS = "macAdress";
    public static final String COLUMN_DESCRIPTION = "Description";


    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_BEACON_LIST +
                    "(" + COLUMN_MACADRESS + " TEXT PRIMARY KEY, " +
                    COLUMN_DESCRIPTION + " TEXT NOT NULL);";

    private static final String TABLE_MOOD_DROP = "DROP TABLE IF EXISTS "
            + TABLE_BEACON_LIST;



    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrade der Datenbank von Version " + oldVersion + " zu "
                + newVersion + "; alle Daten werden gelöscht");
        db.execSQL(TABLE_MOOD_DROP);
        onCreate(db);

    }
}
