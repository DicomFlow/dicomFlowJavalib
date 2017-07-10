package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;
import com.github.dicomflow.dicomflowjavalib.services.Service;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Domain;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Mail;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Port;

import org.simpleframework.xml.Element;

import java.util.List;
import java.util.Map;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public abstract class Certificate extends Service {

    @Element public final Domain domain;
    @Element public final Mail mail;
    @Element public final Port port;

    public Certificate(String action, String from, Domain domain, Mail mail, Port port) {
        super("CERTIFICATE", action, from);
        this.domain = domain;
        this.mail = mail;
        this.port = port;
    }

    public Certificate(Map<String, Object> params) {
        super(params);

        this.domain = new Domain((Map<String, Object> ) params.get("domain"));
        this.mail = new Mail((Map<String, Object> ) params.get("mail"));
        this.port = new Port((Map<String, Object> ) params.get("port"));
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("domain", domain.toMap());
        map.put("from", mail.toMap());
        map.put("port", port.toMap());
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        super.verifyParams(params);
        if (!params.containsKey("domain"))
            throw new DicomFlowObjectsParamMissingException("Param domain is missing for Certificate.");
        if (!params.containsKey("mail"))
            throw new DicomFlowObjectsParamMissingException("Param mail is missing for Certificate.");
        if (!params.containsKey("port"))
            throw new DicomFlowObjectsParamMissingException("Param port is missing for Certificate.");

        if ( params.get("domain") == null)
            throw new ValueForParamShouldNotBeNullException("Param domain should not be null.");
        if ( params.get("mail") == null)
            throw new ValueForParamShouldNotBeNullException("Param mail should not be null.");
        if ( params.get("port") == null)
            throw new ValueForParamShouldNotBeNullException("Param port should not be null.");
    }
}
