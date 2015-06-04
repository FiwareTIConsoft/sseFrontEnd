/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend;

import com.tilab.ca.ssefrontend.models.ClassifyOutput;
import com.tilab.ca.ssefrontend.models.ClassifyRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author riccardo
 */
public class CoreInterfaceMock implements CoreInterface {

    List<ClassifyOutput> outputs = null;

    @Override

    public List<ClassifyOutput> classifyData(ClassifyRequest classifyRequest) {

        outputs = new ArrayList<>();
        ClassifyOutput classifyOutput1 = new ClassifyOutput();
        classifyOutput1.setUri("http://dbpedia.org/resource/Bus");
        classifyOutput1.setLabel("Autobus");
        classifyOutput1.setMergedTypes("");
        classifyOutput1.setScore("0.50076705");
        classifyOutput1.setTitle("Autobus");
        classifyOutput1.setWikilink("http://it.wikipedia.org/wiki/Autobus");
        classifyOutput1.setImage("");

        outputs.add(classifyOutput1);
        outputs.add(classifyOutput1);
        outputs.add(classifyOutput1);
        outputs.add(classifyOutput1);
        outputs.add(classifyOutput1);
        outputs.add(classifyOutput1);
        outputs.add(classifyOutput1);

        return outputs;
    }

}
