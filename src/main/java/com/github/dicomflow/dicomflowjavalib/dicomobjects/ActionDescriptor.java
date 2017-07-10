package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;

import org.simpleframework.xml.Attribute;
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
public class ActionDescriptor extends SimplestDicomFlowObject {
    @Attribute public final String name;
    @Attribute public final String status;
    @ElementList(inline = true) public final List<FieldDescriptor> fieldDescriptors;

    public ActionDescriptor(@Attribute(name = "name") String name,
                            @Attribute(name = "status") String status,
                            @ElementList(inline = true) List<FieldDescriptor> fieldDescriptors) {
        this.name = name;
        this.status = status;
        this.fieldDescriptors = fieldDescriptors;
    }

    public ActionDescriptor(Map<String, Object> params) {
        super(params);
        this.name = (String) params.get("name");
        this.status = (String) params.get("status");

        this.fieldDescriptors = new ArrayList<>();
        List<Map<String, Object>> paramList = (List<Map<String, Object>>) params.get("fieldDescriptors");
        for (Map<String, Object> paramItem : paramList) {
            this.fieldDescriptors.add(new FieldDescriptor( paramItem ));
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", name);
        map.put("status", status);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (FieldDescriptor o : fieldDescriptors) mapList.add( o.toMap());
        map.put("actionDescriptors", mapList);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("status"))
            throw new DicomFlowObjectsParamMissingException("Param status is missing for ActionDescriptor.");
        if ( params.get("status") == null)
            throw new ValueForParamShouldNotBeNullException("Value status should not be null.");


        if (!params.containsKey("name"))
            throw new DicomFlowObjectsParamMissingException("Param name is missing for ActionDescriptor.");
        if ( params.get("name") == null)
            throw new ValueForParamShouldNotBeNullException("Value name should not be null.");

        if ( !params.containsKey("fieldDescriptors"))
            throw new DicomFlowObjectsParamMissingException("Param fieldDescriptors is missing for ActionDescriptor.");
        if ( params.get("fieldDescriptors") == null)
            throw new ValueForParamShouldNotBeNullException("Value fieldDescriptors is null and has to be a list not empty.");
        if ( !(params.get("fieldDescriptors") instanceof List))
            throw new ValueForParamMustBeAListNotEmptyException("Value fieldDescriptors must be a list type.");
        if ( ( (List)params.get("fieldDescriptors")).isEmpty())
            throw new ValueForParamMustBeAListNotEmptyException("Value fieldDescriptors must be a not empty list.");

    }
}

