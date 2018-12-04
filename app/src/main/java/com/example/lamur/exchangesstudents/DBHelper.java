package com.example.lamur.exchangesstudents;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper sInstance;

    private static final int DATABASE_VERSION = 21;
    private static final String DATABASE_NAME = "Services.db";
    public static final String TABLE_USERS = "user";
    public static final String COLUMN_ID = "_id_user";
    public static final String COLUMN_USERNAME = "name";
    public static final String COLUMN_MDP = "mdp";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_ADDRESSE = "adresse";
    public static final String COLUMN_CP = "cp";
    public static final String COLUMN_VILLE = "ville";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_COMPANY_NAME = "company";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LICENSE = "license";

    public static final String TABLE_SERVICES = "services";
    public static final String SERVICE_ID = "_id_service";
    public static final String SERVICE_USERNAME = "type_service";
    public static final String TAUX_HORAIRE = "taux_horaires";

    public static final String TABLE_SERVICE_HORAIRE = "service_horaire";
    public static final String COLUMN_JOUR = "jour";
    public static final String COLUMN_HEURE = "heure";
    public static final String COLUMN_SERVICES_HORAIRES_ID = "id";
    public static final String SERVICE_CHOSE_ID = "id_service_dispo";
    public static final String COLUMN_FOURNISSEUR_ID = "id_fournisseur";
    public static final String COLUMN_NOTE= "note";
    public static final String COLUMN_NBVOTE= "nbvote";

    public static final String TABLE_SERVICE_RDV = "Rendez_vous";
    public static final String COLUMN_RDV_ID = "id_rdv";
    public static final String COLUMN_ID_USER = "id_user";
    public static final String COLUMN_DISPONIBILITE = "id_disponibilite";
    public static final String COLUMN_COMS = "commentaire";
    public static final String COLUMN_NOTE_USER= "note_user";



    public static synchronized DBHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        // db.setForeignKeyConstraintsEnabled(true);
    }

    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                COLUMN_USERNAME + " TEXT," +
                COLUMN_MDP + " TEXT," +
                COLUMN_ROLE + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_ADDRESSE + " TEXT," +
                COLUMN_VILLE + " TEXT," +
                COLUMN_CP + " INTEGER," +
                COLUMN_LICENSE + " BOOLEAN," +
                COLUMN_COMPANY_NAME + " TEXT," +
                COLUMN_PHONE + " TEXT" +
                ")";


        String CREATE_SERVICES_TABLE = "CREATE TABLE " +
                TABLE_SERVICES + "("
                + SERVICE_ID + " INTEGER PRIMARY KEY," +
                SERVICE_USERNAME +
                " TEXT," + TAUX_HORAIRE + " DOUBLE" + ")";

        String CREATE_SERVICE_HORAIRE_TABLE = "CREATE TABLE " +
                TABLE_SERVICE_HORAIRE + "("
                + COLUMN_SERVICES_HORAIRES_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_JOUR + " TEXT,"
                + SERVICE_CHOSE_ID + " INTEGER,"
                + COLUMN_NOTE + " DOUBLE,"
                + COLUMN_NBVOTE + " LONG,"
                + COLUMN_FOURNISSEUR_ID + " INTEGER," +
                COLUMN_HEURE + " TEXT,"
                + " FOREIGN KEY (" + SERVICE_CHOSE_ID + ") REFERENCES " + TABLE_SERVICES + "(" + SERVICE_ID + ")," +
                " FOREIGN KEY (" + COLUMN_FOURNISSEUR_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")" +
                ")";

        String CREATE_RDV_TABLE = "CREATE TABLE " +
                TABLE_SERVICE_RDV + "("
                + COLUMN_RDV_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_ID_USER + " INTEGER,"
                + COLUMN_DISPONIBILITE + " INTEGER,"
                + COLUMN_NOTE_USER + " DOUBLE,"
                + COLUMN_COMS + " TEXT,"
                + " FOREIGN KEY (" + COLUMN_ID_USER + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")," +
                " FOREIGN KEY (" + COLUMN_DISPONIBILITE + ") REFERENCES " + TABLE_SERVICE_HORAIRE + "(" + COLUMN_SERVICES_HORAIRES_ID + ")" +
                ")";


        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_SERVICES_TABLE);
        db.execSQL(CREATE_SERVICE_HORAIRE_TABLE);
        db.execSQL(CREATE_RDV_TABLE);

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, "admin");
        Fournisseur frn = new Fournisseur();
        String mdp = frn.cryptage("admin");
        values.put(COLUMN_MDP, mdp);
        values.put(COLUMN_ROLE, "admin");
        db.insertOrThrow(TABLE_USERS, null, values);


    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_HORAIRE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_RDV);
            onCreate(db);
        }

    }

    public void addOrUpdateUser(User user, String role) {


        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, user.get_username());
            values.put(COLUMN_MDP, user.getMdp());
            values.put(COLUMN_ROLE, role);
            values.put(COLUMN_ADDRESSE, user.getAdresse());
            values.put(COLUMN_CP, user.getCode_postal());
            values.put(COLUMN_VILLE, user.getVille());
            values.put(COLUMN_DESCRIPTION, user.getDescription());
            values.put(COLUMN_LICENSE, user.isLicense());
            values.put(COLUMN_PHONE, user.getPhone());
            values.put(COLUMN_COMPANY_NAME, user.getCompany());


            int rows = db.update(TABLE_USERS, values, COLUMN_ID + "= ?", new String[]{String.valueOf(user.get_id())});

            if (rows == 1) {
                // Get the primary key of the user we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        COLUMN_USERNAME, TABLE_USERS, COLUMN_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(user.get_id())});
                try {
                    if (cursor.moveToFirst()) {
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                db.insertOrThrow(TABLE_USERS, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update user");
        } finally {
            db.endTransaction();
        }

    }


    public boolean isReal(String name, String mdp) {
        SQLiteDatabase db = getReadableDatabase();
        Fournisseur frn = new Fournisseur();
        String mdp_cryp = frn.cryptage(mdp);
        String IsReel =
                String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                        TABLE_USERS,
                        COLUMN_USERNAME,
                        name,
                        COLUMN_MDP,
                        mdp_cryp);
        Cursor cursor = db.rawQuery(IsReel, null);

        if (cursor.moveToFirst()) {

            cursor.close();
            db.close();
            return true;

        } else {
            cursor.close();
            db.close();
            return false;
        }


    }


    public String infoRole(String name, String mdp) {
        SQLiteDatabase db = getReadableDatabase();

        String role = "";
        Fournisseur frn = new Fournisseur();

        String mdp_cryp = frn.cryptage(mdp);


        String infoUser =
                String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                        TABLE_USERS,
                        COLUMN_USERNAME,
                        name,
                        COLUMN_MDP,
                        mdp_cryp);


        Cursor cursor = db.rawQuery(infoUser, null);
        try {
            if (cursor.moveToFirst()) {
                if (cursor.getString(cursor.getColumnIndex(COLUMN_ROLE)) != null) {
                    role = cursor.getString(cursor.getColumnIndex(COLUMN_ROLE));
                } else {
                    role = "Unknown";
                }
            }

        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return role;
    }

       public Fournisseur getFournisseur(String name, String mdp) {
        SQLiteDatabase db = getReadableDatabase();

        String role = "";

        Fournisseur user = new Fournisseur();

        String infoUser =
                String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                        TABLE_USERS,
                        COLUMN_USERNAME,
                        name,
                        COLUMN_MDP,
                        mdp);


        Cursor cursor = db.rawQuery(infoUser, null);
        try {
            if (cursor.moveToFirst()) {
                if (cursor.getString(cursor.getColumnIndex(COLUMN_ROLE)) != null) {
                    user.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                    user.set_username((cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))));
                    user.setMdp((cursor.getString(cursor.getColumnIndex(COLUMN_MDP))));
                    user.setVille((cursor.getString(cursor.getColumnIndex(COLUMN_VILLE))));
                    user.setAdresse((cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESSE))));
                    user.setCode_postal((cursor.getString(cursor.getColumnIndex(COLUMN_CP))));
                    user.setCompany((cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY_NAME))));
                    user.setDescription((cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))));
                    user.setPhone((cursor.getString(cursor.getColumnIndex(COLUMN_PHONE))));
                    user.setLicense(Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_LICENSE))));


                } else {
                    role = "Unknown";
                }
            }

        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return user;
    }

    public Proprietaire getProprietaire(String name, String mdp) {
        SQLiteDatabase db = getReadableDatabase();

        String role = "";


        Proprietaire user = new Proprietaire();

        String infoUser =
                String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                        TABLE_USERS,
                        COLUMN_USERNAME,
                        name,
                        COLUMN_MDP,
                        mdp);


        Cursor cursor = db.rawQuery(infoUser, null);
        try {
            if (cursor.moveToFirst()) {
                if (cursor.getString(cursor.getColumnIndex(COLUMN_ROLE)) != null) {
                    user.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                    user.set_username((cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME))));
                    user.setMdp((cursor.getString(cursor.getColumnIndex(COLUMN_MDP))));
                    user.setVille((cursor.getString(cursor.getColumnIndex(COLUMN_VILLE))));
                    user.setAdresse((cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESSE))));
                    user.setCode_postal((cursor.getString(cursor.getColumnIndex(COLUMN_CP))));
                    user.setCompany((cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY_NAME))));
                    user.setDescription((cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))));
                    user.setPhone((cursor.getString(cursor.getColumnIndex(COLUMN_PHONE))));
                    user.setLicense(Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_LICENSE))));


                } else {
                    role = "Unknown";
                }
            }

        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return user;
    }

    public boolean deleteUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        boolean result = false;
        String query = "SELECT role FROM "
                + TABLE_USERS
                + " WHERE "
                + COLUMN_USERNAME
                + " = \""
                + user.get_username()
                + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_USERS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean isAdmin(String name, String mdp) {


        if (name.equals("admin") && mdp.equals("admin")) {
            return true;

        } else {
            return false;
        }
    }

    public ArrayList listProprio() {
        ArrayList<Proprietaire> list_proprio = new ArrayList<>();

        String PROPRIO_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = '%s' ",
                        TABLE_USERS,
                        COLUMN_ROLE,
                        "Propriétaire");


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(PROPRIO_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Proprietaire newUser = new Proprietaire();
                    newUser.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                    newUser.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                    list_proprio.add(newUser);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }


        return list_proprio;
    }

    public ArrayList listFournisseur() {
        ArrayList<Fournisseur> list_fournisseur = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String FOURNISSEUR_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = '%s' ",
                        TABLE_USERS,
                        COLUMN_ROLE,
                        "Fournisseur");

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(FOURNISSEUR_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Fournisseur newUser = new Fournisseur();
                    newUser.set_username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                    newUser.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));

                    list_fournisseur.add(newUser);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }


        return list_fournisseur;
    }


    public ArrayList listService() {
        ArrayList<Services> list_serv = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String FOURNISSEUR_SELECT_QUERY =
                String.format("SELECT * FROM %s ",
                        TABLE_SERVICES);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(FOURNISSEUR_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Services newService = new Services();

                    newService.setNom(cursor.getString(cursor.getColumnIndex(SERVICE_USERNAME)));
                    newService.setTaux_horraire(Double.parseDouble(cursor.getString(cursor.getColumnIndex(TAUX_HORAIRE))));
                    newService.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_ID))));

                    list_serv.add(newService);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }


        return list_serv;
    }


    public void addOrUpdateService(Services service) {


        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SERVICE_USERNAME, service.getNom());
            values.put(TAUX_HORAIRE, service.getTaux_horraire());


            int rows = db.update(TABLE_SERVICES, values, SERVICE_ID + "= ?", new String[]{String.valueOf(service.getId())});

            if (rows == 1) {
                // Get the primary key of the service we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        SERVICE_USERNAME, TABLE_SERVICES, SERVICE_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(service.getId())});
                try {
                    if (cursor.moveToFirst()) {
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                db.insertOrThrow(TABLE_SERVICES, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update service");
        } finally {
            db.endTransaction();
        }

    }

    public boolean deleteService(int id_service) {
        SQLiteDatabase db = getWritableDatabase();
        boolean result = false;
        String query = "SELECT * FROM "
                + TABLE_SERVICES
                + " WHERE "
                + SERVICE_ID
                + " = \""
                + id_service
                + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICES, SERVICE_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public HashMap<Integer, Service_Disponibilite> getAllDispo()
    {
        HashMap<Integer, Service_Disponibilite> service_horaire = new HashMap<>();


        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT *"
                + " FROM "
                + TABLE_SERVICE_HORAIRE
                + " INNER JOIN "
                + TABLE_SERVICES
                + " ON "
                + SERVICE_ID
                + " = "
                + SERVICE_CHOSE_ID
                ;

        int cmpt = 0;


        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Service_Disponibilite _ns = new Service_Disponibilite();

                    _ns.setHeure(cursor.getString(cursor.getColumnIndex(COLUMN_HEURE)));
                    _ns.setJour(cursor.getString(cursor.getColumnIndex(COLUMN_JOUR)));
                    _ns.setNom_service(cursor.getString(cursor.getColumnIndex(SERVICE_USERNAME)));
                    _ns.setId_service(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_ID))));
                    _ns.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SERVICES_HORAIRES_ID))));
                    _ns.setId_fournisseur(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_FOURNISSEUR_ID))));
                    _ns.setMoyenne(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE))));
                    _ns.setNb_vote(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_NBVOTE))));

                    service_horaire.put(cmpt, _ns);
                    cmpt++;

                } while (cursor.moveToNext());
                cursor.close();

            }


        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        db.close();
        return service_horaire;

    }

    public HashMap<Integer, Service_Disponibilite> ServicesByFournisseur(int id_user) {

        HashMap<Integer, Service_Disponibilite> service_horaire = new HashMap<>();


        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT "
                + SERVICE_USERNAME +","
                + COLUMN_JOUR +","
                + COLUMN_HEURE +","
                + COLUMN_NOTE + ","
                + COLUMN_NBVOTE + ","
                + COLUMN_SERVICES_HORAIRES_ID +","
                + COLUMN_FOURNISSEUR_ID +","
                + SERVICE_ID
                + " FROM "
                + TABLE_SERVICE_HORAIRE
                + " INNER JOIN "
                + TABLE_SERVICES
                + " ON "
                + SERVICE_ID
                + " = "
                + SERVICE_CHOSE_ID
                + " WHERE "
                + COLUMN_FOURNISSEUR_ID
                + " = \""
                + id_user
                + "\"";

        int cmpt = 0;


        Cursor cursor = db.rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Service_Disponibilite _ns = new Service_Disponibilite();

                    _ns.setHeure(cursor.getString(cursor.getColumnIndex(COLUMN_HEURE)));
                    _ns.setJour(cursor.getString(cursor.getColumnIndex(COLUMN_JOUR)));
                    _ns.setNom_service(cursor.getString(cursor.getColumnIndex(SERVICE_USERNAME)));
                    _ns.setId_service(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_ID))));
                    _ns.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_SERVICES_HORAIRES_ID))));
                    _ns.setId_fournisseur(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_FOURNISSEUR_ID))));
                    _ns.setMoyenne(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE))));
                    _ns.setNb_vote(Long.parseLong(cursor.getString(cursor.getColumnIndex(COLUMN_NBVOTE))));

                    service_horaire.put(cmpt, _ns);
                    cmpt++;

                } while (cursor.moveToNext());
                cursor.close();

            }


        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        db.close();
        return service_horaire;

    }

    public HashMap<Integer, Rendez_Vous> ServicesByUser(int id_user){

        SQLiteDatabase db = getWritableDatabase();
        String comm = "";
        HashMap<Integer, Rendez_Vous> service_horaire = new HashMap<>();

        int id = 0;

        int id_rdv = 0;

        String query1 = "SELECT "
                + COLUMN_DISPONIBILITE +","
                + COLUMN_NOTE_USER +","
                + COLUMN_COMS +","
                + COLUMN_RDV_ID
                + " FROM "
                + TABLE_SERVICE_RDV
                + " WHERE "
                + COLUMN_ID_USER
                + " = \""
                + id_user
                + "\"";


        int cmpt = 0;

        Cursor cursor = db.rawQuery(query1, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Rendez_Vous rdv = new Rendez_Vous();

                    id = (Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DISPONIBILITE))));
                    id_rdv = (Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RDV_ID))));
                    comm = cursor.getString(cursor.getColumnIndex(COLUMN_COMS));

                    rdv.setIdDispo(id);
                    rdv.setNote_user(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE_USER))));
                    rdv.setIdRendez_vous(id_rdv);
                    rdv.setCommentaire(comm);

                    String query2 = "SELECT "
                            + SERVICE_USERNAME +","
                            + COLUMN_USERNAME  +","
                            + COLUMN_JOUR +","
                            + COLUMN_HEURE +","
                            + COLUMN_NOTE+ ","
                            + COLUMN_FOURNISSEUR_ID +","
                            + COLUMN_SERVICES_HORAIRES_ID +","
                            + "f."+COLUMN_USERNAME +","
                            + SERVICE_ID
                            + " FROM "
                            + TABLE_SERVICE_HORAIRE
                            + " INNER JOIN "
                            + TABLE_SERVICES
                            + " ON "
                            + SERVICE_ID
                            + " = "
                            + SERVICE_CHOSE_ID
                            + " INNER JOIN "
                            + TABLE_USERS + " AS " +"f"
                            + " ON "
                            + COLUMN_ID
                            + " = "
                            + COLUMN_FOURNISSEUR_ID
                            + " WHERE "
                            + COLUMN_SERVICES_HORAIRES_ID
                            + " = \""
                            + id +"\"";

                    Cursor cursor2 = db.rawQuery(query2, null);
                    try {
                        if (cursor2.moveToFirst()) {
                            do {

                                Service_Disponibilite _serv = new Service_Disponibilite();

                                _serv.setHeure(cursor2.getString(cursor2.getColumnIndex(COLUMN_HEURE)));

                                _serv.setJour(cursor2.getString(cursor2.getColumnIndex(COLUMN_JOUR)));

                                _serv.set_id(Integer.parseInt(cursor2.getString(cursor2.getColumnIndex(SERVICE_ID))));
                                _serv.setName_four((cursor2.getString(cursor2.getColumnIndex(COLUMN_USERNAME))));
                                _serv.setNom_service(cursor2.getString(cursor2.getColumnIndex(SERVICE_USERNAME)));

                                _serv.setMoyenne(cursor2.getDouble(cursor2.getColumnIndex(COLUMN_NOTE)));

                                rdv.setServ(_serv);

                                service_horaire.put(cmpt, rdv);
                                cmpt++;

                            } while (cursor2.moveToNext());
                            cursor2.close();

                        }


                    } catch (Exception e) {
                        Log.d(TAG, "Error while trying to get posts from database");
                    } finally {
                        if (cursor2 != null && !cursor2.isClosed()) {
                            cursor2.close();
                        }
                    }


                } while (cursor.moveToNext());
                cursor.close();

            }


        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }


        db.close();
        return service_horaire;


    }

    public void addOrUpdateDisponibilite(int service_id, String heure, String jour, int four_id, int id_dispo, int nbr_notes, double note) {

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_JOUR, jour);
            values.put(COLUMN_HEURE, heure);
            values.put(COLUMN_FOURNISSEUR_ID, four_id);
            values.put(SERVICE_CHOSE_ID, service_id);
            values.put(COLUMN_NOTE,note);
            values.put(COLUMN_NBVOTE,nbr_notes);

            int rows = db.update(TABLE_SERVICE_HORAIRE, values, COLUMN_SERVICES_HORAIRES_ID + "= ?", new String[]{String.valueOf(id_dispo)});

            if (rows == 1) {

                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        COLUMN_FOURNISSEUR_ID, TABLE_SERVICE_HORAIRE, COLUMN_SERVICES_HORAIRES_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(id_dispo)});
                try {
                    if (cursor.moveToFirst()) {
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                db.insertOrThrow(TABLE_SERVICE_HORAIRE, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update service");
        } finally {
            db.endTransaction();
        }

    }


    public boolean delete_disponibilite(Integer id_dispo){

        SQLiteDatabase db = getWritableDatabase();
        boolean result = false;

        String query = "SELECT * FROM "
                + TABLE_SERVICE_HORAIRE
                + " WHERE "
                + COLUMN_SERVICES_HORAIRES_ID
                + " = \""
                + id_dispo
                + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICE_HORAIRE, COLUMN_SERVICES_HORAIRES_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;

    }

    public boolean delete_Rdv(int id_user, int id_dispo){

        SQLiteDatabase db = getWritableDatabase();
        boolean result = false;

        String query = "SELECT * FROM "
                + TABLE_SERVICE_RDV
                + " WHERE "
                + COLUMN_ID_USER
                + " = \""
                + id_user
                + "\""
                + " AND "
                + COLUMN_DISPONIBILITE
                + " = \""
                + id_dispo
                + "\""
                ;


        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICE_RDV, COLUMN_RDV_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void ajout_RDV(int id_user,int id_dispo, int id_rdv, String commentaire,double note){


        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_DISPONIBILITE, id_dispo);
            values.put(COLUMN_ID_USER, id_user);
            values.put(COLUMN_COMS, commentaire);
            values.put(COLUMN_NOTE_USER,note);



            int rows = db.update(TABLE_SERVICE_RDV, values, COLUMN_RDV_ID + "= ?", new String[]{String.valueOf(id_rdv)});

            if (rows == 1) {
                // Get the primary key of the  we just updated
                String usersSelectQuery = String.format("SELECT %s FROM %s WHERE %s = ?",
                        COLUMN_ID_USER, TABLE_SERVICE_RDV, COLUMN_RDV_ID);
                Cursor cursor = db.rawQuery(usersSelectQuery, new String[]{String.valueOf(id_rdv)});
                try {
                    if (cursor.moveToFirst()) {
                        db.setTransactionSuccessful();
                    }
                } finally {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                }
            } else {
                // user with this userName did not already exist, so insert new user
                db.insertOrThrow(TABLE_SERVICE_RDV, null, values);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add or update service");
        } finally {
            db.endTransaction();
        }


    }


    public int nombre_vote_disponibilite(int id_dispo){


        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT "
                + COLUMN_NBVOTE
                + " FROM "
                + TABLE_SERVICE_HORAIRE
                + " WHERE "
                + COLUMN_SERVICES_HORAIRES_ID
                + " = \""
                + id_dispo
                + "\""
                ;


        Cursor cursor = db.rawQuery(query, null);
        int nbr_vote=0;

        if (cursor.moveToFirst()) {

            nbr_vote = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_NBVOTE)));
            cursor.close();
        }
        db.close();
        return nbr_vote;
    }


    public double moyenne_dispo(int id_dispo){


        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT "
                + COLUMN_NOTE
                + " FROM "
                + TABLE_SERVICE_HORAIRE
                + " WHERE "
                + COLUMN_SERVICES_HORAIRES_ID
                + " = \""
                + id_dispo
                + "\""
                ;


        Cursor cursor = db.rawQuery(query, null);
        double moyenne=0.0;

        if (cursor.moveToFirst()) {

            moyenne = cursor.getDouble(0);
            cursor.close();
        }
        db.close();
        return moyenne;
    }


}







