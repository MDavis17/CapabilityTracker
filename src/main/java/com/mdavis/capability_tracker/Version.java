package com.mdavis.capability_tracker;

import java.io.Serializable;

public class Version implements Serializable {
    private int major;
    private int minor;
    private int patch;

    public Version() {
        this.major = 0;
        this.minor = 0;
        this.patch = 0;
    }

    public Version(int major,int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public Version(String version) {
        String[] v_split = version.split("\\.",0);
        this.major = Integer.parseInt(v_split[0]);
        this.minor = Integer.parseInt(v_split[1]);
        if(v_split.length > 2)
            this.patch = Integer.parseInt(v_split[2]);
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getPatch() {
        return patch;
    }

    public void setPatch(int patch) {
        this.patch = patch;
    }
}
