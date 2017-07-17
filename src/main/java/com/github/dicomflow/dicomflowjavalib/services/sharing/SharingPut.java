package com.github.dicomflow.dicomflowjavalib.services.sharing;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Url;
import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class SharingPut extends Sharing {

    @ElementList(name = "ulrs", inline = true) public final List<Url> urls;

    public SharingPut(String from, List<Url> urls){
        super("PUT", from, ServiceIF.SHARING_PUT);
        this.urls = urls;
    }

}
