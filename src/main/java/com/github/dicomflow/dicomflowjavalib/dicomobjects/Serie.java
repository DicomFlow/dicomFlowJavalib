package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ricardobarbosa on 21/06/17.
 */

@Root
public class Serie extends SimplestDicomFlowObject {
    @Attribute public final String id;
    @Attribute public final String bodypart;
    @Attribute public final String description;
    @Attribute public final Integer instances;

    public Serie(@Attribute(name = "id") String id,
                 @Attribute(name = "bodypart") String bodypart,
                 @Attribute(name = "description") String description,
                 @Attribute(name = "instances") Integer instances){
        this.id = id;

        this.bodypart = bodypart;
        this.description = description;
        this.instances = instances;
    }

    public Serie(Map<String, Object> params) {
        super(params);
        this.id = (String) params.get("id");
        this.bodypart = (String) params.get("bodypart");
        this.description = (String) params.get("description");
        this.instances = ((Number) params.get("instances")).intValue();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("bodypart", bodypart);
        map.put("description", description);
        map.put("instances", instances);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("id"))
            throw new DicomFlowObjectsParamMissingException("Param id is missing to Result.");
        if (!params.containsKey("bodypart"))
            throw new DicomFlowObjectsParamMissingException("Param bodypart is missing to Result.");
        if (!params.containsKey("description"))
            throw new DicomFlowObjectsParamMissingException("Param description is missing to Result.");
        if (!params.containsKey("instances"))
            throw new DicomFlowObjectsParamMissingException("Param instances is missing to Result.");

        if ( params.get("id") == null)
            throw new ValueForParamShouldNotBeNullException("Param id should not be null.");
        if ( params.get("bodypart") == null)
            throw new ValueForParamShouldNotBeNullException("Param bodypart should not be null.");
        if ( params.get("description") == null)
            throw new ValueForParamShouldNotBeNullException("Param description should not be null.");
        if ( params.get("instances") == null)
            throw new ValueForParamShouldNotBeNullException("Param instances should not be null.");
    }
}
