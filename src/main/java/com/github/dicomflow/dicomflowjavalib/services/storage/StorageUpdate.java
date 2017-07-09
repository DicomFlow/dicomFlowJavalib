package com.github.dicomflow.dicomflowjavalib.services.storage;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.DicomObject;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Url;

import org.simpleframework.xml.Element;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class StorageUpdate extends Storage {

    @Element public final Url url;
    @Element public final DicomObject object;

    public StorageUpdate(String from, Url url, DicomObject object){
        super("UPDATE", from);
        this.url = url;
        this.object = object;
    }

}
