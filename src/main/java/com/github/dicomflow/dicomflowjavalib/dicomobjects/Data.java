package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;
import com.github.dicomflow.dicomflowjavalib.utils.FileUtil;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ricardobarbosa on 22/06/17.
 */

@Root
public class Data extends SimplestDicomFlowObject{
    @Attribute public final String filename;
    @Element(name = "bytes") public final String encoded;

    public Data(
            @Attribute(name = "filename") String filename,
            @Element(name = "bytes") String encoded) {
            this.filename = filename;
            this.encoded = FileUtil.encodeFileToBase64Binary(encoded);
    }

    public Data(Map<String, Object> params) {
        super(params);
        this.filename = (String) params.get("filename");
        this.encoded = (String) params.get("encoded");
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("filename", filename);
        map.put("encoded", encoded);
        return map;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("encoded"))
            throw new DicomFlowObjectsParamMissingException("Param encoded is missing for Data.");
        if (!params.containsKey("filename"))
            throw new DicomFlowObjectsParamMissingException("Param filename is missing for Data.");
    }

}
