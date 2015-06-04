/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend.textprocessing;

/**
 *
 * @author riccardo
 */
public class TextProcessorBase implements TextProcessor{

	@Override
	public String process(String process) {
		return process.replaceAll("[\\+\\-\\|!\\(\\)\\{\\}\\[\\]\\^~\\*\\?\"\\\\:&]", " ").trim();
	}
	
}
