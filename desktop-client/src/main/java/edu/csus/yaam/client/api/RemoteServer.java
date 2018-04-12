package edu.csus.yaam.client.api;

import org.asynchttpclient.uri.Uri;

/**
 * @author Ryan R
 * @date 4/9/2018
 */
public class RemoteServer {
    private final Uri uri;

    private final YaamClientApi clientApi;

    public RemoteServer(Uri uri, ClientAPICallback callback) {
        this.uri = uri;
        clientApi = new YaamClientApi(uri, callback);
    }

    // derived

    private String name;
}