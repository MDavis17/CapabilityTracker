package com.mdavis.capability_tracker;

import java.util.Arrays;
import java.util.Vector;

public class Capability {
    private String name;
    private Version version;
    private Vector valueThemes;


    public Capability() {
        this.name = "";
        this.version = new Version();
        this.valueThemes = new Vector();
    }

    public Capability(String name,String version,String valueThemes) {
        this.name = name;
        this.version = new Version(version);
        if (valueThemes.length() != 0)
            this.valueThemes = new Vector(Arrays.asList((valueThemes.replaceAll("\\s+","")).split(",",0)));
        else
            this.valueThemes = new Vector();
    }

    public String getName() {
        return name;
    }

    public Version getVersion() {
        return version;
    }

    public Vector getValueThemes() {
        return valueThemes;
    }

}
