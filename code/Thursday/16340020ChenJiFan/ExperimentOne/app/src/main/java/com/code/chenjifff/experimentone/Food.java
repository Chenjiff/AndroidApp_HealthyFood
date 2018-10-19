package com.code.chenjifff.experimentone;

import android.graphics.Color;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private String type_simple;
    private String type;
    private String nutrient;
    private int background_color;

    public Food(String _name, String _type_simple, String _type, String _nutrient, String _background_color) {
        name = _name;
        type_simple = _type_simple;
        type = _type;
        nutrient = _nutrient;
        background_color = Color.parseColor(_background_color);
    }

    public String getName () {
        return name;
    }

    public String getType_simple () {
        return type_simple;
    }

    public String getType () {
        return type;
    }

    public String getNutrient () {
        return nutrient;
    }

    public int getBackground_color () {
        return background_color;
    }
}
