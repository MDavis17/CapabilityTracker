package com.mdavis.capability_tracker;

import java.util.Arrays;
import java.util.Vector;

import static com.mdavis.capability_tracker.Utils.*;

public class Capability {
    private String name;
    private Version version;
    private Vector valueThemes;


    public Capability() {
        this.name = "";
        this.version = new Version();
        this.valueThemes = new Vector();
    }

    public Capability(String name,String version,String a_b,String valueThemes) {
        this.name = name;
        this.version = new Version(version,getAlphaBetaStatus(a_b));
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

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void setValueThemes(Vector valueThemes) {
        this.valueThemes = valueThemes;
    }
}
