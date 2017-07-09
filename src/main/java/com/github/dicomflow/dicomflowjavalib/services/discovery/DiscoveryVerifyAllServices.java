package com.github.dicomflow.dicomflowjavalib.services.discovery;

import org.simpleframework.xml.Element;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public abstract class DiscoveryVerifyAllServices extends Discovery {

    @Element public final int detail;

    public DiscoveryVerifyAllServices(String from, int detail, int priority, String timezone) {
        super("VERIFYALLSERVICES", from, priority, timezone);
        this.detail = detail;
    }


}
