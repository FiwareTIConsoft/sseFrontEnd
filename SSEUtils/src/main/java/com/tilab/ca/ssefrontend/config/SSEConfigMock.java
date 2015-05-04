/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tilab.ca.ssefrontend.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.HotReloadType;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Reloadable;

/**
 *
 * @author andrea
 */
// Will use ASYNC reload type and will check every 15 seconds.
@HotReload(value = 15, type = HotReloadType.ASYNC)
@LoadPolicy(LoadType.MERGE)
@Sources({"classpath:com/tilab/ca/ssefrontend/config/test/server.properties"})

public interface SSEConfigMock extends Config, Reloadable {

    @Key("rest.service.url")
    @DefaultValue("http://127.0.0.1:8888/sse/")
    String serviceUrl(); //This web service local URL.

    @Key("cache.TTL")
    @DefaultValue("900")
    Integer cacheTTL(); //cache TTL (sec)
    
    @Key("core.url")
    @DefaultValue("http://127.0.0.1:8888/core/")
    String coreUrl(); //Timeout calling core (sec)
    
    @Key("core.timeout")
    @DefaultValue("30")
    Integer coreTimeout();
    
    @Key("ae.url")
    @DefaultValue("http://127.0.0.1:8888/ae/")
    String aeUrl(); //Article Extractor URL (do we need to split URI and path?)
    
    @Key("ae.timeout")
    @DefaultValue("30")
    Integer aeTimeout(); //Timeout calling ArticleExtractor
}

//FIXME TODO Urgent! Move to Core project!
// ---
// IT config
//String corpusIndexIt();
//String kbIt();
//String residualKbIt();
//String stopWordsIt();
// EN config
//String corpusIndexEn();
//String kbEn();
//String residualKbEn();
//String stopWordsEn();
// ---

