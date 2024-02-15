package com.example.celilcavusauthorapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celilcavusauthorapp.Activityies.DetailActivity.MainActivity_Detail;
import com.example.celilcavusauthorapp.Adapter.Holders.BooksHolder;
import com.example.celilcavusauthorapp.Entity.Books;
import com.example.celilcavusauthorapp.Services.IBooksServices;
import com.example.celilcavusauthorapp.databinding.RecylerBookrowBinding;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksAdapter extends RecyclerView.Adapter<BooksHolder> {
    private List<Books> books;

    public BooksAdapter(List<Books> books) {
        this.books = books;
    }


    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecylerBookrowBinding recylerBookrowBinding  = RecylerBookrowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new BooksHolder(recylerBookrowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, @SuppressLint("RecyclerView") int position) {

    try{
        holder.recylerBookrowBinding.txtKitapAdi.setText(books.get(position).getName());
        holder.recylerBookrowBinding.txtKitapPuani.setText(books.get(position).getPoint());
        holder.recylerBookrowBinding.txtKitapSayfa.setText(String.valueOf(books.get(position).getPageCount()));
        holder.recylerBookrowBinding.txtKitapYazar.setText(String.valueOf(books.get(position).getAuthorId()));
        holder.recylerBookrowBinding.txtKitapTuru.setText(String.valueOf(books.get(position).getTypeId()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int id = books.get(position).getId();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.112:8080/").addConverterFactory(GsonConverterFactory.create()).build();
                IBooksServices services = retrofit.create(IBooksServices.class);
                Call<Books> books1 = services.GetById(id);
                books1.enqueue(new Callback<Books>() {
                    @Override
                    public void onResponse(Call<Books> call, Response<Books> response) {
                        if (response.isSuccessful())
                        {
                            Books books2 = response.body();

                            Intent intent = new Intent(holder.itemView.getContext(),MainActivity_Detail.class);
                            intent.putExtra("Id",books2.getId());
                            intent.putExtra("Name",books2.getName());
                            intent.putExtra("Point",books2.getPoint());
                            intent.putExtra("Page",books2.getPageCount());
                            intent.putExtra("Author",books2.getAuthorId());
                            intent.putExtra("Type",books2.getTypeId());
                            holder.itemView.getContext().startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Books> call, Throwable t) {
                        System.out.println("==== Books Adapter Bind View Holder Click Exception ====");
                        System.out.println(t.getMessage());
                    }
                });

            }
        });


    }catch (Exception exception)
    {
        System.out.println("==== Books Adapter Bind View Holder Exception ====");
        System.out.println(exception.getMessage());
    }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}

