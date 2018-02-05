package com.marcelo.privalia.moviesapp.presentation.ui.display;

public class DensityUtil {
    private static final String ldpi = "0.75";
    private static final String mdpi = "1.0";
    private static final String hdpi = "1.5";
    private static final String xhdpi = "2.0";
    private static final String xxhdpi = "3.0";
    public static final String W92 = "w92";
    public static final String W154 = "w154";
    public static final String W185 = "w185";
    public static final String W342 = "w342";
    public static final String W500 = "w500";
    public static final String W780 = "w780";

    public static String getSizeMovie(double density){
        switch (String.valueOf(density)){
            case ldpi:
                return W92;
            case mdpi:
                return W154;
            case hdpi:
                return W185;
            case xhdpi:
                return W342;
            case xxhdpi:
                return W500;
            default:
                return W780;
        }
    }
}
