package kpencak.teachme.dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import kpencak.teachme.R;

public class ShowDictionaryActivity extends AppCompatActivity {
    private static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 1;

    private DictionaryItemViewModel dictionaryViewModel;
    private DictionaryRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dictionary);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dictionaryList);
        recyclerViewAdapter = new DictionaryRecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dictionaryViewModel = new ViewModelProvider(this).get(DictionaryItemViewModel.class);
        dictionaryViewModel.getAllDictionary().observe(this, dictionaryItems -> recyclerViewAdapter.setData(dictionaryItems));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            try {
                Intent intent = new Intent(this, NewDictionaryItem.class);
                startActivityForResult(intent, NEW_ITEM_ACTIVITY_REQUEST_CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button deleteAllbtn = findViewById(R.id.btn_delete_all);
        deleteAllbtn.setOnClickListener(view -> {
            try {
                dictionaryViewModel.deleteAllDictionary();
                notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position = viewHolder.getAdapterPosition();
                    DictionaryItem item = recyclerViewAdapter.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), "Usunięto " + item.getName(), Toast.LENGTH_LONG).show();

                    dictionaryViewModel.deleteDictionaryItem(item);
                }
        });

        helper.attachToRecyclerView(recyclerView);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent newItemData) {
        super.onActivityResult(requestCode, resultCode, newItemData);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            DictionaryItem newItem = new DictionaryItem(newItemData.getStringExtra("NAME_REPLY"), newItemData.getStringExtra("DESC_REPLY"), 1);
            dictionaryViewModel.insertDictionaryItem(newItem);
        } else {
            Toast.makeText(getApplicationContext(), R.string.add_new_item_error, Toast.LENGTH_SHORT).show();
        }
    }
}