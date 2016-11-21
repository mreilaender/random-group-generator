package com.accenture.mreilaender.view;

import com.accenture.mreilaender.model.GroupViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author manuel
 * @version 11/21/16
 */
public class GroupView implements FxmlView<GroupViewModel>, Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TabPane tabPane;

    @InjectViewModel
    private GroupViewModel groupViewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
