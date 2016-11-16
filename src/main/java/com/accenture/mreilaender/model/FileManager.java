package com.accenture.mreilaender.model;

import javafx.scene.Scene;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * @author manuel
 * @version 11/16/16
 */
public class FileManager {

    public static File chooseFile(Scene scene) {
        return new FileChooser().showOpenDialog(scene.getWindow());
    }
}
