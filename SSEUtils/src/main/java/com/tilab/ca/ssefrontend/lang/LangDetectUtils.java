package com.tilab.ca.ssefrontend.lang;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import static com.tilab.ca.ssefrontend.util.SSEUtils.unchecked;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

/**
 * author Andrea
 */
public class LangDetectUtils {

    static Logger LOG = Logger.getLogger(LangDetectUtils.class.getName());

    // Test without jar packaging, files in resource folder
    public static void initMock() {
        unchecked(() -> {
            URL path = LangDetectUtils.class.getClassLoader().getResource("profiles");
            DetectorFactory.loadProfile(new File(path.toURI()));
        });
    }

    // The LangDetect library expects a String to a directory path or an actual File for the directory. 
    // You just can not convert a jar URL/URI into a file. You would have to write the files to a directory on the file system
    // You can load the profiles from the LangDetect jar using JarUrlConnection and JarEntry.
    public static void init() {

        unchecked(() -> {
            String dirname = "profiles/";
            Enumeration<URL> en = Detector.class.getClassLoader().getResources(
                    dirname);
            List<String> profiles = new ArrayList<>();
            if (en.hasMoreElements()) {
                URL url = en.nextElement();
                JarURLConnection urlcon = (JarURLConnection) url.openConnection();
                try (JarFile jar = urlcon.getJarFile();) {
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        String entry = entries.nextElement().getName();
                        if (entry.startsWith(dirname)) {
                            try (InputStream in = Detector.class.getClassLoader()
                                    .getResourceAsStream(entry);) {

                                StringBuilder textBuilder = new StringBuilder();
                                try (Reader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(StandardCharsets.UTF_8.name())))) {
                                    int c = 0;
                                    while ((c = reader.read()) != -1) {
                                        textBuilder.append((char) c);
                                    }
                                }
                                profiles.add(textBuilder.toString());
                            }
                        }
                    }
                }
            }

            DetectorFactory.loadProfile(profiles);

        });
    }

    public static String detect(String text) {
        return unchecked(() -> {

            Detector detector;
            detector = DetectorFactory.create();
            detector.append(text);
            return detector.detect();
            //LOG.log(Level.INFO, "Detected lang = {0}", lang);

        }, "en");
    }

}
