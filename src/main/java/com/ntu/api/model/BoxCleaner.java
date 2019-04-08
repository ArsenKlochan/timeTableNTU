package com.ntu.api.model;

import javafx.scene.control.ComboBox;

public class BoxCleaner {
    public static void boxClear(ComboBox<String> box){
        box.promptTextProperty().setValue(null);
        box.setValue(null);
    }
}
