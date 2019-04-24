package com.app.activities.mainActivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.R;
import com.app.databinding.ItemLayoutBinding;
import com.app.models.User;
import com.app.utils.AlertUtils;
import com.kalpesh.krecyclerviewadapter.KRecyclerViewAdapter;
import com.kalpesh.krecyclerviewadapter.KRecyclerViewHolder;
import com.kalpesh.krecyclerviewadapter.KRecyclerViewHolderCallBack;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {


    public MainViewModel(@NonNull Application application) {
        super(application);
        recyclerViewAdapter = new KRecyclerViewAdapter(getApplication(), userArrayList, new KRecyclerViewHolderCallBack() {
            @Override
            public KRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
                LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                ItemLayoutBinding itemLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false);
                return new MyViewHolder(itemLayoutBinding);
            }

            @Override
            public void onHolderDisplayed(@NonNull KRecyclerViewHolder kRecyclerViewHolder, int i) {

            }
        }, (kRecyclerViewHolder, o, i) -> {
        });
    }

    public KRecyclerViewAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    public void setRecyclerViewAdapter(KRecyclerViewAdapter recyclerViewAdapter) {
        this.recyclerViewAdapter = recyclerViewAdapter;
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public void setUsername(MutableLiveData<String> username) {
        this.username = username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }

    public void onButtonClicked(View view) {
        if (username.getValue() != null && !username.getValue().isEmpty() && password.getValue() != null && !password.getValue().isEmpty()) {
            userArrayList.add(new User(username.getValue(), password.getValue()));
            recyclerViewAdapter.notifyDataSetChanged();
            username.setValue("");
            password.setValue("");
            AlertUtils.showToast("User Added", Toast.LENGTH_SHORT, view.getContext());
        } else {
            AlertUtils.showToast("Username or password cannot be empty", Toast.LENGTH_SHORT, view.getContext());
        }
    }

    private ArrayList<User> userArrayList = new ArrayList<>();
    private KRecyclerViewAdapter recyclerViewAdapter;
    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();
}
