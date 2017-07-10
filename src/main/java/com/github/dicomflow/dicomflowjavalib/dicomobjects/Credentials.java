package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ricardobarbosa on 21/06/17.
 */

@Root
public class Credentials extends SimplestDicomFlowObject{
    @Element(name = "value") public final String value;

    public Credentials(@Element(name = "value") String value) {
        super();
        this.value = value;
    }

    public Credentials(Map<String, Object> params) {
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
            throw new DicomFlowObjectsParamMissingException("Param 'value' is missing for Credentials.");
        if ( params.get("value") == null)
            throw new ValueForParamShouldNotBeNullException("Param value should not be null.");
    }
}
