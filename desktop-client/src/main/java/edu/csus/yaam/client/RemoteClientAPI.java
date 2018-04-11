package edu.csus.yaam.client;

import edu.csus.yaam.client.api.ClientAPICallback;
import edu.csus.yaam.client.api.RemoteServer;
import edu.csus.yaam.client.api.event.BindableProxy;
import edu.csus.yaam.client.util.BlockChain;
import java.util.List;

/**
 * @author Ryan R
 * @date 4/10/2018
 */
public class RemoteClientAPI {
    private final BindableProxy apiCallback = new BindableProxy<>(ClientAPICallback.class);
    private final List<RemoteServer> servers = new BlockChain<>();

    public RemoteClientAPI(YaamClient client) {

    }
}