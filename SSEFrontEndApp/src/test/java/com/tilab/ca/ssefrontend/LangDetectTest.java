/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.lang.LangDetectUtils;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aantonazzo
 */
public class LangDetectTest extends TestCase {

    final static String enText = "The final work of legendary director Stanley Kubrick, who died within a week of completing the edit, is based upon a novel by Arthur Schnitzler. Tom Cruise and Nicole Kidman play William and Alice Harford, a physician and a gallery manager who are wealthy, successful, and travel in a sophisticated social circle.";
    final static String itText = "Sono 42 le vittime in Nepal e 17 in India. Scossa avvertita in Nepal, a New Delhi e in altre parti del nord dell'India. Il tremore è durato un minuto. Epicentro del sisma in Nepal. Dodici  persone estratte vive dalle macerie. Chiuso l'aeroporto di Kathmandu";
    
    public LangDetectTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {

          super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test lang detection initialization
     *
     */
    @Before
    public void testInitLangDetect() {
        LangDetectUtils.initMock();
    }

    /**
     * Test check italian language
     */
    @Test
    public void testCheckItalianLanguage() {
        System.out.println("Enter testCheckItalianLanguage");
        String result = LangDetectUtils.detect(itText);
        System.out.println("language detected result=" + result);
        assertEquals("it", result);

    }

    /**
     * Test check english language
     */
    @Test
    public void testCheckEnglishLanguage() {
        System.out.println("Enter testCheckEnglishLanguage");
        String result = LangDetectUtils.detect(enText);
        System.out.println("language detected result=" + result);
        assertEquals("en", result);

    }

}
