package com.ntu.api.controller.additional.admin;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ImageSchemViewerController {
    @FXML private AnchorPane schemeViewerPane;
    @FXML private ImageView scheme;

    public void exit(){
        Stage dlg = (Stage) schemeViewerPane.getScene().getWindow();
        dlg.close();
    }
}
