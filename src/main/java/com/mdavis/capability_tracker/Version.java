package com.mdavis.capability_tracker;

import java.io.Serializable;

import static com.mdavis.capability_tracker.Utils.*;

public class Version implements Serializable {
    private int major;
    private int minor;
    private int patch;
    private int alpha_beta;

    public Version() {
        this.major = DEFAULT;
        this.minor = DEFAULT;
        this.patch = DEFAULT;
        this.alpha_beta = DEFAULT;
    }

    public Version(int major,int minor, int patch, int a_b) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.alpha_beta = a_b;
    }

    public Version(String version,int alpha_beta) {
        String[] v_split = version.split("\\.",0);
        this.major = Integer.parseInt(v_split[0]);

        // check for ? in both minor and patch

        if(v_split[1].trim().equals("?"))
            this.minor = UNDETERMINED;
        else
            this.minor = Integer.parseInt(v_split[1]);
        this.alpha_beta = alpha_beta;

        if(v_split.length > 2) {
            if(v_split[2].trim().equals("?"))
                this.patch = UNDETERMINED;
            else
                this.patch = Integer.parseInt(v_split[2].trim());

//            this.patch = Integer.parseInt(v_split[2].trim());
//            char p = v_split[2].trim().charAt(0);
//            if(p == '?')
//                this.patch = 'x';
//            else
//                this.patch = p;
        }

    }

    // remember to check for '?' in minor and patch
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

    public int getAlpha_beta() {
        return alpha_beta;
    }

    public void setAlpha_beta(int alpha_beta) {
        this.alpha_beta = alpha_beta;
    }

    //TODO think about adding alpha and beta into the test for equallity
    public String toString() {
//        return Character.toString(major)+"."+Character.toString(minor)+"."+Character.toString(patch);
        return Integer.toString(major)+"."+Integer.toString(minor)+"."+Integer.toString(patch);
    }

    public boolean equals(Version version) {
        if(this.toString() == version.toString())
            return true;
        else
            return false;
    }
}
