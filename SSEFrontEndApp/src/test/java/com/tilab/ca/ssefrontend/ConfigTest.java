/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.config.SSEConfigMock;
import java.io.File;
import java.util.Date;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import static java.util.Optional.ofNullable;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.event.ReloadEvent;
import org.aeonbits.owner.event.ReloadListener;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aantonazzo
 */
public class ConfigTest extends TestCase {

    private SSEConfigMock sseConfigFromCache = null;
    String newServiceUrl = "http://127.0.0.1:8811/sse/v2/";
    String newCoreUrl = "http://127.0.0.1:8812/sse/v2/core";
    String newAeUrl = "http://127.0.0.1:8813/sse/v2/ae";
    int coreTimeout = 60;
    int aeTimeout = 45;
    int cacheTTL = 900;

 

    //@Rule
    //public TemporaryFolder testFolder = new TemporaryFolder();

    public ConfigTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {

        sseConfigFromCache = ConfigCache.getOrCreate(SSEConfigMock.class);
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of testHotReloadProperties method, of class SSEConfiguration.
     *
     * @throws java.lang.RuntimeException
     */
    @Before
    public void testHotReloadProperties() throws RuntimeException, InterruptedException {

        System.out.println("Enter testHotReloadProperties");

        Properties prop = new Properties();
        OutputStream output = null;
        File tempFile = null;
        URL url = null;

        try {

             //tempFile = testFolder.newFile("server.properties");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            url = ofNullable(classLoader.getResource("com/tilab/ca/ssefrontend/config/test/server.properties")).orElseThrow(RuntimeException::new);
            
            tempFile = new File(url.toURI().getPath());
            output = new FileOutputStream(tempFile);
            // set the properties value
            prop.setProperty("rest.service.url", newServiceUrl);
            prop.setProperty("core.url", newCoreUrl);
            prop.setProperty("core.timeout", Integer.toString(coreTimeout));
            prop.setProperty("ae.url", newAeUrl);
            prop.setProperty("ae.timeout", Integer.toString(aeTimeout));
            prop.setProperty("cache.TTL", Integer.toString(cacheTTL));
            // save properties to project root folder
            prop.store(output, "server.properties");

        } catch (URISyntaxException | IOException ex) {
            System.out.println("Error when reading server.properties file ");
            throw new RuntimeException("Error occurred when reading server.properties file " + ex.getMessage());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("Error occurred when writing server.properties file");
                    throw new RuntimeException("Error occurred when writing server.properties file " + e.getMessage());
                }
            }
        }

        sseConfigFromCache.addReloadListener(new ReloadListener() {
            public void reloadPerformed(ReloadEvent event) {
                System.out.print("Reload intercepted at " + new Date());
                assertTrue(true);
            }
        });

        System.out.println("The program is running. ");
        String updatedServiceUrl = null;
        String updatedCoreUrl = null;
        String updatedAeUrl = null;
        Thread.sleep(16000);
        updatedServiceUrl = sseConfigFromCache.serviceUrl();
        System.out.println("updatedServiceUrl is: " + updatedServiceUrl);
        updatedCoreUrl = sseConfigFromCache.coreUrl();
        System.out.println("updatedCoreUrl is: " + updatedCoreUrl);
        updatedAeUrl = sseConfigFromCache.aeUrl();
        System.out.println("updatedAeUrl is: " + updatedAeUrl);

        assertEquals(updatedServiceUrl, newServiceUrl);
        assertEquals(updatedCoreUrl, newCoreUrl);
        assertEquals(updatedAeUrl, newAeUrl);

    }

    /**
     * Test of serviceUrl method, of class SSEConfiguration.
     */
    @Test
    public void testServiceUrl() {

        System.out.println("Enter testRestServiceUrl");

        // String expResult = "http://127.0.0.1:8811/sse/v2/";
        String result = sseConfigFromCache.serviceUrl();

        System.out.println("serviceUrl result=" + result);
        assertEquals(newServiceUrl, result);

    }

    /**
     * Test of cacheTTL method, of class SSEConfiguration.
     */
    @Test
    public void testCacheTTL() {

        System.out.println("Enter testCacheTTL");

        //int expResult = 900;
        int result = sseConfigFromCache.cacheTTL();

        System.out.println("cacheTTL result=" + result);
        assertEquals(cacheTTL, result);

    }

    /**
     * Test of coreUrl method, of class SSEConfiguration.
     */
    @Test
    public void testCoreUrl() {

        System.out.println("Enter testCoreUrl");

        //String expResult = "http://127.0.0.1:8812/sse/v2/core/";
        String result = sseConfigFromCache.coreUrl();

        System.out.println("coreUrl result=" + result);
        assertEquals(newCoreUrl, result);

    }

    /**
     * Test of coreTimeout method, of class SSEConfiguration.
     */
    @Test
    public void testCoreTimeout() {

        System.out.println("Enter testCoreTimeout");

        //int expResult = 30;
        int result = sseConfigFromCache.coreTimeout();

        System.out.println("coreTimeout result=" + result);
        assertEquals(coreTimeout, result);

    }

    /**
     * Test of aeUrl method, of class SSEConfiguration.
     */
    @Test
    public void testAeUrl() {

        System.out.println("Enter testAeUrl");

        //String expResult = "http://127.0.0.1:8813/sse/v2/ae/";
        String result = sseConfigFromCache.aeUrl();

        System.out.println("aeUrl result=" + result);
        assertEquals(newAeUrl, result);

    }

    /**
     * Test of aeTimeout method, of class SSEConfiguration.
     */
    @Test
    public void testAeTimeout() {

        System.out.println("Enter testAeTimeout");

        //int expResult = 45;
        int result = sseConfigFromCache.aeTimeout();

        System.out.println("aeeTimeout result=" + result);
        assertEquals(aeTimeout, result);

    }

}
