/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.ae.AE;
import com.tilab.ca.ssefrontend.textprocessing.TextProcessor;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

/**
 *
 * @author riccardo
 */
public class ClassifyService {
	
	@Inject
	public AE articleExtractor;

	@Inject
	public TextProcessor textProcessor;
	
	@Inject
	public CoreInterface coreInterface;
	
	public String classify(
			Optional<String> url, 
			Optional<String> inputText, 
			Optional<String> imagePolicy) {
		
		// Retrieve the text from URL or directly from param
		String text = url.map( articleExtractor::extract ).orElse(
				inputText.orElseThrow( ()-> new IllegalArgumentException("No input text retrieved") ));
		
		// Pre process text
		String processedText = textProcessor.process(text);
		
		// Core integration
		List<ClassifyOutput> classifyOutput = coreInterface.classifyData(text);
		
		// 
		return "";
	}
	
}
