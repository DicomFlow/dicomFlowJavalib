package com.github.dicomflow.dicomflowjavalib.services.request;

import com.github.dicomflow.dicomflowjavalib.services.Service;

import java.util.Map;

/**
 * Created by netolucena on 22/06/17.
 */
public abstract class Request extends Service {

    public Request(String action, String from, int type) {
        super("REQUEST", action, from, type);
    }

    public Request(String name, String action, String from, int type, String version, String timeout, String timestamp, String messageID) {
        super(name, action, from, type, version, timeout, timestamp, messageID);
    }

    public Request(Map<String, Object> params) {
        super(params);
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        return map;
    }
}
