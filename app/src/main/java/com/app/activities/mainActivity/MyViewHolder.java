package com.app.activities.mainActivity;

import android.content.Context;
import android.support.annotation.NonNull;

import com.app.databinding.ItemLayoutBinding;
import com.app.models.User;
import com.kalpesh.krecyclerviewadapter.KRecyclerViewHolder;

public class MyViewHolder extends KRecyclerViewHolder {

    private ItemLayoutBinding binding;


    MyViewHolder(@NonNull ItemLayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    @Override
    protected void setSelected(@NonNull Context context, boolean selected) {
        super.setSelected(context, selected);
        // This method is called whenever the holder is selected/unselected.
        if (selected) {
            // Selected
        } else {
            // Unselected
        }
    }

    @Override
    protected void setData(@NonNull Context context, @NonNull Object itemObject) {
        super.setData(context, itemObject);

        if (itemObject instanceof User) {
            binding.setUser((User) itemObject);
        }
    }
}
