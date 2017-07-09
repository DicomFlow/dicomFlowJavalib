package com.github.dicomflow.dicomflowjavalib.services.storage;

import com.github.dicomflow.dicomflowjavalib.services.Service;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public abstract class Storage extends Service {

    public Storage(String action, String from) {
        super("STORAGE", action, from);
    }
}
