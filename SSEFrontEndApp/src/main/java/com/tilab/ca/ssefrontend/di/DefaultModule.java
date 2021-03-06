/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend.di;

import com.google.inject.AbstractModule;
import com.tilab.ca.ssefrontend.CoreInterface;
import com.tilab.ca.ssefrontend.CoreInterfaceImpl;
import com.tilab.ca.ssefrontend.ae.AE;
import com.tilab.ca.ssefrontend.ae.AEImpl;
import com.tilab.ca.ssefrontend.enhancer.Enhancer;
import com.tilab.ca.ssefrontend.enhancer.EnhancerImpl;
import com.tilab.ca.ssefrontend.textprocessing.TextProcessor;
import com.tilab.ca.ssefrontend.textprocessing.TextProcessorBase;

/**
 *
 * @author riccardo
 */
public class DefaultModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AE.class).to(AEImpl.class);
		bind(TextProcessor.class).to(TextProcessorBase.class);
		bind(CoreInterface.class).to(CoreInterfaceImpl.class);
		bind(Enhancer.class).to(EnhancerImpl.class);
	}
	
}
