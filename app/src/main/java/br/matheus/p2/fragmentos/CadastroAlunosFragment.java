package br.matheus.p2.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.matheus.p2.R;
import br.matheus.p2.entidades.Alunos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastroAlunosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroAlunosFragment extends Fragment {

    //Declarando
    EditText nome;
    EditText curso;
    Button btsalvar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CadastroAlunosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastroAlunosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroAlunosFragment newInstance(String param1, String param2) {
        CadastroAlunosFragment fragment = new CadastroAlunosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //verifica se o usuário colocou um item com as informações corretas
    public boolean verificar() {
        String nomeAluno = nome.getText().toString();
        String cursoAluno = curso.getText().toString();

        if ((nomeAluno.equals(null) || nomeAluno.equals(null) || cursoAluno.equals(null))
                || (cursoAluno.equals(""))) {
            Toast.makeText(getActivity(), "Preencha os dados", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void salvar(View view) {
        //databaseReference = FirebaseDatabase.getInstance().getReference("alunos");
        if(verificar()){
            String nomeAluno = nome.getText().toString();
            String cursoAluno = curso.getText().toString();

            Alunos a = new Alunos();
            a.setNome(nomeAluno);
            a.setCurso(cursoAluno);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("alunos");
            databaseReference.push().setValue(a);
            Toast.makeText(view.getContext(), "Salvo", Toast.LENGTH_SHORT).show();

            // Redirect to list fragment
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.idframe, new AlunosListaFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void limpar() {
        nome.setText("");
        curso.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_alunos, container, false);

        nome = view.findViewById(R.id.nomeID);
        curso = view.findViewById(R.id.cursoID);
        btsalvar = view.findViewById(R.id.btCadastrarAlunosID);


        btsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar(view);
                limpar();
            }
        });
        return view;
    }
}

