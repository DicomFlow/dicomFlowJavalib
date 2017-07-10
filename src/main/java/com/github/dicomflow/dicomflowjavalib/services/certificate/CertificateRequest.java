package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Domain;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Mail;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Port;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Url;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Map;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class CertificateRequest extends Certificate {

    public CertificateRequest(
            @Element(name = "from") String from,
            @Element(name = "domain")Domain domain,
            @Element(name = "mail") Mail mail,
            @Element(name = "port")Port port) {
        super("REQUEST", from, domain, mail, port);
    }

    public CertificateRequest(@Attribute(name = "name") String name,
                      @Attribute(name = "action") String action,
                      @Element(name = "from") String from,
                      @Attribute(name = "version") String version,
                      @Element(name = "timeout") String timeout,
                      @Element(name = "timestamp") String timestamp,
                      @Element(name = "messageID")String messageID,
                      @Element(name = "domain")Domain domain,
                      @Element(name = "mail") Mail mail,
                      @Element(name = "port")Port port) {
        super(name, action, from, version, timeout, timestamp, messageID, domain, mail, port);
    }

    public CertificateRequest(Map<String, Object> params) {
        super(params);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        return map;
    }

}
