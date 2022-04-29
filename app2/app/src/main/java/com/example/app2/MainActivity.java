package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


//TODO FAIRE LE SYSTEME DE SAVE AND LOAD DE LA
//TODO FAIRE LE SYSTEME DE TRADUCTION A VOIR LE LIEN SAVE SUR LE SUJET

public class MainActivity extends AppCompatActivity {


    ListView Lv;
    /*Cocktail mojito = new Cocktail("Mojito","Je suis le meilleur des cocktails",R.drawable.mojito,1);
    Cocktail mojito2 = new Cocktail("Leo","Je suis le meilleur des cocktails",R.drawable.mojito,2);
    Cocktail mojito3 = new Cocktail("Teo","Je suis le meilleur des cocktails",R.drawable.mojito,3);
    Cocktail mojito4 = new Cocktail("Fabrice","Je suis le meilleur des cocktails",R.drawable.mojito,4);
    Cocktail mojito5 = new Cocktail("vadim","Je suis le meilleur des cocktails",R.drawable.mojito,5);
    Cocktail mojito6 = new Cocktail("elephant","Je suis le meilleur des cocktails",R.drawable.mojito,6);
    Cocktail mojito7 = new Cocktail("zidane","Je suis le meilleur des cocktails",R.drawable.mojito,7);
    */

    public ArrayList<Cocktail> listCocktail = new ArrayList<Cocktail>();

    public final static int CHOOSE_COCKTAIL_REQUEST = 0;
    public final static int ADD_COCKTAIL_REQUEST = 1;

    public final static  String Add_cocktail = "com.example.app2.intent.example.add_cocktail";


    public final static String COCKTAIL_Name = "com.example.app2.intent.example.cocktail_name";
    public final static String COCKTAIL_Description = "com.example.app2.intent.example.cocktail_description";
    public final static String COCKTAIL_Icone = "com.example.app2.intent.example.cocktail_icone";

    public static Cocktail addcocktail = new Cocktail(8);
    public static int transferdonnee = -1;

    public static Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.controle = Controle.getInstance(this);
        //recupProfil();

        /*listCocktail.add(mojito);
        listCocktail.add(mojito2);
        listCocktail.add(mojito3);
        listCocktail.add(mojito4);
        listCocktail.add(mojito5);
        listCocktail.add(mojito6);
        listCocktail.add(mojito7);*/

        listCocktail = controle.recuplistCocktail();

        Lv = (ListView) findViewById(R.id.ListView);
        Lv.setAdapter(new CocktailAdapter(MainActivity.this,listCocktail));
        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent secondActivite = new Intent(MainActivity.this,ShowCocktailActivity.class);
                secondActivite.putExtra("myCocktail",listCocktail.get(position));
                startActivityForResult(secondActivite,CHOOSE_COCKTAIL_REQUEST);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                Toast.makeText(MainActivity.this,"List item was clicked in position: "+position,Toast.LENGTH_SHORT).show();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondActivite = new Intent(MainActivity.this,AddCocktailActivity.class);
                startActivityForResult(secondActivite,ADD_COCKTAIL_REQUEST);
            }
        });

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        ImageView menu = (ImageView) findViewById(R.id.imagemenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_COCKTAIL_REQUEST) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "SUCEED", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == ADD_COCKTAIL_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {

                Intent j = getIntent();
                int isnew = j.getIntExtra(Add_cocktail,-1);
                System.out.println("Le chiffre doit être :"+transferdonnee);
                if (transferdonnee != -1)
                {

                    this.listCocktail.add(addcocktail);
                    PrintACocktail(addcocktail);
                    Lv.setAdapter(new CocktailAdapter(MainActivity.this,this.listCocktail));
                    Toast.makeText(this,"New Cocktail !",Toast.LENGTH_SHORT).show();

                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                Toast.makeText(this, "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_sort_AZ:
                SortbyAZ(listCocktail);
                return true;
            case R.id.menu_activity_sort_ZA:
                SortbyZA(listCocktail);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void SortbyAZ(ArrayList<Cocktail> listcocktail)
    {
        quickSortA_Z(listcocktail,0,listcocktail.size()-1);
        this.listCocktail = listcocktail;
        Lv.setAdapter(new CocktailAdapter(MainActivity.this,this.listCocktail));

    }

    public void SortbyZA(ArrayList<Cocktail> listcocktail)
    {
        quicksortZ_A(listcocktail,0,listcocktail.size()-1);
        this.listCocktail = listcocktail;
        Lv.setAdapter(new CocktailAdapter(MainActivity.this,this.listCocktail));
    }


    // Implementation of QuickSort

    public void swap (ArrayList<Cocktail> arr, int i, int j)
    {
        Cocktail temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }

    public int partitionA_Z(ArrayList<Cocktail> arr, int low, int high)
    {
        Cocktail pivot = arr.get(high);

        int i = (low-1);
        for (int j =low;j<=high-1;j++)
        {
            if (arr.get(j).name.compareToIgnoreCase(pivot.name)<0)
            {
                i++;
                swap(arr,i,j);

            }
        }
        swap(arr,i+1,high);
        return (i+1);
    }

    public void quickSortA_Z(ArrayList<Cocktail> arr,int low, int high)
    {
        if (low<high)
        {
            int pi = partitionA_Z(arr,low,high);
            quickSortA_Z(arr,low,pi-1);
            quickSortA_Z(arr,pi+1,high);
        }
    }

    public int partitionZ_A(ArrayList<Cocktail> arr, int low, int high)
    {
        Cocktail pivot = arr.get(high);

        int i = (low-1);
        for (int j =low;j<=high-1;j++)
        {
            if (arr.get(j).name.compareToIgnoreCase(pivot.name)>0)
            {
                i++;
                swap(arr,i,j);

            }
        }
        swap(arr,i+1,high);
        return (i+1);
    }

    public void quicksortZ_A(ArrayList<Cocktail> arr,int low,int high)
    {
        if(low<high)
        {
            int pi = partitionZ_A(arr,low,high);
            quicksortZ_A(arr,low,pi-1);
            quicksortZ_A(arr,pi+1,high);
        }
    }

    public void PrintACocktail ( Cocktail a )
    {
        System.out.println("Name: "+a.name+"\n");
        System.out.println("Description: "+a.description+"\n");
        System.out.println("Recette: "+a.stateofpreparation+"\n");
    }

    //TODO LIST QUI CHANGE DANS LA MAIN APRES AVOIR VU UN COCKTAIL

    public void recupProfil(){
        if (controle.getName()!=null)
        {
            Toast.makeText(this,controle.getName()+" "+controle.getId(),Toast.LENGTH_LONG).show();

        }
    }

}