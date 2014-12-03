package com.bitmastro.portalLW;

import android.content.Context;

import rajawali.wallpaper.Wallpaper;

public class Service extends Wallpaper {

    public Engine onCreateEngine() {
        Renderer mRenderer = new Renderer(this);
        return new WallpaperEngine(this.getSharedPreferences(SHARED_PREFS_NAME,
                Context.MODE_PRIVATE), getBaseContext(), mRenderer, false);
    }
}
