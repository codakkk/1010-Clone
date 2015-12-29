package com.valery.diecidieci.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.valery.diecidieci.Configs;
import com.valery.diecidieci.TenTen;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Configs.GAME_WIDTH, Configs.GAME_HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new TenTen();
        }
}