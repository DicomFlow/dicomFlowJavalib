package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Map;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class CertificateResult extends Certificate {

    public enum Status {
        SUCCESS, ERROR
    }

    @Element(required = false)
    public final String credential;
    @Element public final String status;

    public CertificateResult(
            @Element(name = "from") String from,
            @Element(name = "domain")String domain,
            @Element(name = "mail") String mail,
            @Element(name = "port") String port,
            @Element(name = "credential") String credential,
            @Element(name = "status") String status) {
        super("RESULT", from, ServiceIF.CERTIFICATE_RESULT, domain,mail,port);
        this.credential = credential;
        this.status = status;
    }

    public CertificateResult(@Attribute(name = "name") String name,
                            @Attribute(name = "action") String action,
                            @Element(name = "from") String from,
                            @Attribute(name = "type") int type,
                            @Attribute(name = "version") String version,
                            @Element(name = "timeout") String timeout,
                            @Element(name = "timestamp") String timestamp,
                            @Element(name = "messageID")String messageID,
                            @Element(name = "domain")String domain,
                            @Element(name = "mail") String mail,
                            @Element(name = "port")String port,
                            @Element(name = "credential") String credential,
                            @Element(name = "status") String status) {
        super(name, action, from, type, version, timeout, timestamp, messageID, domain, mail, port);
        this.credential = credential;
        this.status = status;
    }

    public CertificateResult(Map<String, Object> params) {
        super(params);

        this.credential = (String) params.get("credential");
        this.status = (String) params.get("status");
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("credential", credential);
        map.put("status",status);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        params.put("action", "RESULT");
        params.put("type", ServiceIF.CERTIFICATE_RESULT);

        super.verifyParams(params);

        if (!params.containsKey("status"))
            throw new DicomFlowObjectsParamMissingException("Param credential is missing to CertificateConfirm");

        if ( params.containsKey("credential") && params.get("credential") == null)
            throw new ValueForParamShouldNotBeNullException("Value credential should not be null.");
        if ( params.get("status") == null)
            throw new ValueForParamShouldNotBeNullException("Value status should not be null.");
        if ( !params.get("status").equals(Status.SUCCESS.name()) && !params.get("status").equals(Status.ERROR.name())  )
            throw new IllegalArgumentException("Value "+params.get("status")+" is not SUCCESS or ERROR. ");

    }

}
