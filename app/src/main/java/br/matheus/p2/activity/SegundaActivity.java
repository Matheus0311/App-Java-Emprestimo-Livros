package br.matheus.p2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import br.matheus.p2.R;
import br.matheus.p2.fragmentos.AlunosListaFragment;
import br.matheus.p2.fragmentos.CadastroAlunosFragment;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        setResult(80);
        AlunosListaFragment lista = new AlunosListaFragment(); //cria fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //cria transação

        transaction.add(R.id.idframe, lista); //add fragmento
        transaction.commit();//valida adição

    }
    public void primeiraTela(View view){
        getSupportFragmentManager().beginTransaction().//cria transacao
                replace(R.id.idframe,new AlunosListaFragment())//add fragmento
                .commit();//valida a adição
    }
    public void segundaTela(View view){
        getSupportFragmentManager().beginTransaction().//cria transacao
                replace(R.id.idframe,new CadastroAlunosFragment())//add fragmento
                .commit();//valida a adição
    }
}