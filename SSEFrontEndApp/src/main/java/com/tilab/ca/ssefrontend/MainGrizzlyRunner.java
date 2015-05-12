package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.config.SSEConfig;
import com.tilab.ca.ssefrontend.di.DefaultModule;
import com.tilab.ca.ssefrontend.di.SSEInjector;
import com.tilab.ca.ssefrontend.lang.LangDetectUtils;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.event.ReloadEvent;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * Main class.
 *
 */
public class MainGrizzlyRunner {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:8079/sse/"; //XXX Hardcoded?
    static Logger LOG = Logger.getLogger(MainGrizzlyRunner.class.getName());
    static SSEConfig sseConfigFromCache;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("com.tilab.ca.ssefrontend.rest");

        // to enable JSON support
        rc.register(JacksonFeature.class);

        // to enable Multipart support
        rc.register(MultiPartFeature.class);

        SSEInjector.setModule(new DefaultModule());

        sseConfigFromCache = ConfigCache.getOrCreate(SSEConfig.class);
        sseConfigFromCache.addReloadListener((ReloadEvent event) -> {
            LOG.log(Level.INFO, "Reload intercepted at {0}", new Date());
        });

        LangDetectUtils.init();

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at configured serviceUrl 
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(sseConfigFromCache.serviceUrl()), rc);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\n", sseConfigFromCache.serviceUrl()));
        Thread.currentThread().suspend(); //XXX verify the best option to suspend the current thread

        server.stop();
    }
}
