package sample.ControllersDir;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.FirebaseDir.FirebaseDatabase;

class DiodeController {


    void updateDiode(FirebaseDatabase firebaseDatabase, Circle currentDiodeColorCircle, ToggleButton setColorRedBtn,
                     ToggleButton setColorBlueBtn, ToggleButton setColorGreenBtn, ToggleButton setColorMagentaBtn,
                     ToggleButton setColorCyanBtn, ToggleButton setColorWhiteBtn, ToggleButton setColorYellowBtn,
                     Label currentColor
    ) {
        int diodeRedColor = firebaseDatabase.getColorRed();
        int diodeBlueColor = firebaseDatabase.getColorBlue();
        int diodeGreenColor = firebaseDatabase.getColorGreen();
        System.out.println(diodeBlueColor + "," + diodeRedColor + "," + diodeRedColor);
        if (diodeRedColor == 1 && (diodeBlueColor == 0 && diodeGreenColor == 0)) {
            currentDiodeColorCircle.setFill(Color.RED);
            setColorRedBtn.setSelected(true);
            currentColor.setTextFill(Color.RED);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentColor.setTextFill(Color.RED);
                    currentColor.setText("RED");
                }
            });
        } else if (diodeBlueColor == 1 && (diodeRedColor == 0 && diodeGreenColor == 0)) {
            currentDiodeColorCircle.setFill(Color.BLUE);
            setColorBlueBtn.setSelected(true);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentColor.setTextFill(Color.BLUE);
                    currentColor.setText("BLUE");
                }
            });
        } else if (diodeGreenColor == 1 && (diodeRedColor == 0 && diodeBlueColor == 0)) {
            currentDiodeColorCircle.setFill(Color.GREEN);
            setColorGreenBtn.setSelected(true);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentColor.setTextFill(Color.GREEN);
                    currentColor.setText("GREEN");
                }
            });
        } else if (diodeRedColor == 1 && diodeBlueColor == 1 && diodeGreenColor == 0) {
            currentDiodeColorCircle.setFill(Color.MAGENTA);
            setColorMagentaBtn.setSelected(true);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentColor.setTextFill(Color.MAGENTA);
                    currentColor.setText("MAGENTA");
                }
            });
        } else if (diodeRedColor == 1 && diodeGreenColor == 1 && diodeBlueColor == 0) {
            currentDiodeColorCircle.setFill(Color.YELLOW);
            setColorYellowBtn.setSelected(true);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentColor.setTextFill(Color.YELLOW);
                    currentColor.setText("YELLOW");
                }
            });
        } else if (diodeBlueColor == 1 && diodeGreenColor == 1 && diodeRedColor == 0) {
            currentDiodeColorCircle.setFill(Color.CYAN);
            setColorCyanBtn.setSelected(true);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentColor.setTextFill(Color.CYAN);
                    currentColor.setText("CYAN");
                }
            });
        } else if (diodeBlueColor == 1 && diodeGreenColor == 1 && diodeRedColor == 1) {
            currentDiodeColorCircle.setFill(Color.GRAY);
            setColorWhiteBtn.setSelected(true);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentColor.setTextFill(Color.GRAY);
                    currentColor.setText("WHITE");
                }
            });
        } else {
            currentDiodeColorCircle.setFill(Color.WHITE);
            setColorBlueBtn.setSelected(false);
            setColorRedBtn.setSelected(false);
            setColorGreenBtn.setSelected(false);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    currentColor.setTextFill(Color.GRAY);
                    currentColor.setText("NONE");
                }
            });
        }
    }

    FirebaseDatabase updateDiodeColor(FirebaseDatabase firebaseDatabase, Label currentColorLabel, ToggleButton setColorRedBtn,
                                      ToggleButton setColorBlueBtn, ToggleButton setColorGreenBtn, ToggleButton setColorMagentaBtn,
                                      ToggleButton setColorCyanBtn, ToggleButton setColorWhiteBtn, ToggleButton setColorYellowBtn) {
        if (setColorRedBtn.isSelected()) {
            firebaseDatabase.setColorRed(1);
            firebaseDatabase.setColorBlue(0);
            firebaseDatabase.setColorGreen(0);
            currentColorLabel.setText("RED");
        } else if (setColorGreenBtn.isSelected()) {
            firebaseDatabase.setColorRed(0);
            firebaseDatabase.setColorBlue(0);
            firebaseDatabase.setColorGreen(1);
            currentColorLabel.setText("GREEN");
        } else if (setColorBlueBtn.isSelected()) {
            firebaseDatabase.setColorRed(0);
            firebaseDatabase.setColorBlue(1);
            firebaseDatabase.setColorGreen(0);
            currentColorLabel.setText("BLUE");
        } else if (setColorCyanBtn.isSelected()) {
            firebaseDatabase.setColorBlue(1);
            firebaseDatabase.setColorGreen(1);
            firebaseDatabase.setColorRed(0);
            currentColorLabel.setText("CYAN");
        } else if (setColorMagentaBtn.isSelected()) {
            firebaseDatabase.setColorRed(1);
            firebaseDatabase.setColorBlue(1);
            firebaseDatabase.setColorGreen(0);
            currentColorLabel.setText("MAGENTA");
        } else if (setColorYellowBtn.isSelected()) {
            firebaseDatabase.setColorGreen(1);
            firebaseDatabase.setColorRed(1);
            firebaseDatabase.setColorBlue(0);
            currentColorLabel.setText("YELLOW");
        } else if (setColorWhiteBtn.isSelected()) {
            firebaseDatabase.setColorBlue(1);
            firebaseDatabase.setColorRed(1);
            firebaseDatabase.setColorGreen(1);
            currentColorLabel.setText("WHITE");
        } else {
            firebaseDatabase.setColorRed(0);
            firebaseDatabase.setColorBlue(0);
            firebaseDatabase.setColorGreen(0);
            currentColorLabel.setText("NONE");
        }
        return firebaseDatabase;
    }

    FirebaseDatabase TurnOffDiode(FirebaseDatabase firebaseDatabase, Label currentColorLabel, ToggleGroup diodeColor) {
        firebaseDatabase.setColorRed(0);
        firebaseDatabase.setColorBlue(0);
        firebaseDatabase.setColorGreen(0);
        currentColorLabel.setText("NONE");
        diodeColor.getSelectedToggle().setSelected(false);
        return firebaseDatabase;
    }
}
