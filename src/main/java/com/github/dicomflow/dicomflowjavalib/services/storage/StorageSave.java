package com.github.dicomflow.dicomflowjavalib.services.storage;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Url;
import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;

import org.simpleframework.xml.Element;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class StorageSave extends Storage {

    @Element public final Url url;

    public StorageSave(String from, Url url){
        super("SAVE", from, ServiceIF.STORAGE_SAVE);
        this.url = url;
    }



}
