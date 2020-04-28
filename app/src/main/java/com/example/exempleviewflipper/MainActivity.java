package com.example.exempleviewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    ArrayList<String> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        images.add("hebergement_1");
        images.add("hebergement_2");
        images.add("hebergement_3");

        //On réccupère notre ViewFlipper
        final ViewFlipper viewFlipper = findViewById(R.id.vwFlipper);

        //On associe des transitions entre chaque image
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(context,android.R.anim.fade_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(context,android.R.anim.fade_out));

        //On définit le view en mode auto
        viewFlipper.setAutoStart(true);
        //On définit l'interval de temps entre chaque image (millisecond)
        viewFlipper.setFlipInterval(3000);

        for(int i = 0;i < images.size(); i++)
        {
            //On instancie notre objet itemView
            ItemView itemView = new ItemView(this);
            //On récupère le nom de l'image dans notre array list
            String nomImage = images.get(i);
            //On récupère notre image dans les drayable à partir de son nom
            Drawable drawable = getDrawable(getDrawableByName(nomImage));

            //On génère notre image
            ImageView imageView = itemView.findViewById(R.id.imgView);
            imageView.setImageDrawable(drawable);
            imageView.setMaxHeight(200);
            imageView.setMaxWidth(200);
            viewFlipper.addView(itemView);

            //autre possibilité : imageView
            //ImageView imageView2 = new ImageView(context);
            //imageView2.setImageDrawable(drawable);
            //imageView2.setMaxHeight(200);
            //imageView2.setMaxWidth(200);
            //viewFlipper.addView(itemView2);
        }

        final Button btnStartStop = findViewById(R.id.btn);

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewFlipper.isFlipping())
                {
                    viewFlipper.stopFlipping();
                    btnStartStop.setText("Start");
                }
                else
                    {
                    viewFlipper.startFlipping();
                    btnStartStop.setText("Stop");
                }
            }
        });
    }

    //permet de récupérer n'importe quoi en ressource, IMPORTANT
    private int getDrawableByName(String nomImage)
    {
        String pkgName = this.getPackageName();

        // Return 0 if not found.
        int resID = this.getResources().getIdentifier(nomImage, "drawable", pkgName);

        //Log.i("MyLog", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;

    }
}
