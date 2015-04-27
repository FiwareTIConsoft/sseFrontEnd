package com.tilab.ca.ssefrontend.di;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.inject.AbstractModule;
import com.google.inject.Guice;

/**
 *
 * @author riccardo
 */
public class SSEDefaultBinding {

	private static AbstractModule module;

	public static void setModule(AbstractModule module) {
		SSEDefaultBinding.module = module;
	}
	
	public static <T> T instance(Class<T> clazz){
		return Guice.createInjector(module).getInstance(clazz);
	}
	
}
