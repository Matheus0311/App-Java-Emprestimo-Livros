package br.matheus.p2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import br.matheus.p2.R;
import br.matheus.p2.fragmentos.CadastroEmprestimosFragment;
import br.matheus.p2.fragmentos.EmprestimosListaFragment;

public class QuartaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarta);
        setResult(80);
        EmprestimosListaFragment lista = new EmprestimosListaFragment(); //cria fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //cria transação

        transaction.add(R.id.idframe, lista); //add fragmento
        transaction.commit();//valida adição

    }
    public void primeiraTela(View view){
        getSupportFragmentManager().beginTransaction().//cria transacao
                replace(R.id.idframe,new EmprestimosListaFragment())//add fragmento
                .commit();//valida a adição
    }
    public void segundaTela(View view){
        getSupportFragmentManager().beginTransaction().//cria transacao
                replace(R.id.idframe,new CadastroEmprestimosFragment())//add fragmento
                .commit();//valida a adição
    }
}