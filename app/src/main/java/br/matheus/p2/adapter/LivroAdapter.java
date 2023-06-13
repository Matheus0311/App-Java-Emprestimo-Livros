package br.matheus.p2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import br.matheus.p2.R;
import br.matheus.p2.entidades.Livros;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivrosHolder>{
    ArrayList<Livros> dados;
    Context context;

    public LivroAdapter(Context context, ArrayList<Livros> dados) {
        this.dados = dados;
        this.context = context;
    }
    @NonNull
    @Override
    public LivrosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_livros,parent,false);
        return new LivrosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LivrosHolder holder, int position) {
        Livros livros = dados.get(position);
        holder.nome.setText(livros.getNome());
        holder.autor.setText(livros.getAutor());

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class LivrosHolder extends RecyclerView.ViewHolder {
        public TextView nome;
        public TextView autor;
        public LivrosHolder(@NonNull View itemView) {
            super(itemView);
            nome =itemView.findViewById(R.id.nomeid);
            autor =itemView.findViewById(R.id.autorid);
        }
    }

}
