package com.nani.gamesForKids.Helper;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

public class PositionTranslator {

    private float imageWidth, imageHeight;
    private float viewWidth, viewHeight;
    private ImageView imageView;
    private Bitmap bitmap;

    private double widthFactor, heightFactor;

    public PositionTranslator(ImageView imageView) {
        this.imageView = imageView;
        setBitmap();
        setImageSize();
        setViewSize();
        setFactors();
    }

    private void setBitmap() {

        if (this.imageView.getDrawable() instanceof GlideBitmapDrawable) {
            this.bitmap = ((GlideBitmapDrawable) this.imageView.getDrawable()).getBitmap();
        } else {
            this.bitmap = ((BitmapDrawable) this.imageView.getDrawable()).getBitmap();
        }
    }

    private void setImageSize() {
        this.imageHeight = bitmap.getHeight();
        this.imageWidth = bitmap.getWidth();
    }

    private void setViewSize() {
        this.viewWidth = imageView.getWidth();
        this.viewHeight = imageView.getHeight();
    }

    private void setFactors() {
        widthFactor = (double) viewWidth / (double) imageWidth;
        heightFactor = (double) viewHeight / (double) imageHeight;
    }

    public double getImageXForViewX(double x) {
        float[] values = new float[9];
        imageView.getImageMatrix().getValues(values);
        float scalex = values[Matrix.MSCALE_X];
        float trans = values[Matrix.MTRANS_X];

        return getValidatedX((x - trans) / (scalex));
    }

    public double getImageYForViewY(double y) {
        float[] values = new float[9];
        imageView.getImageMatrix().getValues(values);
        float scaley = values[Matrix.MSCALE_Y];
        float trans = values[Matrix.MTRANS_Y];

        return getValidatedY((y - trans) / (scaley));
    }

    public double getViewXForImageX(double x) {
        float[] values = new float[9];
        imageView.getImageMatrix().getValues(values);
        float scalex = values[Matrix.MSCALE_X];
        float trans = values[Matrix.MTRANS_X];

        return x * scalex + trans;
    }

    public double getViewYForImageY(double y) {
        float[] values = new float[9];
        imageView.getImageMatrix().getValues(values);
        float scaley = values[Matrix.MSCALE_Y];
        float trans = values[Matrix.MTRANS_Y];

        return y * scaley + trans;
    }

    private double getValidatedX(double x) {

        if (x < 0) {

            return 0;
        }

        if (x > bitmap.getWidth()) {

            return bitmap.getWidth();
        }

        return x;
    }

    private double getValidatedY(double y) {

        if (y < 0) {

            return 0;
        }

        if (y > bitmap.getHeight()) {

            return bitmap.getHeight();
        }

        return y;
    }
}
