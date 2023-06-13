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

import br.matheus.p2.adapter.EmprestimoAdapter;
import br.matheus.p2.entidades.Emprestimo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmprestimosListaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmprestimosListaFragment extends Fragment {

    //Declarando
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    EmprestimoAdapter emprestimoAdapter;

    ArrayList<Emprestimo> listagem;

    DatabaseReference databaseReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmprestimosListaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmprestimosListaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmprestimosListaFragment newInstance(String param1, String param2) {
        EmprestimosListaFragment fragment = new EmprestimosListaFragment();
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
        View view =  inflater.inflate(R.layout.fragment_emprestimos_lista, container, false);

        //setando o recyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.idLista);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //listando os dados
        databaseReference= FirebaseDatabase.getInstance().getReference("emprestimos");
        listagem = new ArrayList<>();
        emprestimoAdapter = new EmprestimoAdapter(view.getContext(), listagem);
        recyclerView.setAdapter(emprestimoAdapter);

        Query query = databaseReference.orderByChild("nome");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("Firebase",snapshot.getValue().toString());
                for (DataSnapshot item : snapshot.getChildren()) {
                    Emprestimo emprestimo = item.getValue(Emprestimo.class);
                    listagem.add(emprestimo);
                    emprestimoAdapter.notifyDataSetChanged();
                    Log.i("Firebase",emprestimo.nome);
                    Log.i("Firebase",emprestimo.livro);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }
}