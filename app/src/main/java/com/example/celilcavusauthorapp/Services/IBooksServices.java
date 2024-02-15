package com.example.celilcavusauthorapp.Services;

import com.example.celilcavusauthorapp.Entity.Books;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IBooksServices {
    @GET("/api/book")
    Call<List<Books>> GetAll();

    @GET("/api/book/{id}")
    Call<Books> GetById(@Path("id") int id);

}
