package com.github.dicomflow.dicomflowjavalib.dicomobjects;

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
public class Study extends SimplestDicomFlowObject {
    @Attribute public final String id;
    @Attribute public final String type;
    @Attribute public final String description;
    @Attribute public final Integer datetime;
    @Attribute public final Long size;

    @ElementList(name = "series", inline = true) public final List<Serie> series;

    public Study(@Attribute(name="id") String id,
                 @Attribute(name="type") String type,
                 @Attribute(name="description") String description,
                 @Attribute(name="datetime") Integer datetime,
                 @Attribute(name="size") Long size,
                 @ElementList(name = "series", inline = true) List<Serie> series) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.datetime = datetime;
        this.size = size;
        this.series = series;
    }

    public Study(Map<String, Object> params) {
        super(params);
        this.id = (String) params.get("id");
        this.type = (String) params.get("type");
        this.description = (String) params.get("description");
        this.datetime = ((Number) params.get("datetime")).intValue();
        this.size = ((Number) params.get("size")).longValue();

        this.series = new ArrayList<>();
        List<Map<String, Object>> paramsSeries = (List<Map<String, Object>>) params.get("series");
        for (Map<String, Object> paramSerie : paramsSeries) {
            if (paramSerie == null) continue;
            this.series.add(new Serie( paramSerie ));
        }

    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);
        map.put("description", description);
        map.put("datetime", datetime);
        map.put("size", size);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Serie o : series) mapList.add(o.toMap());
        map.put("series", mapList);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("id"))
            throw new DicomFlowObjectsParamMissingException("Param id is missing to Study.");
        if (!params.containsKey("type"))
            throw new DicomFlowObjectsParamMissingException("Param type is missing to Study.");
        if (!params.containsKey("description"))
            throw new DicomFlowObjectsParamMissingException("Param description is missing to Study.");
        if (!params.containsKey("datetime"))
            throw new DicomFlowObjectsParamMissingException("Param datetime is missing to Study.");
        if (!params.containsKey("size"))
            throw new DicomFlowObjectsParamMissingException("Param size is missing to Study.");

        if ( !params.containsKey("series") )
            throw new DicomFlowObjectsParamMissingException("Param series is missing for Study.");
        if ( params.get("series") == null)
            throw new ValueForParamShouldNotBeNullException("Param series is null and has to be a list not empty.");
        if ( !(params.get("series") instanceof List))
            throw new ValueForParamMustBeAListNotEmptyException("Param studies must be a list type.");
        if ( ( (List)params.get("series")).isEmpty())
            throw new ValueForParamMustBeAListNotEmptyException("Param studies must be a not empty list.");
    }
}
