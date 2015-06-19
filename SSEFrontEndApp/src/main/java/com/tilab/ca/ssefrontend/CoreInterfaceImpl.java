/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.config.SSEConfig;
import com.tilab.ca.ssefrontend.models.ClassifyOutput;
import com.tilab.ca.ssefrontend.models.ClassifyRequest;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.aeonbits.owner.ConfigCache;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import org.json.JSONObject;

/**
 *
 * @author
 */
public class CoreInterfaceImpl implements CoreInterface {

    static Logger LOG = Logger.getLogger(CoreInterfaceImpl.class.getName());
    ClientConfig configuration = null;
    JSONObject jsonInput = null;

    @Override
    public List<ClassifyOutput> classifyData(ClassifyRequest classifyRequest) {

        LOG.info( "[classifyData] - BEGIN invoking SSE core");
        configuration = new ClientConfig();
        configuration.property(ClientProperties.CONNECT_TIMEOUT, ConfigCache.getOrCreate(SSEConfig.class).coreTimeout());
        configuration.property(ClientProperties.READ_TIMEOUT, ConfigCache.getOrCreate(SSEConfig.class).coreTimeout());

        Entity entity = Entity.json(
                classifyRequest
        );
        
        Response resp = ClientBuilder.newClient(configuration)
                .target(ConfigCache.getOrCreate(SSEConfig.class)
                .coreUrl())
                .path("classify")
                .request(MediaType.APPLICATION_JSON)
                .post(entity, Response.class);

        List<ClassifyOutput> outputs = resp.readEntity(new GenericType<List<ClassifyOutput>>() {});
        LOG.info( "[classifyData] - EXIT invoking SSE core");
        return outputs;

    }

}
