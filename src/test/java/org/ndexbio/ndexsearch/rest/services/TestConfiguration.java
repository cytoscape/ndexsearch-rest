/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ndexbio.ndexsearch.rest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.ndexbio.ndexsearch.App;
import static org.junit.Assert.*;
import org.ndexbio.ndexsearch.rest.model.SourceResults;

/**
 *
 * @author churas
 */
public class TestConfiguration {
    
    
    @Rule
    public TemporaryFolder _folder= new TemporaryFolder();
    
    
    public String getAlternateConfigurationAsStrNoNDExInfo(final String dbDir,
            final String taskDir, final String logDir){
        StringBuilder sb = new StringBuilder();
        
        sb.append(Configuration.DATABASE_DIR);
        sb.append(" = ");
        sb.append(dbDir);
        sb.append("\n");        
        sb.append(Configuration.TASK_DIR);
        sb.append(" = ");
        sb.append(taskDir);
        sb.append("\n");
        sb.append(App.RUNSERVER_CONTEXTPATH);
        sb.append(" = /\n");
        sb.append(App.RUNSERVER_LOGDIR);
        sb.append(" = ");
        sb.append(logDir);
        sb.append("\n");
        sb.append(App.RUNSERVER_PORT + " = 8080\n");
        sb.append(App.RUNSERVER_LOGLEVEL + " = INFO\n");
        sb.append(Configuration.SOURCE_RESULTS_JSON_FILE+ " = " + Configuration.SOURCE_RESULTS_JSON_FILE + "\n");
        return sb.toString();
    }
    
    @Test
    public void testNonexistantAlternateConfiguration() throws Exception {
        File tempDir = _folder.newFolder();
        try {
            File confFile = new File(tempDir.getAbsolutePath() + File.separator + "my.conf");
            
            Configuration.setAlternateConfigurationFile(confFile.getAbsolutePath());
            Configuration.reloadConfiguration();
            Configuration config = Configuration.getInstance();
            assertEquals("/tmp", config.getSearchDatabaseDirectory());
            assertNull(config.getSearchTaskDirectory());
            assertEquals("/tmp/" + Configuration.SOURCE_RESULTS_JSON_FILE, config.getSourceResultsFile().getAbsolutePath());
            assertNull(config.getSourceResults());
            assertNull(config.getSearchEngine());
            assertNull(config.getNDExClient());
        } finally {
            _folder.delete();
        }
        
    }

    @Test
    public void testValidAlternateConfigurationNoNDexConfig() throws Exception {
        File tempDir = _folder.newFolder();
        try {
            File confFile = new File(tempDir.getAbsolutePath() + File.separator + "my.conf");
            FileWriter fw = new FileWriter(confFile);
            String dbDir = tempDir.getAbsolutePath() + File.separator + "db";
            String taskDir = tempDir.getAbsolutePath() + File.separator + "tasks";
            String logDir = tempDir.getAbsolutePath() + File.separator + "logs";
    
            fw.write(this.getAlternateConfigurationAsStrNoNDExInfo(dbDir, taskDir, logDir));
            fw.flush();
            fw.close();
            File databaseDir = new File(dbDir);
            assertTrue(databaseDir.mkdirs());
            File srcResFile = new File(dbDir + File.separator + Configuration.SOURCE_RESULTS_JSON_FILE);
            SourceResults sr = new SourceResults();
            
            ObjectMapper mappy = new ObjectMapper();
            FileWriter out = new FileWriter(srcResFile);
            mappy.writeValue(out, sr);
            out.close();
            Configuration.setAlternateConfigurationFile(confFile.getAbsolutePath());
            Configuration.reloadConfiguration();
            Configuration config = Configuration.getInstance();
            assertEquals(dbDir, config.getSearchDatabaseDirectory());
            assertEquals(taskDir, config.getSearchTaskDirectory());
            assertEquals(srcResFile.getAbsolutePath(), config.getSourceResultsFile().getAbsolutePath());
            assertNotNull(config.getSourceResults());
            assertNull(config.getSearchEngine());
            assertNull(config.getNDExClient());
        } finally {
            _folder.delete();
        }
        
    }
}
