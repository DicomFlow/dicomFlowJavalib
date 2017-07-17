package com.github.dicomflow.dicomflowjavalib.services.sharing;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Result;
import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class SharingResult extends Sharing {

    @ElementList(name = "results", inline = true) public final List<Result> results;

    public SharingResult(String from, List<Result> results){
        super("RESULT", from, ServiceIF.SHARING_RESULT);
        this.results = results;
    }

}
