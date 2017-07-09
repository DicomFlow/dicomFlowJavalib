package com.github.dicomflow.dicomflowjavalib.dicomobjects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ricardobarbosa on 22/06/17.
 */

@Root
public class Data {
    @Attribute public final String filename;
    @Element(name = "bytes") public final String encoded;

    public Data(
            @Attribute(name = "filename") String filename,
            @Element(name = "bytes") String encoded) {
            this.filename = filename;
            this.encoded = FileUtil.encodeFileToBase64Binary(encoded);
    }

}
