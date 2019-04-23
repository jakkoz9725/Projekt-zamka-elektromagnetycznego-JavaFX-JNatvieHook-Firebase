package sample.NativeHookDir;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import sample.ControllersDir.MainWindowController;
import sample.FileDir.SettingsFile;
import sample.Main;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NativeHook {
    private static MainWindowController mainWindowController;
    private static String clickedKey;
    private static boolean getClickedKeyNow;
    private static Label labelToChange;
    private static Label setKeyInfo;

    public NativeHook(MainWindowController mainWindowController) {
        NativeHook.mainWindowController = mainWindowController;
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        Handler[] handlers = Logger.getLogger("").getHandlers();
        for (int i = 0; i < handlers.length; i++) {
            handlers[i].setLevel(Level.OFF);
        }
        GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
    }

    private static void setClickedKey() {
        Platform.runLater(() -> {
            System.out.println(labelToChange.getText());
            labelToChange.setText(clickedKey);
            getClickedKeyNow = false;
            labelToChange = null;
            clickedKey = null;
            setKeyInfo.setVisible(false);
        });
    }

    public static void setPickKeyInfo(Label setKeyInfo) {
        NativeHook.setKeyInfo = setKeyInfo;
    }

    public static void setGetClickedKeyNow(boolean getClickedKeyNow) {
        NativeHook.getClickedKeyNow = getClickedKeyNow;
    }

    public static void setLabelToChange(Label labelToChange) {
        NativeHook.labelToChange = labelToChange;
    }

    public static class GlobalKeyListenerExample implements NativeKeyListener {


        @Override
        public void nativeKeyTyped(NativeKeyEvent e) {

        }

        @Override
        public void nativeKeyPressed(NativeKeyEvent e) {
            System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
            clickedKey = NativeKeyEvent.getKeyText(e.getKeyCode());
            if (getClickedKeyNow) {
                setClickedKey();
            } else {
                if (!Main.checkIfSettingsWindowExists() && Main.checkIfShortcutsTurnedOn()) {
                    System.out.println("Window not exists");
                    if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(SettingsFile.getKeybinds().get(0).getKeybind()) && !SettingsFile.getKeybinds().get(0).getKeybind().equals("undefined")) {
                        mainWindowController.openDoors();
                    } else if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(SettingsFile.getKeybinds().get(1).getKeybind()) && !SettingsFile.getKeybinds().get(1).getKeybind().equals("undefined")) {
                        Main.toggleRfidBlockBtn();
                        mainWindowController.toggleRfid();
                    } else if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(SettingsFile.getKeybinds().get(2).getKeybind()) && !SettingsFile.getKeybinds().get(2).getKeybind().equals("undefined")) {
                        Main.toggleRedColorBtn();
                        mainWindowController.updateDiodeColor();
                    } else if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(SettingsFile.getKeybinds().get(3).getKeybind()) && !SettingsFile.getKeybinds().get(2).getKeybind().equals("undefined")) {
                        Main.toggleBlueColorBtn();
                        mainWindowController.updateDiodeColor();
                    } else if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(SettingsFile.getKeybinds().get(4).getKeybind()) && !SettingsFile.getKeybinds().get(2).getKeybind().equals("undefined")) {
                        Main.toggleGreenColorBtn();
                        mainWindowController.updateDiodeColor();
                    }
                } else {
                    System.out.println("Window is showing");
                }
            }
        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent e) {

        }
    }


}