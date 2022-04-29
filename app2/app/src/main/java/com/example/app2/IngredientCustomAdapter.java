package com.example.app2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientCustomAdapter extends BaseAdapter {
    Context context;
    static LayoutInflater inflater = null;
    ArrayList<Ingrédient> listofingredient;
    ArrayList<Quantity> listofquantity;

    public IngredientCustomAdapter(Context context, ArrayList<Ingrédient> listofingredient, ArrayList<Quantity> listofquantity) {
        this.context = context;
        this.listofingredient = listofingredient;
        this.listofquantity = listofquantity;
    }

    @Override
    public int getCount() {
        return listofingredient.size();
    }

    @Override
    public Object getItem(int position) {
        return getItemId(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.add_row_ingredient, null,true);

        }

        TextView name = (TextView) row.findViewById(R.id.rowadd_nameingredient);
        TextView number = (TextView) row.findViewById(R.id.rowadd_quantityingredient);
        TextView unity = (TextView) row.findViewById(R.id.rowadd_whichquantityingredient);

        System.out.println(listofingredient.get(position).Name+" "+position);
        name.setText(listofingredient.get(position).Name);
        number.setText(String.valueOf(listofquantity.get(position).quantity));
        unity.setText(unitytoString(listofquantity.get(position).whichunity));

        return row;
    }

    public String unitytoString (whichunity a)
    {
        switch (a)
        {
            case ml:
                return "ml";
            case quarter:
                return "quarter";
            case splash:
                return "splash";
            case third:
                return "third";
            case stalk:
                return "stalk";
            case slice:
                return "slice";
            case piece:
                return "piece";
            case zest:
                return "zest";
            case skin:
                return "skin";
            case half:
                return "half";
            case dash:
                return "dash";
            case cup:
                return "cup";
            default:
                return "mL";
        }
    }
}

