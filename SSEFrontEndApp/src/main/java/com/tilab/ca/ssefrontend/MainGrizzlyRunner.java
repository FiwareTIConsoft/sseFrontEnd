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
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.event.ReloadEvent;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.GrizzlyFuture;
import org.glassfish.grizzly.http.server.accesslog.AccessLogBuilder;
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
            LOG.info("Reload intercepted at " + new Date());
        });

        LangDetectUtils.init();

        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(sseConfigFromCache.serviceUrl()), rc, false, null, false);
        enableAccessLog(httpServer);

        // register shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOG.info("shutdown hook invoked, stopping Grizzly server");
            shutdownGrizzlyWebServer(httpServer);
        }, "shutdownHook"));

        try {
            httpServer.start();
        } catch (Exception e) {
            LOG.error("could not start Grizzly server", e);
            throw new RuntimeException(e);
        }

        return httpServer;

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at configured serviceUrl 
        //return GrizzlyHttpServerFactory.createHttpServer(URI.create(sseConfigFromCache.serviceUrl()), rc);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        final HttpServer server = startServer();

        LOG.info("Jersey app started with WADL available at " + sseConfigFromCache.serviceUrl());
        Thread.currentThread().suspend(); //XXX verify the best option to suspend the current thread

        server.stop();
    }

    public static void enableAccessLog(HttpServer httpServer) {
        /*RotatingLogAppender appender = new RotatingLogAppender(System.out);
         AccessLogFormat format = ApacheLogFormat.COMBINED;
         int statusThreshold = AccessLogProbe.DEFAULT_STATUS_THRESHOLD;
         AccessLogProbe alp = new AccessLogProbe(appender, format, statusThreshold);
         ServerConfiguration sc = httpServer.getServerConfiguration();
         sc.getMonitoringConfig().getWebServerConfig().addProbes(alp);*/
        final AccessLogBuilder builder = new AccessLogBuilder("./sse_frontend_access.log");
        builder.instrument(httpServer.getServerConfiguration());

    }

    protected static void shutdownGrizzlyWebServer(HttpServer grizzlyWebServer) {
        if (grizzlyWebServer != null && grizzlyWebServer.isStarted()) {
            // Gracefully stops the transport accepting new connections and allows existing work to complete before 
            // finalizing the shutdown. This method will wait for the specified time for all interested parties to signal 
            // it is safe to terminate the transport.
            // If the timeout is exceeded, the transport will be terminated forcefully.
            GrizzlyFuture<HttpServer> future = grizzlyWebServer.shutdown(10000, TimeUnit.MILLISECONDS);
            //Returns true if this task completed
            while (!future.isDone()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignore) {
                    LOG.error("ignore error = ", ignore);
                }
            }
        }
    }

}
