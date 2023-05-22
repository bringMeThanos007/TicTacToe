package com.example.ttt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Won extends AppCompatActivity {
    String name;
    int imgInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
         name = GetStart.getWinnerStr();
         imgInt=GetStart.getI();

         if(imgInt==1) {
             ImageView img = (ImageView) findViewById(R.id.img);
             img.setImageResource(R.drawable.cat);
         }
         else{
             ImageView img = (ImageView) findViewById(R.id.img);
             img.setImageResource(R.drawable.tan);
         }

        TextView text =(TextView) findViewById(R.id.text);
        text.setText(name);

    }

    public void playAgain(View view) {
        Intent i = new Intent(this,GetStart.class);
        startActivity(i);
    }
}