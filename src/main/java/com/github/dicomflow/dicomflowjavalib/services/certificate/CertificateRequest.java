package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.List;
import java.util.Map;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class CertificateRequest extends Certificate {

    public CertificateRequest(
            @Element(name = "from") String from,
            @Element(name = "domain")String domain,
            @Element(name = "mail") String mail,
            @Element(name = "port")String port) {
        super("REQUEST", from, ServiceIF.CERTIFICATE_REQUEST, domain, mail, port);
    }

    public CertificateRequest(@Attribute(name = "name") String name,
                              @Attribute(name = "action") String action,
                      @Element(name = "from") String from,
                              @Attribute(name = "type") int type,
                      @Attribute(name = "version") String version,
                      @Element(name = "timeout") String timeout,
                      @Element(name = "timestamp") String timestamp,
                      @Element(name = "messageID")String messageID,
                      @Element(name = "domain")String domain,
                      @Element(name = "mail") String mail,
                      @Element(name = "port")String port) {
        super(name, action, from, type, version, timeout, timestamp, messageID, domain, mail, port);
    }

    public CertificateRequest(Map<String, Object> params) {
        super(params);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        params.put("action", "REQUEST");
        params.put("type", ServiceIF.CERTIFICATE_REQUEST);

        super.verifyParams(params);
    }
}
