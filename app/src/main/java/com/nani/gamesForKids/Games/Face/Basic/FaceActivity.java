package com.nani.gamesForKids.Games.Face.Basic;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.ImageView;

import com.nani.gamesForKids.Core.FullScreenGameActivity;
import com.nani.gamesForKids.Games.Face.Face;
import com.nani.gamesForKids.Games.Face.FaceTouchListener;
import com.nani.gamesForKids.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nataliajastrzebska on 25/04/16.
 */
public class FaceActivity extends FullScreenGameActivity implements FaceTouchListener.OnFacePartClickListener, FacePresenter.FaceView {

    @Bind(R.id.faceImageView)
    ImageView faceImageView;

    private FacePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        ButterKnife.bind(this);

        this.presenter = new FacePresenter();
        this.presenter.attachView(this);
        this.presenter.start();

        this.faceImageView.setImageResource(R.drawable.face_boy);
        this.presenter.addImageLoadedListenerToImageView(this.faceImageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void clickedOn(String facePartName) {
        this.presenter.clickedOnFacePart(facePartName);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addTouchListenerToFaceImageView(Face face) {
        this.faceImageView.setOnTouchListener(new FaceTouchListener(this, face));
    }
}
