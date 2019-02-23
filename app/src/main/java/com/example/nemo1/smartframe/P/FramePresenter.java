package com.example.nemo1.smartframe.P;

import com.example.nemo1.smartframe.M.FrameModel;
import com.example.nemo1.smartframe.M.SendPresenter;

import java.util.List;

public class FramePresenter implements SendPresenter {
    private SendView sendView;
    private FrameModel frameModel;

    public FramePresenter(SendView sendView) {
        this.sendView = sendView;
        frameModel = new FrameModel(this);
        frameModel.GetLoginFirebase();
    }

    @Override
    public void onSendPresenter(List<String> images) {
        sendView.onSendView(images);
    }
}
