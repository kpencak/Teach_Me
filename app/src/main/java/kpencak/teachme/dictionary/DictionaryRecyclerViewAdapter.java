package kpencak.teachme.dictionary;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kpencak.teachme.R;

import java.util.ArrayList;
import java.util.List;

public class DictionaryRecyclerViewAdapter extends RecyclerView.Adapter<DictionaryRecyclerViewAdapter.DictionaryViewHolder> {

    private List<DictionaryItem> dictionary;

    public DictionaryRecyclerViewAdapter() {
        this.dictionary = new ArrayList<>();
    }

    @Override
    public DictionaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dictionary_item, parent, false);
        return new DictionaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DictionaryViewHolder holder, final int position) {
        final DictionaryItem dictionaryItem = dictionary.get(position);

        holder.name.setText(dictionaryItem.getName());
        holder.description.setText(dictionaryItem.getDescription());
    }

    @Override
    public int getItemCount() {
        if (dictionary != null) {
            return dictionary.size();
        } else {
            return 0;
        }
    }

    public DictionaryItem getItemAtPosition (int position) {
        return dictionary.get(position);
    }

    public void setData(List<DictionaryItem> newData) {
        if (dictionary != null) {
            DictionaryDiffCallback dictionaryDiffCallback = new DictionaryDiffCallback(dictionary, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(dictionaryDiffCallback);

            dictionary.clear();
            dictionary.addAll(newData);
            notifyDataSetChanged();
            diffResult.dispatchUpdatesTo(this);
        } else {
            dictionary = newData;
            notifyDataSetChanged();
        }

    }

    public class DictionaryViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        public final TextView description;

        public DictionaryViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.dictionary_item_name);
            description = view.findViewById(R.id.dictionary_item_description);
        }
    }

    class DictionaryDiffCallback extends DiffUtil.Callback {
        private final List<DictionaryItem> oldDictionary, newDictionary;

        public DictionaryDiffCallback(List<DictionaryItem> oldDictionary, List<DictionaryItem> newDictionary) {
            this.oldDictionary = oldDictionary;
            this.newDictionary = newDictionary;
        }

        @Override
        public int getOldListSize() {
            return oldDictionary.size();
        }

        @Override
        public int getNewListSize() {
            return newDictionary.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldDictionary.get(oldItemPosition).getId().equals(newDictionary.get(newItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldDictionary.get(oldItemPosition).equals(newDictionary.get(newItemPosition));
        }
    }
}