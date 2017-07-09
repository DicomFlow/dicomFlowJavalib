package com.github.dicomflow.dicomflowjavalib.services.sharing;

import com.github.dicomflow.dicomflowjavalib.services.Service;

/**
 * Created by ricardobarbosa on 15/06/17.
 */
public abstract class Sharing extends Service {
    public Sharing(String action, String from){
        super("SHARING", action, from);
    }
}
