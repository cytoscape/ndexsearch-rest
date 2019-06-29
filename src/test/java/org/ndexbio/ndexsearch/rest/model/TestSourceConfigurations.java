/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ndexbio.ndexsearch.rest.model;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 *
 * @author churas
 */
public class TestSourceConfigurations {
    
    @Test
    public void testGettersAndSetters(){
        SourceConfigurations sr = new SourceConfigurations();
        assertNull(sr.getSources());
       
       
        SourceConfiguration sRes = new SourceConfiguration();
        sRes.setName("hi");
        sr.setSources(Arrays.asList(sRes));
       
        assertEquals(1, sr.getSources().size());
        assertEquals("hi", sr.getSources().get(0).getName());
        
        
    }
}
