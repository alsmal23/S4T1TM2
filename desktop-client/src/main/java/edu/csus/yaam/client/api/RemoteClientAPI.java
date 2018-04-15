package edu.csus.yaam.client.api;

import edu.csus.yaam.client.YaamClient;
import edu.csus.yaam.client.api.event.BindableProxy;
import lombok.experimental.Delegate;

/**
 * @author Ryan R
 * @date 4/10/2018
 */
public class RemoteClientAPI {
    @Delegate
    private final BindableProxy apiCallback = new BindableProxy<>(ClientAPICallback.class);

    private YaamClientApi clientApi;
    private YaamClient client;

    public RemoteClientAPI(YaamClient client) {
        this.client = client;
    }
}