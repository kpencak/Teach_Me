//package kpencak.teachme.dictionary;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.google.android.material.snackbar.Snackbar;
//
//import java.util.ArrayList;
//
//import kpencak.teachme.R;
//
///**
// * A fragment representing a list of Items.
// */
//public class DictionaryFragment extends Fragment {
//
////    private static final String ARG_COLUMN_COUNT = "column-count";
////
////    private int mColumnCount = 1;
////    private DictionaryItemViewModel dictionaryViewModel;
////    private DictionaryRecyclerViewAdapter recyclerViewAdapter;
//
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public DictionaryFragment() {
//    }
//
//    // TODO: Customize parameter initialization
//    @SuppressWarnings("unused")
//    public static DictionaryFragment newInstance(int columnCount) {
//        DictionaryFragment fragment = new DictionaryFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
////        View view = inflater.inflate(R.layout.dictionary_list, container, false);
////        // Set the adapter
////        if (view instanceof RecyclerView) {
////            Context context = view.getContext();
////            RecyclerView recyclerView = (RecyclerView) view;
////            if (mColumnCount <= 1) {
////                recyclerView.setLayoutManager(new LinearLayoutManager(context));
////            } else {
////                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
////            }
////            recyclerViewAdapter = new DictionaryRecyclerViewAdapter();
////            recyclerView.setAdapter(recyclerViewAdapter);
////            dictionaryViewModel = new ViewModelProvider(this).get(DictionaryItemViewModel.class);
////            dictionaryViewModel.getAllDictionary().observe(getViewLifecycleOwner(), dictionaryItems -> recyclerViewAdapter.setData(dictionaryItems));
////        }
////        return view;
//    }
//
//
//
////    private ArrayList<DictionaryItem> initDictionary() {
////        ArrayList<DictionaryItem> list = new ArrayList<>();
////
////        list.add(new DictionaryItem("potop szwedzki", "najazd Szwecji na Rzeczpospolitą w latach 1655-1660 będący jedną z odsłon II wojny północnej"));
////        list.add(new DictionaryItem("Mały Bełt", " cieśnina na Morzu Bałtyckim, położona między duńskimi wyspami Ærø i Fionią a Półwyspem Jutlandzkim"));
////        list.add(new DictionaryItem("Kanał morski", "akwen morski o wydłużonym kształcie łączący dwa większe akweny morskie."));
////        list.add(new DictionaryItem("La Manche", "z fr. dosł. „rękaw”; ang. English Channel, „Kanał Angielski”) – kanał morski oddzielający Wielką Brytanię od Francji."));
////        list.add(new DictionaryItem("Wielka Brytania", "Zjednoczone Królestwo (ang. United Kingdom), Zjednoczone Królestwo Wielkiej Brytanii i Irlandii Północnej (ang. United Kingdom of Great Britain and Northern Ireland"));
////        list.add(new DictionaryItem( "Unitaryzm", "polityczna zasada porządku i organizacji, która polega na dążeniu do zjednoczenia w jedną całość wielu autonomicznych tworów społecznych, np. połączenie landów (Niemcy), kantonów (Szwajcaria), grup etnicznych, narodów (Stany Zjednoczone, dawny Związek Socjalistycznych Republik Radzieckich, Indie, Jugosławia) oraz państw (Zjednoczone Królestwo Wielkiej Brytanii i Irlandii Północnej, Wspólnota Europejska). "));
////
////        return list;
////    }
//}