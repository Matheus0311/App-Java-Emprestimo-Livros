package br.matheus.p2.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.matheus.p2.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void alunos(View view){
        Intent it = new Intent(this, SegundaActivity.class);
        resultLauncher.launch(it);
    }
    public void livros(View view){
        Intent it = new Intent(this, TerceiraActivity.class);
        resultLauncher.launch(it);
    }
    public void emprestimos(View view){
        Intent it = new Intent(this, QuartaActivity.class);
        resultLauncher.launch(it);
    }
    ActivityResultLauncher resultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>(){
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if(result.getResultCode()==80)
                                Toast.makeText(getApplicationContext(),
                                        "menu",Toast.LENGTH_SHORT).show();
                        }
                    });

    public void sair(View view){
        finish();
    }

}