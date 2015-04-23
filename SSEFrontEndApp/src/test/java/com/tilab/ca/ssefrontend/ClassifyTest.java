package com.tilab.ca.ssefrontend;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ClassifyTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = SSEFrontendMainGrizzlyRunner.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to add
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(SSEFrontendMainGrizzlyRunner.BASE_URI.replace("0.0.0.0", "127.0.0.1"));
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    /**
     * Test to see the text extracted from the URL
     */
    @Test
    public void testGetUrl() {
        
    }
}
