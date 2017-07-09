package com.github.dicomflow.dicomflowjavalib.services.discovery;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Result;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public abstract class DiscoveryVerifyResult extends Discovery {

    @ElementList(inline = true) public final List<Result> results;

    public DiscoveryVerifyResult(String from, int priority, String timezone, List<Result> results) {
        super("VERIFYRESULT", from, priority, timezone);
        this.results = results;
    }

}
