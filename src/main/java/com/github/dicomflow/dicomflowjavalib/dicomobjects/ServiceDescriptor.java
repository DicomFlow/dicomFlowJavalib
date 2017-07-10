package com.github.dicomflow.dicomflowjavalib.dicomobjects;

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
public class ServiceDescriptor extends SimplestDicomFlowObject {
    @Attribute public final String name;
    @Attribute public final String status;
    @ElementList(inline = true) public final List<ActionDescriptor> actionDescriptors;

    public ServiceDescriptor(@Attribute(name="name") String name,
                             @Attribute(name="status") String status,
                             @ElementList(inline = true) List<ActionDescriptor> actionDescriptors) {
        this.name = name;
        this.status = status;
        this.actionDescriptors = actionDescriptors;
    }

    public ServiceDescriptor(Map<String, Object> params) {
        super(params);
        this.name = (String) params.get("name");
        this.status = (String) params.get("status");

        this.actionDescriptors = new ArrayList<>();
        List<Map<String, Object>> paramList = (List<Map<String, Object>>) params.get("actionDescriptors");
        for (Map<String, Object> paramItem : paramList) {
            this.actionDescriptors.add(new ActionDescriptor( paramItem ));
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", name);
        map.put("status", status);
        Map<String, Object> mapList3 = new HashMap<String, Object>();
        for (ActionDescriptor o : actionDescriptors) mapList3.put(o.name, o.toMap());
        map.put("actionDescriptors", mapList3);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("status"))
            throw new DicomFlowObjectsParamMissingException("Param status is missing for ServiceDescriptor.");
        if ( params.get("status") == null)
            throw new ValueForParamShouldNotBeNullException("Value status should not be null.");


        if (!params.containsKey("name"))
            throw new DicomFlowObjectsParamMissingException("Param status is missing for ServiceDescriptor.");
        if ( params.get("name") == null)
            throw new ValueForParamShouldNotBeNullException("Value status should not be null.");

        if ( !params.containsKey("actionDescriptors"))
            throw new DicomFlowObjectsParamMissingException("Param actionDescriptors is missing for ServiceDescriptor.");
        if ( params.get("actionDescriptors") == null)
            throw new ValueForParamShouldNotBeNullException("Value actionDescriptors is null and has to be a list not empty.");
        if ( !(params.get("actionDescriptors") instanceof List))
            throw new ValueForParamMustBeAListNotEmptyException("Value actionDescriptors must be a list type.");
        if ( ( (List)params.get("actionDescriptors")).isEmpty())
            throw new ValueForParamMustBeAListNotEmptyException("Value actionDescriptors must be a not empty list.");

    }
}
