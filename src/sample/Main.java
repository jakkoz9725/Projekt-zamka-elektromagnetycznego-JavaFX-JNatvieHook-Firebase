package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.ControllersDir.MainWindowController;
import sample.ControllersDir.SettingsController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private boolean firstTime;
    private TrayIcon trayIcon;
    private static MainWindowController mainWindowController;
    private static SettingsController settingsController;

    public Main() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage widow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LayoutsDir/mainWindow.fxml"));
        Parent settingsWindowLayout = FXMLLoader.load(getClass().getResource("LayoutsDir/settingsWindow.fxml"));
        createTrayIcon(primaryStage);
        firstTime = true;
        Platform.setImplicitExit(false);
        primaryStage.setTitle("Hello World");
        Scene mainWindowScene = new Scene(root, 1000, 600);
        mainWindowScene.getStylesheets().add("Resources/style.css");
        primaryStage.setScene(mainWindowScene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(600);
        primaryStage.show();
        widow = new Stage();
        widow.initModality(Modality.APPLICATION_MODAL);
        widow.setTitle("TEST");
        widow.setMinWidth(250);
        Scene scene = new Scene(settingsWindowLayout);
        widow.setOnCloseRequest(e -> {
            updateArenaText();
            widow.close();
        });
        widow.setScene(scene);
    }

    public static boolean checkIfShortcutsTurnedOn() {
        return mainWindowController.checkIfShortcutsTurnedOn();
    }

    public static Stage ShowSettingsWindow() throws IOException {
        widow.showAndWait();
        return widow;
    }

    public static boolean checkIfSettingsWindowExists() {
        return widow.isShowing();
    }

    public static void updateShortcutsTextArea() {

    }

    private void createTrayIcon(final Stage stage) {
        if (SystemTray.isSupported()) {
            // get the SystemTray instance
            SystemTray tray = SystemTray.getSystemTray();
            // load an image
            java.awt.Image image = null;
            try {
                image = ImageIO.read(new File("C:\\Users\\Jakub\\Desktop\\iconSmallest.jpg"));
            } catch (IOException ex) {
                System.out.println(ex);
            }

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    if (mainWindowController.toggleWorkInBackground.isSelected()) {
                        hide(stage);
                    } else {
                        if (!mainWindowController.toggleWorkInBackground.isSelected()) {
                            System.exit(0);
                        }
                    }
                }
            });
            // create a action listener to listen for default action executed on the tray icon
            final ActionListener closeListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    System.exit(0);
                }
            };

            ActionListener showListener = new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stage.show();
                        }
                    });
                }
            };
            // create a popup menu
            PopupMenu popup = new PopupMenu();

            MenuItem showItem = new MenuItem("Show");
            showItem.addActionListener(showListener);
            popup.add(showItem);

            MenuItem closeItem = new MenuItem("Close");
            closeItem.addActionListener(closeListener);
            popup.add(closeItem);
            /// ... add other items
            // construct a TrayIcon
            trayIcon = new TrayIcon(image, "Open Your Doors", popup);
            // set the TrayIcon properties
            trayIcon.addActionListener(showListener);
            // ...
            // add the tray image
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            // ...
        }
    }

    public static void toggleRedColorBtn() {
        mainWindowController.toggleColorRedBtn();
    }

    public static void toggleBlueColorBtn() {
        mainWindowController.toggleColorBlueBtn();
    }

    public static void toggleGreenColorBtn() {
        mainWindowController.toggleColorGreenBtn();
    }

    public static void toggleRfidBlockBtn() {
        mainWindowController.toggleRfidBtn();
    }

    public static void setMainWindowController(MainWindowController mainWindowController) {
        Main.mainWindowController = mainWindowController;
    }

    public static void setSettingsController(SettingsController settingsController) {
        Main.settingsController = settingsController;
    }

    public void showProgramIsMinimizedMsg() {
        if (firstTime) {
            trayIcon.displayMessage("Some message.",
                    "Some other message.",
                    TrayIcon.MessageType.INFO);
            firstTime = false;
        }
    }

    public static void updateArenaText() {
        mainWindowController.setTextArea();
    }

    private void hide(final Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (SystemTray.isSupported()) {
                    stage.hide();
                    showProgramIsMinimizedMsg();
                } else {
                    System.exit(0);
                }
            }
        });
    }
}

