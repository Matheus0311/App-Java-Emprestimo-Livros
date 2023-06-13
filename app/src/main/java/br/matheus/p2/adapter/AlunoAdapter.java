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
import br.matheus.p2.entidades.Alunos;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunosHolder>{
    ArrayList<Alunos> dados;
    Context context;

    public AlunoAdapter(Context context, ArrayList<Alunos> dados) {
        this.dados = dados;
        this.context = context;
    }
    @NonNull
    @Override
    public AlunosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_alunos,parent,false);
        return new AlunosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunosHolder holder, int position) {
        Alunos alunos = dados.get(position);
        holder.nome.setText(alunos.getNome());
        holder.curso.setText(alunos.getCurso());

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class AlunosHolder extends RecyclerView.ViewHolder {
        public TextView nome;
        public TextView curso;
        public AlunosHolder(@NonNull View itemView) {
            super(itemView);
            nome =itemView.findViewById(R.id.nomeid);
            curso =itemView.findViewById(R.id.cursoid);
        }
    }

}
