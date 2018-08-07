package com.mdavis.capability_tracker;

import java.io.Serializable;

public class Version implements Serializable {
    private char major;
    private char minor;
    private char patch;
    private char alpha_beta;

    public Version() {
        this.major = 0;
        this.minor = 0;
        this.patch = 0;
        this.alpha_beta = '\0';
    }

    public Version(int major,int minor, int patch, char a_b) {
        this.major = (char)major;
        this.minor = (char)minor;
        this.patch = (char)patch;
        this.alpha_beta = a_b;
    }

    public Version(String version) {
        if(version.contains("alpha") || version.contains("Alpha")) {
            this.alpha_beta = 'a';
            version = version.substring(0,5);
//            version.replace("alpha","");
        }
        else if(version.contains("beta") || version.contains("Beta")) {
            this.alpha_beta = 'b';
            version = version.substring(0,5);
//            version.replace("beta","");
        }

        String[] v_split = version.split("\\.",0);
//        this.major = Integer.parseInt(v_split[0]);
//        this.minor = Integer.parseInt(v_split[1]);
        this.major = v_split[0].trim().charAt(0);
        this.minor = v_split[1].trim().charAt(0);

        if(v_split.length > 2) {
//            this.patch = Integer.parseInt(v_split[2].trim());
            char p = v_split[2].trim().charAt(0);
            if(p == '?')
                this.patch = 'x';
            else
                this.patch = p;
        }
//        String foo = Character.toString(major)+"."+Character.toString(minor)+"."+Character.toString(patch);
//        foo.trim();
//        this.alpha_beta = a_b;
    }

    // remember to check for '?' in minor and patch
    public int getMajor() {
        return Character.getNumericValue(major);
    }

    public void setMajor(int major) {
        this.major = (char)major;
    }

    public int getMinor() {
        return Character.getNumericValue(minor);
    }

    public void setMinor(int minor) {
        this.minor = (char)minor;
    }

    public int getPatch() {
        return Character.getNumericValue(patch);
    }

    public void setPatch(int patch) {
        this.patch = (char)patch;
    }

    public char getAlpha_beta() {
        return alpha_beta;
    }

    public void setAlpha_beta(char alpha_beta) {
        this.alpha_beta = alpha_beta;
    }

    public String toString() {
        return Character.toString(major)+"."+Character.toString(minor)+"."+Character.toString(patch);
//        return Integer.toString(major)+"."+Integer.toString(minor)+"."+Integer.toString(patch);
    }

    public boolean equals(Version version) {
        if(this.toString() == version.toString())
            return true;
        else
            return false;
    }
}
