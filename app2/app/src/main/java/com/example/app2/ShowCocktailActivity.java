package com.example.app2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.io.File;

public class ShowCocktailActivity extends AppCompatActivity {

    private TextView titel =null;
    private TextView description = null;
    private ImageView logo = null;
    private ProgressBar LevelOfAlcohol = null;
    private TextView StepOfPreparation = null;
    private ProgressBar IndiceOfIngredient = null;
    private ImageView isAlcohol = null;
    private ListView lv = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcocktail);

        Intent i = getIntent();

        titel = (TextView) findViewById(R.id.showTitel);
        description = (TextView) findViewById(R.id.showDescription);
        logo = (ImageView) findViewById(R.id.showIcone);
        isAlcohol = (ImageView) findViewById(R.id.showisAlcohol);
        LevelOfAlcohol = (ProgressBar) findViewById(R.id.showindicealcohol);
        StepOfPreparation = (TextView) findViewById(R.id.showPreparation);
        IndiceOfIngredient = (ProgressBar) findViewById(R.id.shownumberofingredient);
        lv = (ListView) findViewById(R.id.showlistview_ingredient);


        Cocktail cocktail = (Cocktail) i.getSerializableExtra("myCocktail");

        titel.setText(cocktail.name);
        description.setText(cocktail.description);
        if (cocktail.imgpath != "")
        {
            File dossier=new File(cocktail.imgpath);
            if (dossier.exists())
            {
                Bitmap image = BitmapFactory.decodeFile((cocktail.imgpath));
                logo.setImageBitmap(image);
            }
        }
        else {
            logo.setImageResource(cocktail.logo);
        }

        StepOfPreparation.setText(cocktail.stateofpreparation);
        LevelOfAlcohol.setProgress(setvalueIndiceAlcohol(cocktail));

        Show_IngredientCustomAdapter adapter =
                new Show_IngredientCustomAdapter(ShowCocktailActivity.this,cocktail.listofingredient,cocktail.listofquantity);
        lv.setAdapter(adapter);



        this.configureToolbar();
    }



    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_showcoktail);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
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
