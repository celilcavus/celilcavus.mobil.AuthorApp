package com.example.celilcavusauthorapp.Adapter.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celilcavusauthorapp.R;
import com.example.celilcavusauthorapp.databinding.RecylerBookrowBinding;

public class BooksHolder extends RecyclerView.ViewHolder {
    public RecylerBookrowBinding recylerBookrowBinding;

    public BooksHolder(RecylerBookrowBinding recylerBookrowBinding) {
        super(recylerBookrowBinding.getRoot());
        this.recylerBookrowBinding = recylerBookrowBinding;
    }
}
