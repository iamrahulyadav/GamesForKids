package com.nani.gamesForKids.Games.Face.Basic;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nani.gamesForKids.Core.MVPView;
import com.nani.gamesForKids.Core.Presenter;
import com.nani.gamesForKids.Games.Face.Face;
import com.nani.gamesForKids.Helper.PositionTranslator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;


public class FacePresenter implements Presenter<FacePresenter.FaceView>, TextToSpeech.OnInitListener {

    private FaceView view;
    private Face face;

    private TextToSpeech textToSpeech;

    @Override
    public void attachView(FaceView view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.textToSpeech = new TextToSpeech(this.view.getContext(), this);
    }

    @Override
    public void stop() {

    }

    public void addImageLoadedListenerToImageView(ImageView faceImageView) {
        faceImageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (faceImageView.getDrawable() != null){
                    loadFaceData(new PositionTranslator(faceImageView));
                }
            }
        });
    }

    private void loadFaceData(PositionTranslator positionTranslator) {

        try {
            JSONObject facesJsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray facesJsonArray = facesJsonObject.getJSONArray("faces");
            this.face = new Face(facesJsonArray.getJSONObject(0), positionTranslator);
            this.view.addTouchListenerToFaceImageView(this.face);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream is = this.view.getContext().getAssets().open("native/faces.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

            return null;
        }

        return json;
    }

    public int getResourceId(String variableName, String resourceName, String packageName) {

        try {
            return this.view.getContext().getResources().getIdentifier(variableName, resourceName, packageName);
        } catch (Exception e) {
            e.printStackTrace();

            return -1;
        }
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            Locale locale = new Locale(Locale.getDefault().getISO3Language(), Locale.getDefault().getISO3Country());
            this.textToSpeech.setLanguage(locale);

            this.textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onStart(String utteranceId) {

                }

                @Override
                public void onDone(String utteranceId) {
                    Log.d("Natalia"," loaded");
                }

                @Override
                public void onError(String utteranceId) {

                }
            });
        }
    }

    public void clickedOnFacePart(String facePartName) {
        String facePart = this.view.getContext().getString(getResourceId(facePartName,"string",this.view.getContext().getPackageName()));
        textToSpeech.speak(facePart, TextToSpeech.QUEUE_FLUSH, null);
    }

    public interface FaceView extends MVPView {
        void addTouchListenerToFaceImageView(Face face);
    }
}
