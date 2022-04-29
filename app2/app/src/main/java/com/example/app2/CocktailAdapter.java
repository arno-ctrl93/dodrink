package com.example.app2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class CocktailAdapter extends BaseAdapter {
    Context context;
    static LayoutInflater inflater = null;

    public ArrayList<Cocktail> listcocktail;

    public CocktailAdapter(Context context, ArrayList<Cocktail> listcocktail ){
        this.context = context;
        this.listcocktail = listcocktail;
    }

    @Override
    public int getCount(){
        return listcocktail.size();
    }

    @Override
    public Object getItem(int position){
        return getItemId(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        View row = convertView;
        if (row == null)
        {
            inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row2,null);
        }
        ProgressBar pBIngredient = (ProgressBar) row.findViewById(R.id.progressbar_ingredient);
        ProgressBar pBIndiceAlcohol = (ProgressBar) row.findViewById(R.id.indice_alcohol);
        ImageView img = (ImageView) row.findViewById(R.id.row2_image);
        ImageView imgAlchol = (ImageView) row.findViewById(R.id.row2_alcoholicon);//TODO GERER DIFFERENTE IMAGE EN FONCTION VALEUR BOOL
        TextView titel = (TextView) row.findViewById(R.id.row2_name);
        TextView description = (TextView) row.findViewById(R.id.row2_description);


        if (listcocktail.get(position).imgpath !="")
        {
            File dossier=new File(listcocktail.get(position).imgpath);
            if (dossier.exists())
            {
                Bitmap image = BitmapFactory.decodeFile((listcocktail.get(position).imgpath));
                img.setImageBitmap(image);
            }
            else
            {
                img.setImageResource(listcocktail.get(position).logo);
            }
        }
        else
        {
            img.setImageResource(listcocktail.get(position).logo);
        }
        titel.setText(listcocktail.get(position).name);
        description.setText(listcocktail.get(position).description);
        pBIngredient.setProgress(50);
        pBIndiceAlcohol.setProgress(setvalueIndiceAlcohol(listcocktail.get(position)));
        //pBIndiceAlcohol.setProgress(20);
        return row;
    }

    public int setvalueIndiceAlcohol (Cocktail a)
    {
        switch (a.typeofalcohol){
            case None:
                return 0;
            case Light:
                return 20;
            case Long:
                return 40;
            case Half_Hard:
                return 60;
            case Hard:
                return 80;
            case Shoot:
                return 100;
        }
        return 50;
    }
}
