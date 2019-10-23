package com.ntu.api.controller;

import com.ntu.api.domain.BaseObject;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;


public class Filter {
    public static <T extends BaseObject> ArrayList<T> filtere(ObservableList<String> objectList, ComboBox<String> box, List<T> objects){
            box.setEditable(true);
            ArrayList<T> objectsList = new ArrayList<>();
            FilteredList<String> filtered = new FilteredList<>(objectList, p -> true);
            box.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
                final TextField editor = box.getEditor();
                final String selected = box.getSelectionModel().getSelectedItem();
                Platform.runLater(() -> {
                    if (selected == null || !selected.equals(editor.getText())) {
                        filtered.setPredicate(items -> {
                            if (items.toUpperCase().contains(newValue.toUpperCase())) {
                                int count = objectList.indexOf(items);
                                objectsList.add(objects.get(count));
                                return true;
                            } else {
                                return false;
                            }
                        });
                        objects.removeAll(objects);
                        objects.addAll(objectsList);
                    }
                });
            });
            box.setItems(filtered);
            return objectsList;
        }
}