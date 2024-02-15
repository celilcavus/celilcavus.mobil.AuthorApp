package com.example.celilcavusauthorapp.Activityies.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.celilcavusauthorapp.R;
import com.example.celilcavusauthorapp.databinding.ActivityMainDetailBinding;

public class MainActivity_Detail extends AppCompatActivity {
    private ActivityMainDetailBinding activityMainDetailBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainDetailBinding = ActivityMainDetailBinding.inflate(getLayoutInflater());
        setContentView(activityMainDetailBinding.getRoot());


      try {
          Intent intent = getIntent();

          int id = intent.getIntExtra("Id",0);
          String  isim = intent.getStringExtra("Name");
          String  point = intent.getStringExtra("Point");
          String  page = intent.getStringExtra("Page");
          int  author = intent.getIntExtra("Author",0);
          int  type = intent.getIntExtra("Type",0);

          activityMainDetailBinding.txtKitapAdi.setText(isim);
          activityMainDetailBinding.txtKitapPuani.setText(point);
          activityMainDetailBinding.txtKitapSayfa.setText(page);
          activityMainDetailBinding.txtKitapYazar.setText(String.valueOf(author));
          activityMainDetailBinding.txtKitapTuru.setText(String.valueOf(type));
      }
      catch (Exception ex)
      {
          System.out.println("===== Main Activity Detail Error =====");
          System.out.println(ex.getMessage());
      }

    }
}