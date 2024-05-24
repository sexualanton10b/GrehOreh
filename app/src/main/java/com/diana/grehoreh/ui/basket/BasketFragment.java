package com.diana.grehoreh.ui.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diana.grehoreh.R;
import com.diana.grehoreh.databinding.FragmentBasketBinding;
import com.diana.grehoreh.ui.Model.Purchase;
import com.diana.grehoreh.ui.Presenter.BasketAdapter;
import com.diana.grehoreh.ui.Presenter.BasketPresenter;

import java.util.List;

public class BasketFragment extends Fragment implements BasketPresenter.BasketView {

    private FragmentBasketBinding binding;
    private BasketAdapter basketAdapter;
    private BasketPresenter basketPresenter;
    private Button payButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        basketPresenter = new BasketPresenter(this, requireContext());

        RecyclerView recyclerView = root.findViewById(R.id.basket);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        basketAdapter = new BasketAdapter(requireContext());
        recyclerView.setAdapter(basketAdapter);
        // Load initial data
        basketPresenter.loadBasket();
        payButton = root.findViewById(R.id.pay_button);
        // Add pay button click handling if needed

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void displayBasket(List<Purchase> purchases) {
        basketAdapter.setData(purchases);
    }

    public void updateData(){
        basketPresenter.updateData();
    }
}
