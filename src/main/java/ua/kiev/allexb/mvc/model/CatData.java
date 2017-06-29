package ua.kiev.allexb.mvc.model;

import java.util.ArrayList;
import java.util.List;

public final class CatData {

    public static final String MODEL_OBJECT = "catModel";
    public static final List<String> COLUMN_NAMES = new ArrayList<>();

    static {
        COLUMN_NAMES.add("Name");
        COLUMN_NAMES.add("Weight");
        COLUMN_NAMES.add("Color");
    }
}
