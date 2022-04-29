package com.example.app2;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddCocktailActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 0;
    private static final  int RESULT_LOAD_IMAGE = 1;

    private ImageButton cancel;
    private ImageButton save;
    private ImageButton addPicture;

    private String imgPath = "";
    private boolean isPathImage = false;

    private EditText name;
    private EditText description;
    private EditText preparation;
    private CheckBox isAlcohol;
    private Spinner typeofalcohol;

    private EditText addanIngredient_name;
    private EditText addanIngredient_quantity;
    private Spinner addanIngredient_whichunity;
    private Button addAnIngredient;

    ListView LvIngredient;

    public String[] listofdifferentquantity = {"ml","half",
            "piece", "skin","quarter","splash","cup",
            "third","stalk","dash","slice","zest"
    };


    public String[] type = {
            "None","Hard","Half_Hard","Light","Long","Shoot"};

    public ArrayList<Ingrédient> listingredient = new ArrayList<Ingrédient>();

    public ArrayList<Quantity> listquantity= new ArrayList<Quantity>();;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcocktail);


        addPicture = (ImageButton) findViewById(R.id.addpicture);
        name = (EditText) findViewById(R.id.nameimput);
        description = (EditText) findViewById(R.id.editdescription);
        preparation = (EditText) findViewById(R.id.editpreparation);
        isAlcohol = (CheckBox) findViewById(R.id.check_alcohol);
        typeofalcohol = (Spinner) findViewById(R.id.typeofalcohol);
        LvIngredient = (ListView) findViewById(R.id.listview_ingredient);
        addPicture = (ImageButton) findViewById(R.id.addpicture);


        addanIngredient_name = (EditText) findViewById(R.id.editaddingredient_name);
        addanIngredient_quantity = (EditText) findViewById(R.id.editaddingredient_quantity);
        addanIngredient_whichunity = (Spinner) findViewById(R.id.editaddingredient_whichquantity);
        addAnIngredient = (Button) findViewById(R.id.editaddingredient_add);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_item,listofdifferentquantity);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        this.addanIngredient_whichunity.setAdapter(adapter);



        IngredientCustomAdapter ingredientCustomAdapter = new IngredientCustomAdapter(AddCocktailActivity.this,listingredient,listquantity);
        LvIngredient.setAdapter(ingredientCustomAdapter);;

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //access to galery
                System.out.println("je suis la");
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
            }
        });


        addAnIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnIngredient(ingredientCustomAdapter);
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                R.layout.spinner_item,
                type);
        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        this.typeofalcohol.setAdapter(adapter2);



        cancel = (ImageButton) findViewById(R.id.add_cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myPopUp= new AlertDialog.Builder(AddCocktailActivity.this);
                myPopUp.setTitle("Warning");
                myPopUp.setMessage("You don't save your new cocktail.\nDo you want to continue?");
                myPopUp.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent result = new Intent();
                        result.putExtra("isnewcocktail",-1);
                        setResult(RESULT_OK,result);
                        finish();
                    }
                });
                myPopUp.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                myPopUp.show();
            }
        });
        save = (ImageButton) findViewById(R.id.add_complete);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO VERIFIER LES CONDITIONS SI ON PEUT SAUVEGARDER
                AlertDialog.Builder myPopUp= new AlertDialog.Builder(AddCocktailActivity.this);
                myPopUp.setTitle("Warning");
                myPopUp.setMessage("Add the cocktail?");
                myPopUp.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO METTRE LE COCKTAIL DANS L'INTENT
                        ingredientCustomAdapter.notifyDataSetChanged();
                        Intent result = new Intent(AddCocktailActivity.this,MainActivity.class);
                        Cocktail newcoc = newCocktail();
                        result.putExtra("MyNewCocktail",newcoc);
                        result.putExtra(MainActivity.Add_cocktail,1);
                        MainActivity.transferdonnee = 1;
                        MainActivity.addcocktail = newcoc;
                        setResult(RESULT_OK,result);
                        MainActivity.controle.creerCocktail(newcoc);
                        finish();
                    }//TODO GESTION FINISH INTENT AVEC L INTENT DE LA PHOTO
                });
                myPopUp.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                myPopUp.show();
            }
        });
        
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }
        
    }

    public Cocktail newCocktail ()
    {
        //TODO S OCCUPER D AJOUTER UNE IMAGE
        Bitmap img = null;
        String name = this.name.getText().toString();
        String description = this.description.getText().toString();
        String preparation = this.preparation.getText().toString();
        Boolean isalcohol = this.isAlcohol.isChecked();
        String typeofalcohol = this.typeofalcohol.getSelectedItem().toString();



        boolean isfavorite = false;
        int numberofingredient = 0; // TODO IMPLEMENTER L ACTUALISATION AVEC INGREDIENT

        typeofalcohol a = stringtotypeofalcohol(typeofalcohol);
        Cocktail result = new Cocktail(name,description,imgPath,preparation,listingredient,listquantity,isfavorite,isalcohol,a,1);//TODO FIXER AVEC LE PROBLEME DE ID

        return result;

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
        System.out.println("ERROR TYPE OF ALCOHOL NON RECONNU");
        return null;
    }

    public void PrintACocktail (Cocktail a )
    {
        System.out.println("Name: "+a.name+"\n");
        System.out.println("Description: "+a.description+"\n");
        System.out.println("Recette: "+a.stateofpreparation+"\n");
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

    public void addAnIngredient (IngredientCustomAdapter a)
    {

        String name = addanIngredient_name.getText().toString();
        int quantity;
        if (name.equals(""))
        {
            Toast.makeText(this,"Fill a name",Toast.LENGTH_SHORT).show();
            addanIngredient_name.setText("");
            addanIngredient_name.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);

            return;
        }
        addanIngredient_name.getBackground().mutate().setColorFilter(getResources().getColor(R.color.duckblue), PorterDuff.Mode.SRC_ATOP);
        try {

            quantity = Integer.parseInt(addanIngredient_quantity.getText().toString());
        }
        catch (Exception e)
        {

            Toast.makeText(this,"Fill a int in Quantity",Toast.LENGTH_SHORT).show();
            addanIngredient_quantity.setText("");
            addanIngredient_quantity.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            return;
        }
        whichunity unity = stringtowhichunity(addanIngredient_whichunity.getSelectedItem().toString());

        Ingrédient ingrédient = new Ingrédient(name,"null");
        Quantity quantity1 = new Quantity(quantity,unity);

        listingredient.add(ingrédient);
        listquantity.add(quantity1);
        a.notifyDataSetChanged();

        addanIngredient_name.setText("");
        addanIngredient_quantity.setText("");

        addanIngredient_quantity.getBackground().mutate().setColorFilter(getResources().getColor(R.color.duckblue), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imgPath = cursor.getString(columnIndex);
                    cursor.close();
                    final Bitmap image = BitmapFactory.decodeFile((imgPath));
                    addPicture.setImageBitmap(image);
                    isPathImage = true;
                }
        }


    }

}
