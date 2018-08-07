package com.mdavis.capability_tracker;

public class Utils {
    public static final int UNDETERMINED = -1;
    public static final int DEFAULT = -2;
    public static final int ALPHA = 1;
    public static final int BETA = 2;
    public static final int WIDTH_OF_SHEET = 9;
    public static final int CAP_NAME_INDEX = 0;
    public static final int CAP_VERSION_START_INDEX = 1;
    public static final int CAP_VERSION_END_INDEX = 5;
    public static final int CAP_A_B_INDEX = 6;
    public static final int CAP_CUSTOMER_INDEX = 7;
    public static final int CAP_THEME_INDEX = 8;

    public static int getAlphaBetaStatus(String a_b_str) {
        if(a_b_str == "alpha" || a_b_str == "Alpha")
            return ALPHA;
        else if(a_b_str == "beta" || a_b_str == "Beta")
            return BETA;
        else
            return DEFAULT;
    }
}
