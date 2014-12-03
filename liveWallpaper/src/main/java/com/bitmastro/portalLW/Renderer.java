package com.bitmastro.portalLW;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.Camera2D;
import rajawali.animation.Animation3D;
import rajawali.animation.RotateAnimation3D;
import rajawali.materials.AMaterial;
import rajawali.materials.SimpleMaterial;
import rajawali.math.Number3D;
import rajawali.primitives.Plane;
import rajawali.renderer.RajawaliRenderer;


public class Renderer extends RajawaliRenderer {
    public static final float WIDTH = 0.512f;
    public static final float HEIGHT = 0.133f;
    private Animation3D mAnim;
    private Plane plane0;
    private Plane plane1;

    public Renderer(Context context) {
        super(context);
        setCamera(new Camera2D());
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
        float scaleX = 1;
        if (width < height) {
            scaleX = (float) height / (float) width;
            plane0.setScale(scaleX, 1, 0);
            plane1.setScale(scaleX, 1, 0);
        } else {
            plane0.setScale(1, (float) width / (float) height, 0);
            plane1.setScale(1, (float) width / (float) height, 0);
        }
        plane0.setPosition(scaleX * (HEIGHT - WIDTH) / 2f, 0, 0);
    }

    public void initScene() {
        plane0 = new Plane(HEIGHT, HEIGHT, 1, 1);
        SimpleMaterial material0 = new SimpleMaterial(AMaterial.ALPHA_MASKING);
        plane0.setMaterial(material0);
        Bitmap texture0 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo);
        plane0.addTexture(mTextureManager.addTexture(texture0));
        plane0.setTransparent(true);

        plane1 = new Plane(WIDTH, 0.256f, 1, 1);
        plane1.setPosition(0, 0, 0);
        SimpleMaterial material1 = new SimpleMaterial(AMaterial.ALPHA_MASKING);
        plane1.setMaterial(material1);
        Bitmap texture1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.aperture);
        plane1.addTexture(mTextureManager.addTexture(texture1));
        plane1.setTransparent(true);

        addChild(plane0);
        addChild(plane1);

        Number3D axis = new Number3D(0, 0, 1);
        axis.normalize();
        mAnim = new RotateAnimation3D(axis, 360);
        mAnim.setDuration(2500);
        mAnim.setRepeatCount(Animation3D.INFINITE);
        mAnim.setTransformable3D(plane0);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        mAnim.start();
    }
}
