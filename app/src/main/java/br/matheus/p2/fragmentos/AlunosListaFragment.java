package br.matheus.p2.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.matheus.p2.R;
import br.matheus.p2.adapter.AlunoAdapter;
import br.matheus.p2.entidades.Alunos;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlunosListaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlunosListaFragment extends Fragment {

    //Declarando
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AlunoAdapter alunoAdapter;

    ArrayList<Alunos> listagem;

    DatabaseReference databaseReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlunosListaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlunosListaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlunosListaFragment newInstance(String param1, String param2) {
        AlunosListaFragment fragment = new AlunosListaFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_alunos_lista, container, false);

        //setando o recyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.idLista);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //listando os dados
        databaseReference= FirebaseDatabase.getInstance().getReference("alunos");
        listagem = new ArrayList<>();
        alunoAdapter = new AlunoAdapter(view.getContext(), listagem);
        recyclerView.setAdapter(alunoAdapter);


        Query query = databaseReference.orderByChild("nome");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("Firebase",snapshot.getValue().toString());
                for (DataSnapshot item : snapshot.getChildren()) {
                    Alunos alunos = item.getValue(Alunos.class);
                    listagem.add(alunos);
                    alunoAdapter.notifyDataSetChanged();
                    Log.i("Firebase",alunos.nome);
                    Log.i("Firebase",alunos.curso);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }
}