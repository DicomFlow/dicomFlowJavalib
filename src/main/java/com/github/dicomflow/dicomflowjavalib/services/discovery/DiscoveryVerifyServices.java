package com.github.dicomflow.dicomflowjavalib.services.discovery;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.ServiceDescriptor;
import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public abstract class DiscoveryVerifyServices extends Discovery {

    @ElementList(name = "services", inline = true) public final List<ServiceDescriptor> serviceDescriptors;

    public DiscoveryVerifyServices(String from, int priority, String timezone, List<ServiceDescriptor> serviceDescriptors) {
        super("VERIFYSERVICES", from, ServiceIF.DISCOVERY_VERIFY_SERVICES, priority, timezone);
        this.serviceDescriptors = serviceDescriptors;
    }

}
