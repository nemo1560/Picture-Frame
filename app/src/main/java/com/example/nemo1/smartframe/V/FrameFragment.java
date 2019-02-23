package com.example.nemo1.smartframe.V;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nemo1.smartframe.P.FramePresenter;
import com.example.nemo1.smartframe.P.SendView;
import com.example.nemo1.smartframe.R;

import java.util.List;

public class FrameFragment extends Fragment implements SendView {
    private FramePresenter framePresenter;
    private ViewPager viewPager;
    private ImagesAdapter imagesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame,container,false);
        viewPager = view.findViewById(R.id.imageFrame);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        framePresenter = new FramePresenter(this);
    }

    @Override
    public void onSendView(final List<String> images) {
        imagesAdapter = new ImagesAdapter(getActivity(),images);
        viewPager.setAdapter(imagesAdapter);
    }
}
