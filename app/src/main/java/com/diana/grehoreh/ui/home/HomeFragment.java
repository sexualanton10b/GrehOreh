package com.diana.grehoreh.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diana.grehoreh.R;
import com.diana.grehoreh.ui.Presenter.ReadProducts;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Находим RecyclerView в макете и настраиваем его
        RecyclerView recyclerView = root.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Создаем адаптер с помощью метода CreateAdapter из класса ReadProducts
        // и устанавливаем его для RecyclerView
        recyclerView.setAdapter(ReadProducts.CreateAdapter(requireContext()));

        return root;
    }
}
