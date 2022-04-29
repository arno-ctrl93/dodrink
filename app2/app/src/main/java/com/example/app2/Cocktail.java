package com.example.app2;

import android.graphics.Bitmap;
import android.media.Image;

import java.awt.font.NumericShaper;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Cocktail implements Serializable {

    //TODO GERER ID
    public int id;
    // Fields

    // name, description, logo and stateofprepartation are just basics information about our cocktail

    public String name;
    public String description;

    public int logo;
    public String imgpath;
    public String stateofpreparation;

    // listofingredient and listofquantity are two lists link between them because of the same position in the two lists indicates which quantity for which ingredent
    public ArrayList<Ingrédient> listofingredient;
    public ArrayList<Quantity> listofquantity;

    // index to help when we construct pannel
    public int numberofingredient;

    // boolean to add an option
    public boolean isFavorite;

    // boolean to help the sort
    public boolean isAlcohol;
    public typeofalcohol typeofalcohol;

    public Cocktail(String name, String description, String imgpath, String stateofpreparation,
                    ArrayList<Ingrédient> listofingredient,ArrayList<Quantity> listofquantity,
                    boolean isFavorite, boolean isAlcohol, com.example.app2.typeofalcohol typeofalcohol,int id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = R.drawable.photodefault;
        this.imgpath = imgpath;
        this.stateofpreparation = stateofpreparation;
        this.listofingredient = listofingredient;
        this.listofquantity = listofquantity;
        this.numberofingredient = listofingredient.size();
        this.isFavorite = isFavorite;
        this.isAlcohol = isAlcohol;
        this.typeofalcohol = typeofalcohol;
    }

    public Cocktail(int id)
    {
        this.id = id;
        this.name = null;
        this.description = null;
        this.imgpath ="";
        this.logo = R.drawable.photodefault;
        this.stateofpreparation = null;
        this.listofingredient = null;
        this.listofquantity = null;
        this.numberofingredient = -1;
        this.isFavorite = false;
        this.isAlcohol = false;
        this.typeofalcohol = com.example.app2.typeofalcohol.None;
    }

    public Cocktail(String name, String description,int logo,int id)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        if (logo == -1)
        {
            this.logo = R.drawable.photodefault;
        }
        else
        {
            this.logo = logo;
        }
        this.imgpath = "";
        this.stateofpreparation = null;
        this.listofingredient = null;
        this.listofquantity = null;
        this.numberofingredient = -1;
        this.isFavorite = false;
        this.isAlcohol = true;
        this.typeofalcohol = com.example.app2.typeofalcohol.Long;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getStateofpreparation() {
        return stateofpreparation;
    }

    public void setStateofpreparation(String stateofpreparation) {
        this.stateofpreparation = stateofpreparation;
    }

    public ArrayList<Ingrédient> getListofingredient() {
        return listofingredient;
    }

    public void setListofingredient(ArrayList<Ingrédient> listofingredient) {
        this.listofingredient = listofingredient;
    }

    public ArrayList<Quantity> getListofquantity() {
        return listofquantity;
    }

    public void setListofquantity(ArrayList<Quantity> listofquantity) {
        this.listofquantity = listofquantity;
    }

    public int getNumberofingredient() {
        return numberofingredient;
    }

    public void setNumberofingredient(int numberofingredient) {
        this.numberofingredient = numberofingredient;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isAlcohol() {
        return isAlcohol;
    }

    public void setAlcohol(boolean alcohol) {
        isAlcohol = alcohol;
    }

    public com.example.app2.typeofalcohol getTypeofalcohol() {
        return typeofalcohol;
    }

    public void setTypeofalcohol(com.example.app2.typeofalcohol typeofalcohol) {
        this.typeofalcohol = typeofalcohol;
    }
}
