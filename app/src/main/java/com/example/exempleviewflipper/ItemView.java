package com.example.exempleviewflipper;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class ItemView extends LinearLayout
{
    public ItemView(Context context) {
        super(context);

        //Permet de récupérer un objet vue depuis un layout : on transforme un layout en vue
        View view = inflate(context,R.layout.item_view, null);
        addView(view);
    }
}
