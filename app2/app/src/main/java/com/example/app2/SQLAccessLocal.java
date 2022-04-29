package com.example.app2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SQLAccessLocal {

    // proprieties
    private String nomBase = "bdcocktail.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accessDB;
    private SQLiteDatabase db;

    // constructor
    public SQLAccessLocal(Context context){
        this.accessDB = new MySQLiteOpenHelper(context,nomBase,null,versionBase);
    }








    public void add(Cocktail cocktail)
    {
        db = accessDB.getWritableDatabase();
        //String req = "insert into cocktail (id, name, description) values ";
        //req+= "("+cocktail.getId()+","+cocktail.getName()+","+cocktail.getDescription()+")";
        System.out.println(cocktail.getName());
        int isfavorite = 0;
        int isAlcohol = 0;
        if (cocktail.isFavorite)
        {
            isfavorite = 1;
        }
        if(cocktail.isAlcohol)
        {
            isAlcohol = 1;
        }
        String typeofalcohol = typeofalcoholtostring(cocktail.typeofalcohol);


        String req = "INSERT INTO Cocktail " +
                "(id,name,description,logo,imgpath,stepsofpreparation,listofingredient,listofquantity," +
                "numberofingredient,isFavorite,isAlcohol,typeofalcohol) VALUES " +
                "(\""+cocktail.getId()+"\"," +
                "\""+cocktail.getName()+"\","+
                "\""+cocktail.getDescription()+"\","+
                "\""+cocktail.getLogo()+"\","+
                "\""+cocktail.getImgpath()+"\","+
                "\""+cocktail.getStateofpreparation()+"\","+
                "\""+cocktail.getListofingredient()+"\","+
                "\""+cocktail.getListofquantity()+"\","+
                "\""+cocktail.getNumberofingredient()+"\","+
                "\""+isfavorite+"\","+
                "\""+isAlcohol+"\","+
                "\""+typeofalcohol+"\")";

        db.execSQL(req);
    }
    /*
    *+ "id INTEGER PRIMARY KEY,"
            + "name TEXT NOT NULL,"
            + "description TEXT NOT NULL,"
            + "logo INTEGER NOT NULL,"
            + "imgpath STRING NOT NULL,"
            + "stepsofpreparation STRING NOT NULL,"
            + "listofingredient STRING NOT NULL,"
            + "listofquantity STRING NOT NULL,"
            + "numberofingredient INTEGER NOT NULL,"
            + "isFavorite INTEGER NOT NULL,"
            + "isAlcohol INTEGER NOT NULL,"
            + "typeofalcohol STRING NOT NULL);";
     */

    public ArrayList<Cocktail> recuplist ()
    {
        ArrayList<Cocktail> finalresult = new ArrayList<Cocktail>();
        db = accessDB.getReadableDatabase();
        Cocktail cocktail = null;
        String req = "select * from cocktail";
        Cursor cursor = db.rawQuery(req,null);
        if (cursor.moveToFirst())
        {
            while(!cursor.isAfterLast())
            {

                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                int logo = cursor.getInt(3);
                String imgpath = cursor.getString(4);
                String steopofprep = cursor.getString(5);
                ArrayList<Ingrédient> listofing = stringtoListIngredient( cursor.getString(6));
                ArrayList<Quantity> listofquantity =stringtoListQuantity( cursor.getString(7));
                int numberofingredient = cursor.getInt(8);
                boolean isFavorite = false;
                if (cursor.getInt(9)==1)
                    isFavorite = true;
                boolean isAlcohol = false;
                if (cursor.getInt(10)==1)
                    isAlcohol = true;
                typeofalcohol typeofalcohol = stringtotypeofalcohol(cursor.getString(11));
                cocktail = new Cocktail(name,description,imgpath,steopofprep,listofing,listofquantity,isFavorite,isAlcohol,typeofalcohol,id);
                finalresult.add(cocktail);
                System.out.println("String sortant : "+cursor.getString(10));
                cursor.moveToNext();
            }
        }
        cursor.close();
        PrintACocktail(finalresult.get(0));
        return finalresult;
    }

    public Cocktail recupPosition (int index)
    {
        db = accessDB.getReadableDatabase();
        Cocktail cocktail = null;
        String req = "select * from cocktail";
        Cursor cursor = db.rawQuery(req,null);
        //TODO STRANGE TO TAKE THE LAST cursor.moveTOPosition(int i)
        cursor.moveToPosition(index); //Cursor allows to move between the element of the table
        if (!cursor.isAfterLast())
        {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            int logo = cursor.getInt(3);
            String imgpath = cursor.getString(4);
            String steopofprep = cursor.getString(5);
            ArrayList<Ingrédient> listofing = stringtoListIngredient( cursor.getString(6));
            ArrayList<Quantity> listofquantity =stringtoListQuantity( cursor.getString(7));
            int numberofingredient = cursor.getInt(8);
            boolean isFavorite = false;
            if (cursor.getInt(9)==1)
                isFavorite = true;
            boolean isAlcohol = false;
            if (cursor.getInt(10)==1)
                isAlcohol = true;
            typeofalcohol typeofalcohol = stringtotypeofalcohol(cursor.getString(10));
            cocktail = new Cocktail(name,description,imgpath,steopofprep,listofing,listofquantity,isFavorite,isAlcohol,typeofalcohol,id);


        }
        cursor.close();
        return cocktail;
    }

    public Cocktail recupDernier ()
    {
        db = accessDB.getReadableDatabase();
        Cocktail cocktail = null;
        String req = "select * from cocktail";
        Cursor cursor = db.rawQuery(req,null);
        //TODO STRANGE TO TAKE THE LAST cursor.moveTOPosition(int i)
        cursor.moveToLast(); //Cursor allows to move between the element of the table
        if (!cursor.isAfterLast())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            cocktail = new Cocktail(name,description,-1,id);
        }
        cursor.close();
        return cocktail;

    }
    public void supprimer(long id) {
        db.delete("cocktail", "id = ?", new String[] {String.valueOf(id)});
    }


    public typeofalcohol stringtotypeofalcohol (String a)
    {
        switch (a)
        {
            case "None":
                return com.example.app2.typeofalcohol.None;
            case "Light":
                return com.example.app2.typeofalcohol.Light;
            case "Long":
                return com.example.app2.typeofalcohol.Long;
            case "Half_Hard":
                return com.example.app2.typeofalcohol.Half_Hard;
            case "Hard":
                return com.example.app2.typeofalcohol.Hard;
            case "Shoot":
                return com.example.app2.typeofalcohol.Shoot;
        }
        System.out.println("ERROR TYPE OF ALCOHOL NON RECONNU : "+a);
        return null;
    }

    public ArrayList<Ingrédient> stringtoListIngredient (String a)
    {

        //TODO LA LIST QUE L ON ENVOIT DOIT ETRE : INGREDIENT+ +INGREDIENT
        ArrayList<Ingrédient> finallist = new ArrayList<Ingrédient>();
        int size  = a.length();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i<size; i++)
        {
            char temp2 = a.charAt(i);
            if (temp2 == ' ')
            {
                Ingrédient temp3 = new Ingrédient(temp.toString(),"");
                finallist.add(temp3);
                temp = new StringBuilder();
            }
            else
            {
                temp.append(temp2);
            }
        }

        return finallist;
    }
    public whichunity stringtowhichunity (String a)
    {
        switch (a)
        {
            case "ml":
                return com.example.app2.whichunity.ml;
            case "half":
                return com.example.app2.whichunity.half;
            case "piece":
                return com.example.app2.whichunity.piece;
            case "skin":
                return com.example.app2.whichunity.skin;
            case "quarter":
                return com.example.app2.whichunity.quarter;
            case "splash":
                return com.example.app2.whichunity.splash;
            case "cup":
                return com.example.app2.whichunity.cup;
            case "third":
                return com.example.app2.whichunity.third;
            case "stalk":
                return com.example.app2.whichunity.stalk;
            case "dash":
                return com.example.app2.whichunity.dash;
            case "slice":
                return com.example.app2.whichunity.slice;
            case "zest":
                return com.example.app2.whichunity.zest;
        }
        return com.example.app2.whichunity.ml;
    }

    public ArrayList<Quantity> stringtoListQuantity(String a)
    {
        //TODO LA LIST QUE L ON ENVOIE EST DE TYPE: INT1|whichquantity1+ +INT2|whichquantity2
        ArrayList<Quantity> finallist = new ArrayList<Quantity>();
        int size  = a.length();
        boolean isSecond = false;
        StringBuilder tempquantity = new StringBuilder();
        StringBuilder tempwhichunity = new StringBuilder();
        for (int i = 0; i<size; i++)
        {
            char temp2 = a.charAt(i);
            if (temp2 == ' ')
            {
                Quantity temp3= new Quantity(Integer.parseInt(tempquantity.toString()),stringtowhichunity(tempwhichunity.toString())) ;
                finallist.add(temp3);
                tempquantity = new StringBuilder();
                tempwhichunity = new StringBuilder();
                isSecond = false;
            }
            else
            {
                if (temp2 == '|')
                {
                    isSecond = true;
                }
                else {
                    if (!isSecond) {
                        tempquantity.append(temp2);
                    } else {
                        tempquantity.append(temp2);

                    }
                }
            }
        }

        return finallist;


    }

    public void PrintACocktail ( Cocktail a )
    {
        System.out.println("Name: "+a.name+"\n");
        System.out.println("Description: "+a.description+"\n");
        System.out.println("Recette: "+a.stateofpreparation+"\n");

        System.out.println("typeofalcohol: "+a.typeofalcohol+"\n");
    }
    public String typeofalcoholtostring (typeofalcohol a)
    {
        switch (a){
            case None:
                return "None";
            case Light:
                return "Light";
            case Long:
                return "Long";
            case Half_Hard:
                return "half_Hard";
            case Hard:
                return "Hard";
            case Shoot:
                return "Shoot";
        }
        return "None";
    }


}

