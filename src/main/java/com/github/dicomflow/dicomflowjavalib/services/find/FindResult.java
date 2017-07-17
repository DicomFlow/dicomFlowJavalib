package com.github.dicomflow.dicomflowjavalib.services.find;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Result;
import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class FindResult extends Find {

    @ElementList(name = "results", inline = true) public final List<Result> results;

    public FindResult(String from, List<Result> results) {
        super("RESULT", from, ServiceIF.FIND_RESULT);
        this.results = results;
    }

}
