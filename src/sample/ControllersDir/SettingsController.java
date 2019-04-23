package sample.ControllersDir;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import sample.FileDir.Keybind;
import sample.FileDir.SettingsFile;
import sample.Main;
import sample.NativeHookDir.NativeHook;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingsController implements EventHandler<ActionEvent>, Initializable {

    @FXML
    BorderPane mainBorderPane;
    @FXML
    Button cancelEditBtn;
    @FXML
    Label pickKeyInfo;
    @FXML
    Label openDoorsShortcut;
    @FXML
    Label rfidBlockShortcut;
    @FXML
    Label redColorShortcut;
    @FXML
    Label blueColorShortcut;
    @FXML
    Label greenColorShortcut;
    @FXML
    Button editOpenDoorsShortcutBtn;
    @FXML
    Button editRfidBlockShortcutBtn;
    @FXML
    Button editRedColorShortcutBtn;
    @FXML
    Button editBlueColorShortcutBtn;
    @FXML
    Button editGreenColorShortcutBtn;
    @FXML
    Button saveAllShortcutsBtn;
    @FXML
    Button editAllShortcutsBtn;
    @FXML
    Button deleteOpenDoorsShortcut;
    @FXML
    Button deleteRfidBlockShortcut;
    @FXML
    Button deleteRedColorShortcut;
    @FXML
    Button deleteBlueColorShortcut;
    @FXML
    Button deleteGreenColorShortcut;

    public void onEditButtonClick() {
        editOpenDoorsShortcutBtn.setVisible(true);
        editRfidBlockShortcutBtn.setVisible(true);
        editRedColorShortcutBtn.setVisible(true);
        editBlueColorShortcutBtn.setVisible(true);
        editGreenColorShortcutBtn.setVisible(true);
        saveAllShortcutsBtn.setVisible(true);
        editAllShortcutsBtn.setVisible(false);
        cancelEditBtn.setVisible(true);
        showDeleteButtons(true);
    }

    public void saveAllShortcuts() {
        showDeleteButtons(false);
        saveAllShortcutsBtn.setVisible(false);
        editAllShortcutsBtn.setVisible(true);
        editOpenDoorsShortcutBtn.setVisible(false);
        editRfidBlockShortcutBtn.setVisible(false);
        editRedColorShortcutBtn.setVisible(false);
        editBlueColorShortcutBtn.setVisible(false);
        editGreenColorShortcutBtn.setVisible(false);
        cancelEditBtn.setVisible(false);
    }


    @Override
    public void handle(ActionEvent event) {
        Object btn = event.getSource();
        if (btn == editOpenDoorsShortcutBtn) {
            System.out.println("I am clicked");
            NativeHook.setGetClickedKeyNow(true);
            NativeHook.setLabelToChange(openDoorsShortcut);
            setKeyInfoVisibility(true);
        } else if (btn == editRfidBlockShortcutBtn) {
            NativeHook.setGetClickedKeyNow(true);
            NativeHook.setLabelToChange(rfidBlockShortcut);
            setKeyInfoVisibility(true);
        } else if (btn == editRedColorShortcutBtn) {
            NativeHook.setGetClickedKeyNow(true);
            NativeHook.setLabelToChange(redColorShortcut);
            setKeyInfoVisibility(true);
        } else if (btn == editBlueColorShortcutBtn) {
            NativeHook.setGetClickedKeyNow(true);
            NativeHook.setLabelToChange(blueColorShortcut);
            setKeyInfoVisibility(true);
        } else if (btn == editGreenColorShortcutBtn) {
            NativeHook.setGetClickedKeyNow(true);
            NativeHook.setLabelToChange(greenColorShortcut);
            setKeyInfoVisibility(true);
        } else if (btn == editAllShortcutsBtn) {
            onEditButtonClick();
        } else if (btn == saveAllShortcutsBtn) {
            ArrayList<String> newShortcuts = new ArrayList<>();
            newShortcuts.add(openDoorsShortcut.getText());
            newShortcuts.add(rfidBlockShortcut.getText());
            newShortcuts.add(redColorShortcut.getText());
            newShortcuts.add(blueColorShortcut.getText());
            newShortcuts.add(greenColorShortcut.getText());
            if (checkIfShortcutsAreDifferent(newShortcuts)) {
                saveAllShortcuts();
                System.out.println("Im here");

                try {
                    SettingsFile.changeAllShortcuts(newShortcuts);
                    SettingsFile.setKeybinds(newShortcuts);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Dupplicates!");
            }
        } else if (btn == cancelEditBtn) {
            ArrayList<Keybind> keybinds = SettingsFile.getKeybinds();
            openDoorsShortcut.setText(keybinds.get(0).getKeybind());
            rfidBlockShortcut.setText(keybinds.get(1).getKeybind());
            redColorShortcut.setText(keybinds.get(2).getKeybind());
            blueColorShortcut.setText(keybinds.get(3).getKeybind());
            greenColorShortcut.setText(keybinds.get(4).getKeybind());
            saveAllShortcutsBtn.setVisible(false);
            editAllShortcutsBtn.setVisible(true);
            editOpenDoorsShortcutBtn.setVisible(false);
            editRfidBlockShortcutBtn.setVisible(false);
            editRedColorShortcutBtn.setVisible(false);
            editBlueColorShortcutBtn.setVisible(false);
            editGreenColorShortcutBtn.setVisible(false);
            cancelEditBtn.setVisible(false);
            showDeleteButtons(false);
        } else if (btn == deleteOpenDoorsShortcut) {
            openDoorsShortcut.setText("undefined");
        } else if (btn == deleteRfidBlockShortcut) {
            rfidBlockShortcut.setText("undefined");
        } else if (btn == deleteRedColorShortcut) {
            redColorShortcut.setText("undefined");
        } else if (btn == deleteBlueColorShortcut) {
            blueColorShortcut.setText("undefined");
        } else if (btn == deleteGreenColorShortcut) {
            greenColorShortcut.setText("undefined");
        }
    }

    private boolean checkIfShortcutsAreDifferent(ArrayList<String> checkDuplicates) {
        for (int i = 0; i < checkDuplicates.size(); i++) {
            for (int j = i + 1; j < checkDuplicates.size(); j++) {
                if (checkDuplicates.get(i).equals(checkDuplicates.get(j)) && (!checkDuplicates.get(i).equals("undefined") && !checkDuplicates.get(j).equals("undefined"))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showDeleteButtons(boolean show) {
        deleteOpenDoorsShortcut.setVisible(show);
        deleteRfidBlockShortcut.setVisible(show);
        deleteRedColorShortcut.setVisible(show);
        deleteGreenColorShortcut.setVisible(show);
        deleteBlueColorShortcut.setVisible(show);
    }


    public static boolean shortcutEdition() {
        return false;
    }


    private void setKeyInfoVisibility(Boolean show) {
        if (show) {
            pickKeyInfo.setVisible(true);
        } else {
            pickKeyInfo.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editOpenDoorsShortcutBtn.setOnAction(this);
        editRfidBlockShortcutBtn.setOnAction(this);
        editRedColorShortcutBtn.setOnAction(this);
        editBlueColorShortcutBtn.setOnAction(this);
        editGreenColorShortcutBtn.setOnAction(this);
        cancelEditBtn.setOnAction(this);
        editAllShortcutsBtn.setFocusTraversable(false);
        editOpenDoorsShortcutBtn.setFocusTraversable(false);
        editRfidBlockShortcutBtn.setFocusTraversable(false);
        editRedColorShortcutBtn.setFocusTraversable(false);
        editBlueColorShortcutBtn.setFocusTraversable(false);
        editGreenColorShortcutBtn.setFocusTraversable(false);
        deleteBlueColorShortcut.setFocusTraversable(false);
        deleteGreenColorShortcut.setFocusTraversable(false);
        deleteRedColorShortcut.setFocusTraversable(false);
        deleteRfidBlockShortcut.setFocusTraversable(false);
        deleteOpenDoorsShortcut.setFocusTraversable(false);
        saveAllShortcutsBtn.setFocusTraversable(false);
        cancelEditBtn.setFocusTraversable(false);
        openDoorsShortcut.setText(SettingsFile.getKeybinds().get(0).getKeybind());
        rfidBlockShortcut.setText(SettingsFile.getKeybinds().get(1).getKeybind());
        redColorShortcut.setText(SettingsFile.getKeybinds().get(2).getKeybind());
        blueColorShortcut.setText(SettingsFile.getKeybinds().get(3).getKeybind());
        greenColorShortcut.setText(SettingsFile.getKeybinds().get(4).getKeybind());
        Main.setSettingsController(this);
        NativeHook.setPickKeyInfo(pickKeyInfo);
    }
}
