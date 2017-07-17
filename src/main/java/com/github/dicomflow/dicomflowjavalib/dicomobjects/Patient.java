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
 * Created by ricardobarbosa on 21/06/17.
 */

@Root
public class Patient extends SimplestDicomFlowObject{
    @Attribute public final String id;
    @Attribute public final String name;
    @Attribute public final String gender;
    @Attribute public final String birthdate;

    @ElementList(name = "study", inline = true) public final List<Study> studies;

    public Patient(@Attribute(name = "id") String id,
                   @Attribute(name = "name") String name,
                   @Attribute(name = "gender") String gender,
                   @Attribute(name = "birthdate") String birthdate,
                   @ElementList(name = "study", inline = true) List<Study> studies) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.studies = studies;
    }

    public Patient(Map<String, Object> params) {
        this.id = (String) params.get("id");
        this.name = (String) params.get("name");
        this.gender = (String) params.get("gender");
        this.birthdate = (String) params.get("birthdate");

        this.studies = new ArrayList<>();
        List<Map<String, Object>> paramsStudies = (List<Map<String, Object>>) params.get("studies");
        for (Map<String, Object> paramsStudy : paramsStudies) {
            if (paramsStudy == null) continue;
            this.studies.add(new Study( paramsStudy));
        }

    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("gender", gender);
        map.put("birthdate", birthdate);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Study p : studies) mapList.add(p.toMap());
        map.put("studies", mapList);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("id"))
            throw new DicomFlowObjectsParamMissingException("Param id is missing to Patient.");
        if (!params.containsKey("name"))
            throw new DicomFlowObjectsParamMissingException("Param name is missing to Patient.");
        if (!params.containsKey("gender"))
            throw new DicomFlowObjectsParamMissingException("Param gender is missing to Patient.");
        if (!params.containsKey("birthdate"))
            throw new DicomFlowObjectsParamMissingException("Param birthdate is missing to Patient.");

        if ( !params.containsKey("studies"))
            throw new DicomFlowObjectsParamMissingException("Param studies is missing for Patient.");
        if ( params.get("studies") == null)
            throw new ValueForParamShouldNotBeNullException("Param studies is null and has to be a list not empty.");
        if ( !(params.get("studies") instanceof List))
            throw new ValueForParamMustBeAListNotEmptyException("Param studies must be a list type.");
        if ( ( (List)params.get("studies")).isEmpty())
            throw new ValueForParamMustBeAListNotEmptyException("Param studies must be a not empty list.");
    }
}
