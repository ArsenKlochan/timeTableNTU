package com.ntu.api.model;

import javafx.scene.control.ComboBox;

public class BoxCleaner {
    public static void boxClear(ComboBox<String> box){
        box.promptTextProperty().setValue(null);
        box.setValue(null);
    }
    public static void boxTwoClear(ComboBox<String> box, ComboBox<String> box1){
        box.promptTextProperty().setValue(null);
        box.setValue(null);
        box1.promptTextProperty().setValue(null);
        box1.setValue(null);
    }
}
