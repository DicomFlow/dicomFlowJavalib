package com.github.dicomflow.dicomflowjavalib.services.find;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Search;
import com.github.dicomflow.dicomflowjavalib.services.ServiceIF;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class FindPut extends Find {

    @Element public final int priority;
    @ElementList(inline = true) public final List<Search> searches;
    @Element public final String timezone;

    public FindPut(String from, int priority, List<Search> searches, String timezone) {
        super("PUT", from, ServiceIF.FIND_PUT);
        this.priority = priority;
        this.searches = searches;
        this.timezone = timezone;
    }

}
