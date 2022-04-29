package com.example.app2;

import android.content.Context;

import java.util.ArrayList;

public final class Controle {

    private static Controle instance = null;
    private static Cocktail cocktail;
    private static SQLAccessLocal accessLocal;

    private Controle(){super();}

    public static final Controle getInstance(Context context)
    {
        if (Controle.instance == null)
        {
            Controle.instance = new Controle();
            accessLocal = new SQLAccessLocal(context);
            cocktail = accessLocal.recupDernier();
        }
        return Controle.instance;
    }

    public void creerCocktail(Cocktail cocktail)
    {
        accessLocal.add(cocktail);
    }

    public ArrayList<Cocktail> recuplistCocktail()
    {
        return accessLocal.recuplist();

    }

    public String getName(){return cocktail.getName();}
    public String getDescription(){return cocktail.getDescription();}
    public int getLogo(){return cocktail.getLogo();}
    public int getId(){return cocktail.getId();}

}
