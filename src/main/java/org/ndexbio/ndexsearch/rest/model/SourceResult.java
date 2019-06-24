package org.ndexbio.ndexsearch.rest.model;

import java.util.List;
import org.ndexbio.enrichment.rest.model.DatabaseResult;

/**
 * Represents a source that can be queried such as
 * enrichment, keyword, and interactome. 
 * 
 * @author churas
 */
public class SourceResult {
    
    public static final String ENRICHMENT_SERVICE = "enrichment";
    public static final String KEYWORD_SERVICE = "keyword";
    public static final String INTERACTOME_SERVICE = "interactome";
    
    private String _uuid;
    private String _description;
    private String _name;
    private String _numberOfNetworks;
    private String _status;
    private String _endPoint;
    private String _version;
    private List<DatabaseResult> _databases;
    

    /**
     * Gets identifier of source
     * @return UUID as string
     */
    public String getUuid() {
        return _uuid;
    }

    /**
     * Sets identifier of source
     * @param _uuid UUID as string
     */
    public void setUuid(String _uuid) {
        this._uuid = _uuid;
    }

    /**
     * Gets description of source
     * @return description as string
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets description of source
     * @param _description description to set
     */
    public void setDescription(String _description) {
        this._description = _description;
    }

    /**
     * Gets name of source
     * @return name of source
     */
    public String getName() {
        return _name;
    }

    /**
     * Sets name of source
     * @param _name name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * Number of networks in source
     * @return number of networks as string
     */
    public String getNumberOfNetworks() {
        return _numberOfNetworks;
    }

    /**
     * Sets number of networks in source
     * @param _numberOfNetworks number of networks as string
     */
    public void setNumberOfNetworks(String _numberOfNetworks) {
        this._numberOfNetworks = _numberOfNetworks;
    }

    /**
     * Gets status of source
     * @return status as string
     */
    public String getStatus() {
        return _status;
    }

    /**
     * Sets status of source
     * @param _status status to set
     */
    public void setStatus(String _status) {
        this._status = _status;
    }

    /**
     * Gets REST endpoint URL
     * @return URL as string
     */
    public String getEndPoint() {
        return _endPoint;
    }

    /**
     * Sets REST endpoint URL
     * @param _endPoint URL as string
     */
    public void setEndPoint(String _endPoint) {
        this._endPoint = _endPoint;
    }

    /**
     * Gets version of source
     * @return 
     */
    public String getVersion() {
        return _version;
    }

    /**
     * Sets version of source
     * @param _version 
     */
    public void setVersion(String _version) {
        this._version = _version;
    }

    /**
     * Gets list of databases within source
     * @return 
     */
    public List<DatabaseResult> getDatabases() {
        return _databases;
    }

    /**
     * Sets list of database within source
     * @param _databases 
     */
    public void setDatabases(List<DatabaseResult> _databases) {
        this._databases = _databases;
    }
}
