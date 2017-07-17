package com.github.dicomflow.dicomflowjavalib.services;

import com.github.dicomflow.dicomflowjavalib.IDicomFlowObjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Root(name = "service")
public class Service implements IDicomFlowObjects {

    @Attribute
    public final String name;
    @Attribute
    public final String action;
    @Attribute
    public final String version;
    @Attribute
    public final int type;


    @Element
    public final String from;
    @Element(required = false)
    public final String timeout;
    @Element
    public final String timestamp;
    @Element
    public final String messageID;

    public Service(
            @Attribute(name = "name") String name,
            @Attribute(name = "action") String action,
            @Element(name = "from") String from,
            @Attribute(name = "type") int type,
            @Attribute(name = "version") String version,
            @Element(name = "timeout") String timeout,
            @Element(name = "timestamp") String timestamp,
            @Element(name = "messageID")String messageID) {
        this.name = name;
        this.action = action;
        this.version = version;
        this.timeout = timeout;
        this.timestamp = timestamp;
        this.messageID = messageID;
        this.from = from;
        this.type = type;
    }

    public Service(String name, String action, String from, int type) {
        this.version = "1.0";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ssZ");
        Date date = new Date();

        this.name = name;
        this.action = action;
        this.timestamp = dateFormat.format(date);
        this.messageID = UUID.randomUUID().toString();
        this.timeout = String.valueOf(date.getTime());
        this.from = from;
        this.type = type;
    }

    public Service(Map<String, Object> params) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm:ssZ");
        Date date = new Date();
        params.put("timestamp", dateFormat.format(date));
        params.put("messageID", UUID.randomUUID().toString());
        params.put("timeout", String.valueOf(date.getTime()));
        params.put("version", "1.0");

        verifyParams(params);

        this.version = (String) params.get("version");
        this.name = (String) params.get("name");
        this.action = (String) params.get("action");
        this.timestamp = (String) params.get("timestamp");
        this.messageID = (String) params.get("messageID");
        this.timeout = (String) params.get("timeout");
        this.from = (String) params.get("from");
        this.type = (int) params.get("type");
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("action", action);
        result.put("from", from);
        result.put("version", version);
        result.put("timeout", timeout);
        result.put("timestamp", timestamp);
        result.put("messageID", messageID);
        result.put("type", type);

        return result;
    }

    @Override
    public void verifyParams(Map<String, Object> params) throws DicomFlowObjectsParamMissingException, ValueForParamShouldNotBeNullException {
        if (!params.containsKey("version"))
            throw new DicomFlowObjectsParamMissingException("Param version is missing for Service.");
        if (!params.containsKey("name"))
            throw new DicomFlowObjectsParamMissingException("Param name is missing for Service.");
        if (!params.containsKey("action"))
            throw new DicomFlowObjectsParamMissingException("Param action is missing for Service.");
        if (!params.containsKey("from"))
            throw new DicomFlowObjectsParamMissingException("Param from is missing for Service.");
        if (!params.containsKey("timeout"))
            throw new DicomFlowObjectsParamMissingException("Param timeout is missing for Service.");
        if (!params.containsKey("timestamp"))
            throw new DicomFlowObjectsParamMissingException("Param timestamp is missing for Service.");
        if (!params.containsKey("messageID"))
            throw new DicomFlowObjectsParamMissingException("Param messageID is missing for Service.");
    }

}