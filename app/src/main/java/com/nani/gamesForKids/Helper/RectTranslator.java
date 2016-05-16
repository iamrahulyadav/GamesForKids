package com.nani.gamesForKids.Helper;

import android.graphics.Rect;
import android.widget.ImageView;

public class RectTranslator {

    public static Rect getViewRectForImageRect(ImageView imageView, Rect rect) {
        PositionTranslator positionTranslator = new PositionTranslator(imageView);

        return new Rect(
                (int) positionTranslator.getViewXForImageX(rect.left),
                (int) positionTranslator.getViewYForImageY(rect.top),
                (int) positionTranslator.getViewXForImageX(rect.right),
                (int) positionTranslator.getViewYForImageY(rect.bottom)
        );
    }

    public static Rect getViewRectForImageRect(PositionTranslator positionTranslator, Rect rect) {
        return new Rect(
                (int) positionTranslator.getViewXForImageX(rect.left),
                (int) positionTranslator.getViewYForImageY(rect.top),
                (int) positionTranslator.getViewXForImageX(rect.right),
                (int) positionTranslator.getViewYForImageY(rect.bottom)
        );
    }
}
