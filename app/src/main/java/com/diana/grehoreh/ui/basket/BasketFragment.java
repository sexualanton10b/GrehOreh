package com.diana.grehoreh.ui.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diana.grehoreh.R;
import com.diana.grehoreh.databinding.FragmentBasketBinding;
import com.diana.grehoreh.ui.Model.Purchase;
import com.diana.grehoreh.ui.Presenter.BasketAdapter;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment  {

    private FragmentBasketBinding binding;
    private BasketAdapter basketAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = root.findViewById(R.id.basket);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        basketAdapter = new BasketAdapter(requireContext());
        recyclerView.setAdapter(basketAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void updateData(){
        basketAdapter.updateData();
    }
}
