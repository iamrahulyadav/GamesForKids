package com.nani.gamesForKids.Games.Face;

import android.graphics.Rect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliajastrzebska on 25/04/16.
 */
public class Face {
    List<Rect> earList = null;

    public Face(JSONObject faceJsonObject) throws JSONException {

        if (faceJsonObject.has("ear")) {
            populateEar(faceJsonObject.getJSONArray("ear"));
        }
    }

    private void populateEar(JSONArray earJsonArray) throws JSONException {
        this.earList = new ArrayList<>();

        for (int i = 0; i < earJsonArray.length(); i++) {
            this.earList.add(getRectForJsonObject(earJsonArray.getJSONObject(i)));
        }
    }

    private Rect getRectForJsonObject(JSONObject rectJsonObject) throws JSONException {
        return new Rect(
                rectJsonObject.getInt("left"),
                rectJsonObject.getInt("top"),
                rectJsonObject.getInt("right"),
                rectJsonObject.getInt("bottom"));
    }
}
