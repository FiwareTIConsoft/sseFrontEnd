/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend.models;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author aantonazzo
 */
@XmlRootElement
public class ClassifyRequest {

    private String text;
    private int numTopics;
    private String lang;

    public ClassifyRequest() {
       
    }
    
    
    /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get the value of numTopics
     *
     * @return the value of numTopics
     */
    public int getNumTopics() {
        return numTopics;
    }

    /**
     * Set the value of numTopics
     *
     * @param numTopics new value of numTopics
     */
    public void setNumTopics(int numTopics) {
        this.numTopics = numTopics;
    }

    /**
     * Get the value of lang
     *
     * @return the value of lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * Set the value of lang
     *
     * @param lang new value of lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

}
