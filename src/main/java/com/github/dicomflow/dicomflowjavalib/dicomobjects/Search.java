package com.github.dicomflow.dicomflowjavalib.dicomobjects;

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
public class Search extends SimplestDicomFlowObject {
    @ElementList(inline = true) public final List<Patient> patients;

    public Search(@ElementList(name="patients", inline = true) List<Patient> patients) {
        this.patients = patients;
    }

    public Search(Map<String, Object> params) {
        super(params);

        List<Map<String, Object>> paramsList = (List<Map<String, Object>>) params.get("patients");
        this.patients =  new ArrayList<>();
        for (Map<String, Object> paramItem : paramsList) {
            this.patients.add(new Patient(paramItem));
        }
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Patient p : patients) mapList.add(p.toMap());
        map.put("patients", mapList);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {

        if ( !params.containsKey("patients"))
            throw new DicomFlowObjectsParamMissingException("Param patients is missing for Search.");
        if ( params.get("patients") == null)
            throw new ValueForParamShouldNotBeNullException("Value patients is null and has to be a list not empty.");
        if ( !(params.get("patients") instanceof List))
            throw new ValueForParamMustBeAListNotEmptyException("Value patients must be a list type.");
        if ( ( (List)params.get("patients")).isEmpty())
            throw new ValueForParamMustBeAListNotEmptyException("Value patients must be a not empty list.");

    }
}
