package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Domain;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Mail;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Port;

import org.simpleframework.xml.Element;

import java.util.Map;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class CertificateConfirm extends Certificate {

    @Element public final String credential;
    // TODO: 10/07/17 definir os status possiveis
    @Element public final String status;

    public CertificateConfirm(String from, Domain domain, Mail mail, Port port, String credential, String status) {
        super("CONFIRM", from, domain,mail,port);
        this.credential = credential;
        this.status = status;
    }

    public CertificateConfirm(Map<String, Object> params) {
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
        super.verifyParams(params);

        if (!params.containsKey("credential"))
            throw new DicomFlowObjectsParamMissingException("Param credential is missing to CertificateConfirm");
        if (!params.containsKey("status"))
            throw new DicomFlowObjectsParamMissingException("Param credential is missing to CertificateConfirm");


        if ( params.get("credential") == null)
            throw new ValueForParamShouldNotBeNullException("Value credential should not be null.");
        if ( params.get("status") == null)
            throw new ValueForParamShouldNotBeNullException("Value status should not be null.");

        // TODO: 10/07/17 verificar o tipo do status

    }
}
