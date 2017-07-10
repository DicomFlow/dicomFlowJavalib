package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Attribute;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ricardobarbosa on 21/06/17.
 */

public abstract class ValueObject extends SimplestDicomFlowObject{
    @Attribute(name = "value") public final String value;

    public ValueObject(@Attribute(name = "value") String value) {
        this.value = value;
    }

    public ValueObject(Map<String, Object> params) {
        super(params);
        this.value = (String) params.get("value");
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("value", value);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException {
        if (!params.containsKey("value"))
            throw new DicomFlowObjectsParamMissingException("Param value is missing for ValueObject.");
    }
}
