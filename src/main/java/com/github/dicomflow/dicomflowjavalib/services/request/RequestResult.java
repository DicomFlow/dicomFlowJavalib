package com.github.dicomflow.dicomflowjavalib.services.request;

import com.github.dicomflow.dicomflowjavalib.dicomobjects.Result;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Serie;
import com.github.dicomflow.dicomflowjavalib.dicomobjects.Study;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by netolucena on 22/06/2017.
 */
@Root(name = "service")
public class RequestResult extends Request{

    @ElementList(name = "results", inline = true) public final List<Result> results;

    public RequestResult(
            @Attribute(name = "from") String from,
            @ElementList(name = "results", inline = true) List<Result> results) {
        super("RESULT", from);
        this.results = results;
    }

    public RequestResult(@Attribute(name = "name") String name,
                         @Attribute(name = "action") String action,
                         @Element(name = "from") String from,
                         @Attribute(name = "version") String version,
                         @Element(name = "timeout") String timeout,
                         @Element(name = "timestamp") String timestamp,
                         @Element(name = "messageID")String messageID,
                         @ElementList(name = "results", inline = true) List<Result> results) {
        super(name, action, from, version, timeout, timestamp, messageID);
        this.results = results;
    }

    public RequestResult(Map<String, Object> params) {
        super(params);

        this.results = new ArrayList<>();
        List<Map<String, Object>> paramsList = (List<Map<String, Object>>) params.get("results");
        for (Map<String, Object> paramItem : paramsList) {

            this.results.add(new Result(paramItem));
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        List<Map<String, Object>> listOfMaps = new ArrayList<>();
        for (Result o : results) listOfMaps.add(o.toMap());
        map.put("results", listOfMaps);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        super.verifyParams(params);

        if ( !params.containsKey("results"))
            throw new DicomFlowObjectsParamMissingException("Param patients is missing for RequestResult.");
        if ( params.get("results") == null)
            throw new ValueForParamShouldNotBeNullException("Value results is null and has to be a list not empty.");
        if ( !(params.get("results") instanceof List))
            throw new ValueForParamMustBeAListNotEmptyException("Value results must be a list type.");
        if ( ( (List)params.get("results")).isEmpty())
            throw new ValueForParamMustBeAListNotEmptyException("Value results must be a not empty list.");

    }
}
