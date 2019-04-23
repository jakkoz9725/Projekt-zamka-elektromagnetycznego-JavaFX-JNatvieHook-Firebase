package sample.ControllersDir;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.FileDir.Keybind;
import sample.FileDir.SettingsFile;
import sample.FirebaseDir.FirebaseDatabase;
import sample.FirebaseDir.FirebaseGetRequest;
import sample.FirebaseDir.FirebaseUpdateRequest;
import sample.Main;
import sample.NativeHookDir.NativeHook;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    private String currentColor = "NONE";

    @FXML
    Slider workInBackgroundSlider;
    @FXML
    public
    ToggleButton toggleWorkInBackground;
    @FXML
    Slider shortcutsSlider;
    @FXML
    ToggleButton toggleShortcuts;
    @FXML
    TextArea shortcutsTextArea;
    @FXML
    Label currentColorLabel;
    @FXML
    Pane diodeColorPane;
    @FXML
    ToggleGroup diodeColor;
    @FXML
    ToggleButton setColorRedBtn;
    @FXML
    ToggleButton setColorBlueBtn;
    @FXML
    ToggleButton setColorGreenBtn;
    @FXML
    ToggleButton setColorMagentaBtn;
    @FXML
    ToggleButton setColorCyanBtn;
    @FXML
    ToggleButton setColorWhiteBtn;
    @FXML
    ToggleButton setColorYellowBtn;
    @FXML
    private Circle currentDiodeColorCircle;
    @FXML
    private Pane togglesPane;
    @FXML
    private Pane shortcutsPane;
    @FXML
    private Button openDoorsBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button diodeBtn;
    @FXML
    private Button connectionTest;
    @FXML
    private ToggleButton toggleRfid;
    @FXML
    private Slider sliderRfid;

    private DiodeController diodeController = new DiodeController();
    private static FirebaseGetRequest firebaseGetRequest = new FirebaseGetRequest();
    private FirebaseUpdateRequest firebaseUpdateRequest = new FirebaseUpdateRequest();
    private FirebaseDatabase firebaseDatabase = new FirebaseDatabase();

    public void toggleRfid() {
        if (toggleRfid.isSelected()) {
            sliderRfid.setValue(1);
        } else {
            sliderRfid.setValue(0);
        }
        firebaseDatabase.setCardsBlocked((int) sliderRfid.getValue());
        tryToUpdate(firebaseDatabase);
    }

    public void toggleWorkInBackground() {
        if (toggleWorkInBackground.isSelected()) {
            workInBackgroundSlider.setValue(1);
        } else {
            workInBackgroundSlider.setValue(0);
        }
    }

    public void tryConnection() {
        boolean connectionSuccesful = false;
        try {
            firebaseDatabase = (FirebaseDatabase) firebaseGetRequest.updatedData();
            System.out.println(firebaseDatabase.toString());
            connectionSuccesful = true;
        } catch (Exception e) {
            System.out.println("Check your internet connection or firebase settings");
            firebaseGetRequest.getDatabaseHere(this);
            connectionSuccesful = false;
        } finally {
            if (connectionSuccesful) {
                System.out.println("Show UI");
                showUI();
            } else {
                System.out.println("Still block UI");
            }
        }
    }

    public void openDoors() {
        firebaseDatabase.setOpenByApp(1);
        tryToUpdate(firebaseDatabase);
        firebaseDatabase.setOpenByApp(0);
    }

    private void tryToUpdate(FirebaseDatabase firebaseDatabase) {
        firebaseUpdateRequest.updateFirebaseDatabase(firebaseDatabase);
    }

    public void updateDiodeColor() {
        Platform.runLater(() -> {
            firebaseDatabase = diodeController.updateDiodeColor(firebaseDatabase, currentColorLabel
                    , setColorRedBtn, setColorBlueBtn, setColorGreenBtn, setColorMagentaBtn
                    , setColorCyanBtn, setColorWhiteBtn, setColorYellowBtn);
            tryToUpdate(firebaseDatabase);
        });
    }

    public void TurnOffDiode() {
        diodeController.TurnOffDiode(firebaseDatabase, currentColorLabel, diodeColor);
        tryToUpdate(firebaseDatabase);
    }

    public void updateUI(FirebaseDatabase firebaseDatabase) {
        System.out.println("WORKS");
        diodeController.updateDiode(firebaseDatabase, currentDiodeColorCircle, setColorRedBtn
                , setColorBlueBtn, setColorGreenBtn, setColorMagentaBtn, setColorCyanBtn
                , setColorWhiteBtn, setColorYellowBtn, currentColorLabel);
    }

    public void turnOnShortcuts() {
        if (toggleShortcuts.isSelected()) {
            shortcutsSlider.setValue(1);
        } else {
            shortcutsSlider.setValue(0);
        }
    }

    private void showUI() {
        shortcutsPane.setVisible(true);
        togglesPane.setVisible(true);
        openDoorsBtn.setDisable(false);
        settingsBtn.setDisable(false);
        diodeBtn.setDisable(false);
        connectionTest.setDisable(true);
    }

    public void setTextArea() {
        String textAreaString = "";
        ArrayList<Keybind> keybinds;
        keybinds = SettingsFile.getKeybinds();
        if (!keybinds.get(0).getKeybind().equals("undefined")) {
            textAreaString += "Open doors shortcut = " + keybinds.get(0).getKeybind() + "\n";
        }
        if (!keybinds.get(1).getKeybind().equals("undefined")) {
            textAreaString += "Block RFID cards = " + keybinds.get(1).getKeybind() + "\n";
        }
        if (!keybinds.get(2).getKeybind().equals("undefined")) {
            textAreaString += "Diode RED color = " + keybinds.get(2).getKeybind() + "\n";
        }
        if (!keybinds.get(3).getKeybind().equals("undefined")) {
            textAreaString += "Diode BLUE color = " + keybinds.get(3).getKeybind() + "\n";
        }
        if (!keybinds.get(4).getKeybind().equals("undefined")) {
            textAreaString += "Diode GREEN color = " + keybinds.get(4).getKeybind();
        }
        shortcutsTextArea.setText(textAreaString);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shortcutsPane.setVisible(false);
        togglesPane.setVisible(false);
        openDoorsBtn.setDisable(true);
        settingsBtn.setDisable(true);
        diodeBtn.setDisable(true);
        shortcutsPane.setFocusTraversable(false);
        toggleRfid.setFocusTraversable(false);
        toggleShortcuts.setFocusTraversable(false);
        togglesPane.setFocusTraversable(false);
        connectionTest.setFocusTraversable(false);
        setColorRedBtn.setFocusTraversable(false);
        setColorBlueBtn.setFocusTraversable(false);
        toggleWorkInBackground.setFocusTraversable(false);
        setColorCyanBtn.setFocusTraversable(false);
        NativeHook nativeHook = new NativeHook(this);
        firebaseGetRequest.getDatabaseHere(this);
        SettingsFile settingsFile = new SettingsFile();
        Main.setMainWindowController(this);
        Main.updateArenaText();
    }

    public void toggleColorRedBtn() {
        setColorRedBtn.setSelected(true);
    }

    public void toggleColorBlueBtn() {
        setColorBlueBtn.setSelected(true);
    }

    public void toggleColorGreenBtn() {
        setColorGreenBtn.setSelected(true);
    }

    public boolean checkIfShortcutsTurnedOn() {
        return toggleShortcuts.isSelected();
    }

    public void toggleRfidBtn() {
        if (!toggleRfid.isSelected()) {
            toggleRfid.setSelected(true);
        } else {
            toggleRfid.setSelected(false);
        }
    }

    public void showOtherWindow() throws IOException {
        Stage menuWindow = Main.ShowSettingsWindow();

    }

    public void onDiodeMenuButtonClick() {
        if (!diodeColorPane.isVisible()) {
            diodeColorPane.setVisible(true);
            currentColorLabel.setText(currentColor);
        } else
            diodeColorPane.setVisible(false);
    }

}
