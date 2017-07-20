package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ricardobarbosa on 22/06/17.
 */

@Root
public class Result extends SimplestDicomFlowObject{
    @Element public final Completed completed;
    @Element public final Data data;
    @Element public final String originalMessageID;
    @Element  public final String timestamp;
    @ElementList public final List<Url> urls;

    public Result(
                @Element(name = "completed" ) Completed completed,
                @Element(name = "data") Data data,
                @Element(name = "originalMessageID" ) String originalMessageID,
                @Element(name = "timestamp" ) String timestamp,
                @ElementList(name = "urls")   List<Url> urls) {
        this.completed = completed;
        this.data = data;
        this.originalMessageID = originalMessageID;
        this.timestamp = timestamp;
        this.urls = urls;
    }

    public Result(Map<String, Object> params) {
        super(params);
        this.originalMessageID = (String) params.get("originalMessageID");
        this.timestamp = (String) params.get("timestamp");

        this.completed = new Completed((Map<String, Object>) params.get("completed"));
        this.data = new Data((Map<String, Object>) params.get("data"));

        List<Map<String, Object>> paramsList = (List<Map<String, Object>>) params.get("urls");
        this.urls =  new ArrayList<>();
        for (Map<String, Object> paramItem : paramsList) {
            this.urls.add(new Url(paramItem));
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("completed", completed.toMap());
        map.put("data", data.toMap());
        map.put("originalMessageID", originalMessageID);
        map.put("timestamp", timestamp);

        List<Map<String, Object>> listOfMaps = new ArrayList<>();
        for (Url p : urls) listOfMaps.add(p.toMap());
        map.put("urls", listOfMaps);

        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("completed"))
            throw new DicomFlowObjectsParamMissingException("Param completed is missing to Result.");
        if (!params.containsKey("originalMessageID"))
            throw new DicomFlowObjectsParamMissingException("Param originalMessageID is missing to Result.");
        if (!params.containsKey("timestamp"))
            throw new DicomFlowObjectsParamMissingException("Param timestamp is missing to Result.");

        if ( !params.containsKey("urls") )
            throw new DicomFlowObjectsParamMissingException("Param urls is missing for Result.");
        if ( params.get("urls") == null)
            throw new ValueForParamShouldNotBeNullException("Param urls is null and has to be a list not empty.");
        if ( !(params.get("urls") instanceof List))
            throw new ValueForParamMustBeAListNotEmptyException("Param urls must be a list type.");
        if ( ( (List)params.get("urls")).isEmpty())
            throw new ValueForParamMustBeAListNotEmptyException("Param urls must be a not empty list.");
    }

}
