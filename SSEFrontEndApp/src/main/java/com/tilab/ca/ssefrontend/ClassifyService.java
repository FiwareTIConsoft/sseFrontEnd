/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.models.ClassifyOutput;
import com.tilab.ca.ssefrontend.ae.AE;
import com.tilab.ca.ssefrontend.enhancer.Enhancer;
import com.tilab.ca.ssefrontend.lang.LangDetectUtils;
import com.tilab.ca.ssefrontend.models.ClassifyRequest;
import com.tilab.ca.ssefrontend.textprocessing.TextProcessor;
import static com.tilab.ca.ssefrontend.util.SSEUtils.unchecked;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riccardo
 */
public class ClassifyService {

    static Logger LOG = Logger.getLogger(ClassifyService.class.getName());

    @Inject
    public AE articleExtractor;

    @Inject
    public TextProcessor textProcessor;

    @Inject
    public CoreInterface coreInterface;

    @Inject
    public Enhancer enhancer;

    public List<ClassifyOutput> classify(
            Optional<String> url,
            Optional<String> inputText,
            Optional<Integer> numTopics,
            Optional<String> imagePolicy) {

        return unchecked(() -> {

            // Retrieve the text from URL or directly from param
            String text = url.map(articleExtractor::extract).orElseGet(
                    () -> inputText.orElseThrow((() -> new IllegalArgumentException("No input text retrieved"))));
            LOG.log(Level.INFO, "text = {0}", text);

            // detect lang (it or en)
            String lang = LangDetectUtils.detect(text);

            // Pre process text
            String processedText = textProcessor.process(text);
            LOG.log(Level.INFO, "processedText = {0}", processedText);

            int nTopics = numTopics.orElse(7);
            LOG.log(Level.INFO, "nTopics = {0}", nTopics);

            // Core integration
            ClassifyRequest cr = new ClassifyRequest();
            cr.setLang(lang);
            cr.setNumTopics(nTopics);
            cr.setText(processedText);
            List<ClassifyOutput> classifyOutput = coreInterface.classifyData(cr);
            
            return classifyOutput;

            // Enhance if needed
            //System.out.println("Enhance if needed");
            //return imagePolicy.map(policy -> enhancer.enhance(classifyOutput, policy))
            //        .orElse(classifyOutput);
        });
    }
}
