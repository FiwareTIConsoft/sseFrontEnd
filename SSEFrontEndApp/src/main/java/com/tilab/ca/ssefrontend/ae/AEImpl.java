/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend.ae;

import com.tilab.ca.ssefrontend.config.SSEConfig;
import javax.ws.rs.client.ClientBuilder;
import org.aeonbits.owner.ConfigCache;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riccardo
 */
public class AEImpl implements AE {

    static Logger LOG = Logger.getLogger(AEImpl.class.getName());
    ClientConfig configuration = null;

    @Override
    public String extract(String url) {

        LOG.log(Level.INFO, "[extract] - BEGIN invoking SSE article extractor");
        configuration = new ClientConfig();
        configuration.property(ClientProperties.CONNECT_TIMEOUT, ConfigCache.getOrCreate(SSEConfig.class).aeTimeout());
        configuration.property(ClientProperties.READ_TIMEOUT, ConfigCache.getOrCreate(SSEConfig.class).aeTimeout());
        return ClientBuilder.newClient(configuration)
                .target(ConfigCache.getOrCreate(SSEConfig.class)
                .aeUrl())
                .path("extraction")
                .queryParam("url", url)
                .request().get(String.class);
    }

}
