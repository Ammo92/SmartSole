package com.developpement.guide.solesmart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;

/**
 * Created by KGULJIN on 31/05/2018.
 */

public class InfoBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "infos.db";

    private static final String TABLE_INFO = "table_informations";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_CALORIES = "Calorie";
    private static final int NUM_COL_CALORIES = 1;
    private static final String COL_STEP = "Pas";
    private static final int NUM_COL_STEP  = 2;

    private SQLiteDatabase bdd;

    private SQLite maBaseSQLite;

    public InfoBDD(Context context){
        maBaseSQLite = new SQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertInfo(Information info){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_CALORIES, info.getCalorie());
        values.put(COL_STEP, info.getPas());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_INFO, null, values);
    }

    public int updateInfo(int id, Information info){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_CALORIES, info.getCalorie());
        values.put(COL_STEP, info.getPas());
        return bdd.update(TABLE_INFO, values, COL_ID + " = " +id, null);
    }

    public int removeInfo(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_INFO, COL_ID + " = " +id, null);
    }


    public Information getInfoWithPas(int pas){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_INFO, new String[] {COL_ID, COL_CALORIES, COL_STEP}, COL_CALORIES + " LIKE \"" + pas +"\"", null, null, null, null);
        return cursorToLivre(c);
    }

    private Information cursorToLivre(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Information info = new Information();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        info.setId(c.getInt(NUM_COL_ID));
        info.setCalorie(c.getInt(NUM_COL_CALORIES));
        info.setPas(c.getInt(NUM_COL_STEP));

        //On ferme le cursor
        c.close();

        //On retourne le livre
        return info;
    }


}


