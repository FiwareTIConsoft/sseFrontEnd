package com.tilab.ca.ssefrontend.lang;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import static com.tilab.ca.ssefrontend.util.SSEUtils.unchecked;
import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

/**
 * author Andrea
 */
public class LangDetectUtils {

    static Logger LOG = Logger.getLogger(LangDetectUtils.class.getName());

    public static void init() {
        unchecked(() -> {
            URL path = LangDetectUtils.class.getClassLoader().getResource("profiles");
            DetectorFactory.loadProfile(new File(path.toURI()));
        });
    }

    public static String detect(String text) {
        return unchecked(() -> {
            
            Detector detector;
            detector = DetectorFactory.create();
            detector.append(text);
            return detector.detect();
            //LOG.log(Level.INFO, "Detected lang = {0}", lang);
            
        },"en");  
    }
}
