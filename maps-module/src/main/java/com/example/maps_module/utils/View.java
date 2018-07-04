package com.example.maps_module.utils;

public class View {
    private static float currentAngleLength;

    public static float getCurrentAngleLength() {
        return currentAngleLength;
    }

    public static void setCurrentAngleLength(float currentAngleLength) {
        View.currentAngleLength = currentAngleLength;
    }
}
