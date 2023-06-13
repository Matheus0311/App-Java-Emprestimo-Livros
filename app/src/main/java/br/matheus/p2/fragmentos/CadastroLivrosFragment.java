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
import br.matheus.p2.entidades.Livros;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastroLivrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroLivrosFragment extends Fragment {

    //Declarando
    EditText nome;
    EditText autor;
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

    public CadastroLivrosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastroLivrosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroLivrosFragment newInstance(String param1, String param2) {
        CadastroLivrosFragment fragment = new CadastroLivrosFragment();
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
        String nomeLivro = nome.getText().toString();
        String nomeAutor = autor.getText().toString();

        if ((nomeLivro.equals(null) || nomeLivro.equals(null) || nomeAutor.equals(null))
                || (nomeAutor.equals(""))) {
            Toast.makeText(getActivity(), "Preencha os dados", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void salvar(View view) {
        //databaseReference = FirebaseDatabase.getInstance().getReference("alunos");
        if (verificar()) {
            String nomeLivro = nome.getText().toString();
            String nomeAutor = autor.getText().toString();

            Livros l = new Livros();
            l.setNome(nomeLivro);
            l.setAutor(nomeAutor);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference().child("livros");
            databaseReference.push().setValue(l);
            Toast.makeText(view.getContext(), "Salvo", Toast.LENGTH_SHORT).show();

            // Redirect to list fragment
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.idframe, new LivrosListaFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void limpar() {
        nome.setText("");
        autor.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_livros, container, false);

        nome = view.findViewById(R.id.nomeID);
        autor = view.findViewById(R.id.autorID);
        btsalvar = view.findViewById(R.id.btCadastrarLivrosID);


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