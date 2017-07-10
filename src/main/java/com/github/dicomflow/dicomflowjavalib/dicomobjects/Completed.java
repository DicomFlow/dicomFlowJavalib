package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ricardobarbosa on 22/06/17.
 */
@Root
public class Completed extends SimplestDicomFlowObject{
    // TODO: 10/07/17 no verify params colocar que o status deve ser um dos dois abaixo
    public enum Status {
        SUCCESS, ERROR
    }

    @Attribute public final String status;
    @Element public final String completedMessage;

    public Completed(@Attribute(name = "status") String status,
                     @Element(name = "completedMessage") String completedMessage) {
        this.status = status;
        this.completedMessage = completedMessage;
    }

    public Completed(Map<String, Object> params) {
        verifyParams(params);
        this.status = (String) params.get("status");
        this.completedMessage = (String) params.get("completedMessage");
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("completedMessage", completedMessage);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("status"))
            throw new DicomFlowObjectsParamMissingException("Param status is missing for Completed.");
        if ( params.get("status") == null)
            throw new ValueForParamShouldNotBeNullException("Value status should not be null.");

        if (!params.containsKey("completedMessage"))
            throw new DicomFlowObjectsParamMissingException("Param completedMessage is missing for Completed.");
        if ( params.get("completedMessage") == null)
            throw new ValueForParamShouldNotBeNullException("Value completedMessage should not be null.");
    }

}

