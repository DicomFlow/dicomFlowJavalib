package com.github.dicomflow.dicomflowjavalib.services.storage;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.DicomObject;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Result;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class StorageResult extends Storage {

    @ElementList(name = "objects" ) public final List<DicomObject> objects;
    @ElementList(name = "results" ) public final List<Result> results;

    public StorageResult(String from, List<DicomObject> objects, List<Result> results){
        super("RESULT", from);
        this.objects = objects;
        this.results = results;
    }


}
