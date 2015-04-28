/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend.enhancer;

import com.tilab.ca.ssefrontend.models.ClassifyOutput;
import java.util.List;

/**
 *
 * @author riccardo
 */
public interface Enhancer {
	List<ClassifyOutput> enhance(List<ClassifyOutput> classified, String policy); //XXX: Code smell - string for options 
																							//can't refactor now since there is no universal
																							//policy for classify enhancement
	//XXX compilation problem for wildcard error using ? extends ClassifyOutput as a return value.
	//This should be fixed in future because the client should have the possibility to extend ClassifyOutput
}