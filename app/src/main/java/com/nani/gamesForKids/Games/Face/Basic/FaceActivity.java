package com.nani.gamesForKids.Games.Face.Basic;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nani.gamesForKids.Core.FullScreenGameActivity;
import com.nani.gamesForKids.Games.Face.Face;
import com.nani.gamesForKids.Games.Face.FaceTouchListener;
import com.nani.gamesForKids.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nataliajastrzebska on 25/04/16.
 */
public class FaceActivity extends FullScreenGameActivity implements FaceTouchListener.OnFacePartClickListener{

    @Bind(R.id.faceImageView)
    ImageView faceImageView;

    private Face face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        ButterKnife.bind(this);

        try {
            JSONObject facesJsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray facesJsonArray = facesJsonObject.getJSONArray("faces");
            this.face = new Face(facesJsonArray.getJSONObject(0));
            Glide.with(this).load(R.drawable.face_boy).fitCenter().into(this.faceImageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.faceImageView.setOnTouchListener(new FaceTouchListener(this, this.face));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public String loadJSONFromAsset() {
        String json = null;

        try {
            InputStream is = getAssets().open("native/faces.json");
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

    @Override
    public void clickedOn(String facePartName) {
        Log.d("Natalia", facePartName);
    }
}
