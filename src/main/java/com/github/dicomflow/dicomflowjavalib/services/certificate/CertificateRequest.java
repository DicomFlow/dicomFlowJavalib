package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Domain;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Mail;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Port;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class CertificateRequest extends Certificate {

    public CertificateRequest(String from, Domain domain, Mail mail, Port port) {
        super("REQUEST", from, domain, mail, port);
    }

}
