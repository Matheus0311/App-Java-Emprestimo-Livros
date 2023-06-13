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
import br.matheus.p2.entidades.Emprestimo;

public class EmprestimoAdapter extends RecyclerView.Adapter<EmprestimoAdapter.EmprestimoHolder>{
    ArrayList<Emprestimo> dados;
    Context context;

    public EmprestimoAdapter(Context context, ArrayList<Emprestimo> dados) {
        this.dados = dados;
        this.context = context;
    }
    @NonNull
    @Override
    public EmprestimoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_emprestimos,parent,false);
        return new EmprestimoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmprestimoHolder holder, int position) {
        Emprestimo emprestimo = dados.get(position);
        holder.nome.setText(emprestimo.getNome());
        holder.livro.setText(emprestimo.getLivro());

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class EmprestimoHolder extends RecyclerView.ViewHolder {
        public TextView nome;
        public TextView livro;
        public EmprestimoHolder(@NonNull View itemView) {
            super(itemView);
            nome =itemView.findViewById(R.id.nomeid);
            livro =itemView.findViewById(R.id.livroid);
        }
    }

}
