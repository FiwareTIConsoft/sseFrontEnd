/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend.config;

/**
 *
 * @author riccardo
 */
public interface SSEConfig {
	String aeUrl(); //Article Extractor URL (do we need to split URI and path?)
	Integer aeTimeout(); //Timeout calling ArticleExtractor
	
	String coreUrl(); //Core URL
	Integer coreTimeout(); //Timeout calling core

	// Enhancer properties?
	
	String serviceUrl(); //This web service local URL.

	Integer cacheTTL(); //Necessary?
	
	//FIXME TODO Urgent! Move to Core project!
	// ---
	// IT config
	String corpusIndexIt();
	String kbIt();
	String residualKbIt();
	String stopWordsIt();
	
	// EN config
	String corpusIndexEn();
	String kbEn();
	String residualKbEn();
	String stopWordsEn();
	// ---
	
}
