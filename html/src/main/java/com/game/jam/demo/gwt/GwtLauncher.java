package com.game.jam.demo.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.game.jam.demo.Demo;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
        @Override
        public GwtApplicationConfiguration getConfig () {
            return new GwtApplicationConfiguration(640, 640);
        }

        @Override
        public ApplicationListener createApplicationListener () {
            return new Demo();
        }
}
