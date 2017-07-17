package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.services.Service;

import org.simpleframework.xml.Element;

import java.util.Map;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public abstract class Certificate extends Service {

    @Element(required = false)
    public final String domain;
    @Element
    public final String mail;
    @Element(required = false)
    public final String port;

    public Certificate(String action, String from, int type, String domain, String mail, String port) {
        super("CERTIFICATE", action, from, type);
        this.domain = domain;
        this.mail = mail;
        this.port = port;
    }

    public Certificate(String name, String action, String from, int type, String version, String timeout, String timestamp, String messageID,
                       String domain, String mail, String port) {
        super(name, action, from, type, version, timeout, timestamp, messageID);
        this.domain = domain;
        this.mail = mail;
        this.port = port;
    }
    public Certificate(Map<String, Object> params) {
        super(params);

        this.domain = (String) params.get("domain");
        this.mail = (String) params.get("mail");
        this.port = (String) params.get("port");
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("domain", domain);
        map.put("from", mail);
        map.put("port", port);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        params.put("name", "CERTIFICATE");

        super.verifyParams(params);

        if (!params.containsKey("mail"))
            throw new DicomFlowObjectsParamMissingException("Param mail is missing for Certificate.");

        if ( params.containsKey("domain") && params.get("domain") == null)
            throw new ValueForParamShouldNotBeNullException("Param domain should not be null.");
        if ( params.containsKey("mail") && params.get("mail") == null)
            throw new ValueForParamShouldNotBeNullException("Param mail should not be null.");
        if ( params.containsKey("port") && params.get("port") == null)
            throw new ValueForParamShouldNotBeNullException("Param port should not be null.");
    }
}
