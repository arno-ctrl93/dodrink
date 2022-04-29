package com.example.app2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Show_IngredientCustomAdapter extends BaseAdapter {
    Context context;
    static LayoutInflater inflater = null;

    ArrayList<Ingrédient> listofingredient;
    ArrayList<Quantity> listofquantity;

    public Show_IngredientCustomAdapter(Context context, ArrayList<Ingrédient> listofingredient, ArrayList<Quantity> listofquantity) {
        this.context = context;
        this.listofingredient = listofingredient;
        this.listofquantity = listofquantity;
    }

    @Override
    public int getCount()
    {
        return listofingredient.size();
    }

    @Override
    public Object getItem(int position)
    {
        return getItemId(position);
    }

    @Override
    public long getItemId (int position)
    {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if (row == null)
        {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_showingredient,null);
        }

        TextView name = (TextView) row.findViewById(R.id.row_nameofingredient);
        TextView quantity = (TextView) row.findViewById(R.id.row_quantityofingredient);

        name.setText(listofingredient.get(position).Name);
        quantity.setText(settextquantity(listofquantity.get(position)));
        return row;
    }

    private String settextquantity (Quantity a)
    {
        String number  = String.valueOf(a.quantity);
        String unity = "No Change";
        switch (a.whichunity)
        {
            case ml:
                unity = "ml";
                break;
            case cup:
                unity = "cup";
                break;
            case dash:
                unity = "dash";
                break;
            case half:
                unity = "half";
                break;
            case skin:
                unity = "skin";
                break;
            case zest:
                unity = "zest";
                break;
            case piece:
                unity = "piece";
                break;
            case slice:
                unity = "slice";
                break;
            case stalk:
                unity = "stalk";
                break;
            case third:
                unity = "third";
                break;
            case splash:
                unity = "splash";
                break;
            case quarter:
                unity = "quarter";
                break;
        }
        return number+" "+unity;
    }
}


