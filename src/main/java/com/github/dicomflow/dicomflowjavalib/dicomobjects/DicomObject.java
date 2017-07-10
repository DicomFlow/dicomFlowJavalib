package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ricardobarbosa on 21/06/17.
 */

@Root(name = "object")
public class DicomObject extends SimplestDicomFlowObject{
    public static enum Type {
        Study, Serie, Instance
    }
    @Element(name = "credential") public final Credentials credentials;
    @Attribute public final String id;
    @Attribute public final String type;

    public DicomObject(@Element(name = "credential") Credentials credentials,
                       @Attribute(name = "id") String id,
                       @Attribute(name = "type") String type) {
        this.credentials = credentials;
        this.id = id;
        this.type = type;
    }

    public DicomObject(Map<String, Object> params) {
        verifyParams(params);
        this.id = (String) params.get("id");
        this.type = (String) params.get("type");
        this.credentials = new Credentials((Map<String, Object> )params.get("credentials"));
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);
        map.put("credentials", credentials.toMap());
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("id"))
            throw new DicomFlowObjectsParamMissingException("Param id is missing for DicomObject.");
        if ( params.get("id") == null)
            throw new ValueForParamShouldNotBeNullException("Param id should not be null.");

        if (!params.containsKey("type"))
            throw new DicomFlowObjectsParamMissingException("Param type is missing for DicomObject.");
        if ( params.get("type") == null)
            throw new ValueForParamShouldNotBeNullException("Param type should not be null.");

        if (!params.containsKey("credentials"))
            throw new DicomFlowObjectsParamMissingException("Param credentials is missing for DicomObject.");
        if ( params.get("credentials") == null)
            throw new ValueForParamShouldNotBeNullException("Param credentials should not be null.");
    }


}
