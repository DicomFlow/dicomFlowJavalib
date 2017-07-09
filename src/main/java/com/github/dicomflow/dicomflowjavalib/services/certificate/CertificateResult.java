package com.github.dicomflow.dicomflowjavalib.services.certificate;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Domain;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Mail;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Port;

import org.simpleframework.xml.Element;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class CertificateResult extends Certificate {

    @Element public final String credential;
    @Element public final String status;

    public CertificateResult(String from, Domain domain, Mail mail, Port port, String credential, String status) {
        super("RESULT", from, domain,mail,port);
        this.credential = credential;
        this.status = status;
    }

}
