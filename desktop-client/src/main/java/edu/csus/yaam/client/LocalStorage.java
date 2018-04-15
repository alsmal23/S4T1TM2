package edu.csus.yaam.client;

import java.io.File;
import lombok.experimental.Delegate;
import org.json.JSONObject;

/**
 * @author Ryan R
 * @date 4/10/2018
 */
public class LocalStorage {
    private final File settings;

    @Delegate
    private JSONObject object;

    public LocalStorage() {
        settings = null;
    }

    public void load() {

    }
}