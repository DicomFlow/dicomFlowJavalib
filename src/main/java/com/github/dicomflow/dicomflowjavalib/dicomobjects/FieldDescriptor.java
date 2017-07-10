package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ricardobarbosa on 22/06/17.
 */
@Root
public class FieldDescriptor extends SimplestDicomFlowObject {
    @Attribute public final String name;
    @Attribute public final String status;

    public FieldDescriptor(@Attribute(name = "name") String name,
                           @Attribute(name = "status")String status) {
        this.name = name;
        this.status = status;
    }

    public FieldDescriptor(Map<String, Object> params) {
        super(params);
        this.name = (String) params.get("name");
        this.status = (String) params.get("status");;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", name);
        map.put("status", status);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("status"))
            throw new DicomFlowObjectsParamMissingException("Param status is missing for FieldDescriptor.");
        if ( params.get("status") == null)
            throw new ValueForParamShouldNotBeNullException("Value status should not be null.");

        if (!params.containsKey("name"))
            throw new DicomFlowObjectsParamMissingException("Param name is missing for FieldDescriptor.");
        if ( params.get("name") == null)
            throw new ValueForParamShouldNotBeNullException("Value name should not be null.");
    }


}
