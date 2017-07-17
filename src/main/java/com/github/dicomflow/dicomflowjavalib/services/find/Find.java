package com.github.dicomflow.dicomflowjavalib.services.find;

import com.github.dicomflow.dicomflowjavalib.services.Service;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public class Find extends Service {
    public Find(String action, String from, int type){
        super("FIND", action, from, type);
    }
}
