package com.cjz.viewpager_tablayout.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjz.viewpager_tablayout.R;

public class Demo1Fragment extends Fragment {

    public Demo1Fragment() {
        // Required empty public constructor
    }

    public static Demo1Fragment newInstance() {
        return new Demo1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_demo1, container, false);
    }
}