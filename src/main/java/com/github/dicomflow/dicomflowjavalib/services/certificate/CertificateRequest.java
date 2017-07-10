package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Domain;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Mail;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Port;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Url;

import java.util.Map;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class CertificateRequest extends Certificate {

    public CertificateRequest(String from, Domain domain, Mail mail, Port port) {
        super("REQUEST", from, domain, mail, port);
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
