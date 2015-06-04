package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.di.SSEInjector;
import com.tilab.ca.ssefrontend.di.TestModule;
import com.tilab.ca.ssefrontend.lang.LangDetectUtils;
import com.tilab.ca.ssefrontend.models.ClassifyOutput;
import java.net.URI;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ITClassifyFrontendTest {

    private static HttpServer server;

    @BeforeClass
    public static void setUp() throws Exception {

        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("com.tilab.ca.ssefrontend.rest");

        // to enable JSON support
        rc.register(JacksonFeature.class);

        // to enable Multipart support
        rc.register(MultiPartFeature.class);

        SSEInjector.setModule(new TestModule());

        LangDetectUtils.initMock();

        server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://127.0.0.1:8897/ssefrontend/"), rc, false, null, false);

        try {
            server.start();
        } catch (Exception e) {
            System.out.println("could not start Grizzly server");
            throw new RuntimeException(e);
        }

    }

    /**
     * Test Classify with text (SSE frontend)
     */
    @Test
    public void testClassifyWithText() {

        FormDataMultiPart part = new FormDataMultiPart().field("numTopics", "7", MediaType.TEXT_PLAIN_TYPE).field("text", "In Campania, dopo un testa a testa che lo ha visto, comunque, sempre in testa anche se di misura si afferma il sindaco di Salerno Vincenzo De Luca primo (al 39,9% contro il 38% di Stefano Caldoro)", MediaType.TEXT_PLAIN_TYPE);

        ClientConfig configuration = new ClientConfig();
        configuration.property(ClientProperties.CONNECT_TIMEOUT, 30000);
        configuration.property(ClientProperties.READ_TIMEOUT, 30000);

        Response resp = ClientBuilder.newClient(configuration).register((MultiPartFeature.class))
                .target("http://127.0.0.1:8897/ssefrontend/")
                .path("classify").request()
                .post(Entity.entity(part, MediaType.MULTIPART_FORM_DATA_TYPE));

        System.out.println("status = " + resp.getStatus());
        System.out.println("status info = " + resp.getStatusInfo());

        List<ClassifyOutput> outputs = resp.readEntity(new GenericType<List<ClassifyOutput>>() {
        });

        assertFalse(outputs.isEmpty());
        assertTrue(outputs.size() == 7);

    }

    /**
     * Test Classify with URL (SSE frontend)
     */
    @Test
    public void testClassifyWithUrl() {

        FormDataMultiPart part = new FormDataMultiPart().field("numTopics", "7", MediaType.TEXT_PLAIN_TYPE).field("url",
                "http://www.ansa.it/sito/notizie/cronaca/2015/06/03/ap-incidente-bus-turisti-italiani-in-usa-3-morti-_85fa7ab1-9b34-4735-b81f-f2aa188baccc.html", MediaType.TEXT_PLAIN_TYPE);

        ClientConfig configuration = new ClientConfig();
        configuration.property(ClientProperties.CONNECT_TIMEOUT, 30000);
        configuration.property(ClientProperties.READ_TIMEOUT, 30000);

        Response resp = ClientBuilder.newClient(configuration).register((MultiPartFeature.class))
                .target("http://127.0.0.1:8897/ssefrontend/")
                .path("classify").request()
                .post(Entity.entity(part, MediaType.MULTIPART_FORM_DATA_TYPE));

        System.out.println("status = " + resp.getStatus());
        System.out.println("status info = " + resp.getStatusInfo());

        List<ClassifyOutput> outputs = resp.readEntity(new GenericType<List<ClassifyOutput>>() {
        });

        assertFalse(outputs.isEmpty());
        assertTrue(outputs.size() == 7);

    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stop();
    }

}
