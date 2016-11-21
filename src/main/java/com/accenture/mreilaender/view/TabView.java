package com.accenture.mreilaender.view;

import com.accenture.mreilaender.entities.Person;
import com.accenture.mreilaender.model.TabViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author manuel
 * @version 11/15/16
 */
public class TabView implements FxmlView<TabViewModel>, Initializable {
    public static final URL FXML_RESOURCE = TabView.class.getClassLoader().getResource("com/accenture/mreilaender/view/TabView.fxml");
    //final static Logger LOGGER = LogManager.getLogger(TabView.class);
    private static Scene scene;

    @InjectViewModel
    private TabViewModel tabViewModel;

    @FXML
    private TextField groupSize;

    @FXML
    private TextField groupOutput;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private Button generateRandomGroup;

    @FXML
    private Label textFieldLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get TabPane from MainView
        generateRandomGroup.disableProperty().bind(tabViewModel.generateButtonDisabledProperty());
    }

    @FXML
    public void generateRandomGroup() {
        /*
        groupOutput.clear();
        ArrayList<Person> persons = new ArrayList<>();
        try {
            PersonTableModel personTableModel = TabHandler.getPersonTableModel(tabPane.getSelectionModel().getSelectedIndex());
            int numberRandom = Integer.parseInt(groupSize.getText());
            for (; numberRandom != 0; --numberRandom)
                persons.add(personTableModel.getRandom());
        } catch (NoDataLoadedException e) {
            //LOGGER.error(e);
            DialogManager.showExceptionDialog(e).show();
        } catch (NumberFormatException e) {
            IllegalArgumentException tmp = new IllegalArgumentException("Invalid group size!");
            //LOGGER.error(tmp);
            DialogManager.showExceptionDialog(tmp).show();
        }
        for (Person person:persons)
            groupOutput.setText(groupOutput.getText() + person.getFirstName() + ", ");
           */
    }

    public TableView<Person> getTableView() {
        return tableView;
    }

    @FXML
    protected void groupSizeChanged() {
        tabViewModel.setGroupSize(groupSize.getText());
    }

}
