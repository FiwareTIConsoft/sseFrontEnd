/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.models.ClassifyOutput;
import java.util.List;

/**
 *
 * @author riccardo
 */
public interface CoreInterface {
	
	List<ClassifyOutput> classifyData(String text);
	
}
