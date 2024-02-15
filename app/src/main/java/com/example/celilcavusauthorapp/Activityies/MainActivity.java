package com.example.celilcavusauthorapp.Activityies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.celilcavusauthorapp.Adapter.BooksAdapter;
import com.example.celilcavusauthorapp.Entity.Books;
import com.example.celilcavusauthorapp.Services.IBooksServices;
import com.example.celilcavusauthorapp.databinding.ActivityMainBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding activityMainBinding;
private List<Books> arrayList;
private BooksAdapter booksAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        arrayList = new ArrayList<>();
        activityMainBinding.recylerview.setLayoutManager(new LinearLayoutManager(this));

        booksAdapter = new BooksAdapter(arrayList);
        activityMainBinding.recylerview.setAdapter(booksAdapter);


        GetData();

    }

    public void GetData()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.112:8080/").addConverterFactory(GsonConverterFactory.create())
            .build();

        IBooksServices services = retrofit.create(IBooksServices.class);
        Call<List<Books>> call =  services.GetAll();

        call.enqueue(new Callback<List<Books>>() {
            @Override
            public void onResponse(Call<List<Books>> call, Response<List<Books>> response) {
                try {
                    if (response.isSuccessful())
                    {
                        for(Books x : response.body())
                        {
                            Books book = new Books();
                            book.setId(x.getId());
                            book.setName(x.getName());
                            book.setPageCount(x.getPageCount());
                            book.setPoint(x.getPoint());
                            book.setAuthorId(x.getAuthorId());
                            book.setTypeId(x.getTypeId());
                            arrayList.add(book);
                        }
                        booksAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        System.out.println("Hata");
                    }
                }catch (Exception exception)
                {
                    System.out.println("==== Exception  ====");
                    System.out.println(exception.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Books>> call, Throwable t) {
                System.out.println("FAİLED Hata");
                System.out.println(t.getMessage());
            }
        });

    }
}