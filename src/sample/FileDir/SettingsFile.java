package sample.FileDir;

import java.io.*;
import java.util.ArrayList;

public class SettingsFile {

    private Keybind openByApp = new Keybind("[openByApp] =");
    private Keybind cardsBlocked = new Keybind("[cardsBlocked] =");
    private Keybind colorRed = new Keybind("[colorRed] =");
    private Keybind colorGreen = new Keybind("[colorGreen] =");
    private Keybind colorBlue = new Keybind("[colorBlue] =");

    private static ArrayList<Keybind> keybinds;


    public SettingsFile() {
        keybinds = new ArrayList<>();
        keybinds.add(openByApp);
        keybinds.add(cardsBlocked);
        keybinds.add(colorRed);
        keybinds.add(colorBlue);
        keybinds.add(colorGreen);
        if (createFile()) {
            readFile();
        }


    }

    private boolean createFile() {
        File file = new File("C:\\Users\\Jakub\\Desktop\\Program\\settings.txt");
        try {
            if (file.createNewFile()) {
                try {
                    FileWriter writer = new FileWriter("C:\\Users\\Jakub\\Desktop\\Program\\settings.txt", true);
                    writer.write("[openByApp] = undefined");
                    writer.write("\r\n");   // write new line
                    writer.write("[cardsBlocked] = undefined");
                    writer.write("\r\n");   // write new line
                    writer.write("[colorRed] = undefined");
                    writer.write("\r\n");   // write new line
                    writer.write("[colorBlue] = undefined");
                    writer.write("\r\n");   // write new line
                    writer.write("[colorGreen] = undefined");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //file probb exists
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.exists();
    }


    public void changeCurrentShortcut(Keybind keybind, String shortcut) throws IOException {
        try {

            BufferedReader file = new BufferedReader(new FileReader("C:\\Users\\Jakub\\Desktop\\Program\\settings.txt"));
            String line;
            StringBuffer stringBuffer = new StringBuffer();

            while ((line = file.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\r\n");
            }
            String inputStr = stringBuffer.toString();
            file.close();

            System.out.println(inputStr); // check that it's inputted right

            inputStr = inputStr.replace(keybind.getKeybindExample() + " " + keybind.getKeybind(), keybind.getKeybindExample() + " " + shortcut);
            System.out.println(keybind.getKeybindExample());
            System.out.println(keybind.getKeybind());

            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Jakub\\Desktop\\Program\\settings.txt");
            fileOut.write(inputStr.getBytes());
            System.out.println(inputStr);
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }


    public static void changeAllShortcuts(ArrayList<String> newKeybind) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader("C:\\Users\\Jakub\\Desktop\\Program\\settings.txt"));
        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while ((line = file.readLine()) != null) {
            stringBuffer.append(line);
            stringBuffer.append("\r\n");
        }
        String inputStr = stringBuffer.toString();
        file.close();
        System.out.println("im here");
        System.out.println(inputStr);
        for (int i = 0; i < keybinds.size(); i++) {
            inputStr = inputStr.replace(keybinds.get(i).getKeybindExample() + " " + keybinds.get(i).getKeybind(), keybinds.get(i).getKeybindExample() + " " + newKeybind.get(i));
        }

        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Jakub\\Desktop\\Program\\settings.txt");
        fileOut.write(inputStr.getBytes());
        System.out.println(inputStr);
        fileOut.close();
    }


    private void readFile() {
        FileReader reader = null;
        try {
            reader = new FileReader("C:\\Users\\Jakub\\Desktop\\Program\\settings.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        try {
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(keybinds.get(i).getKeybindExample())) {
                    keybinds.get(i).setKeybind(line.substring(keybinds.get(i).getKeybindExample().lastIndexOf("=") + 1));
                    //String keyBint = line.substring(example.lastIndexOf("=") + 1);
                    keybinds.get(i).setKeybind(keybinds.get(i).getKeybind().replaceAll(" ", ""));
                    //   System.out.println(keybinds.get(i).getKeybindExample() + " " + keybinds.get(i).getKeybind());
                    //  System.out.println(keybinds.get(i).getKeybind());
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
//        }
        }
    }

    public Keybind getOpenByApp() {
        return openByApp;
    }

    public Keybind getCardsBlocked() {
        return cardsBlocked;
    }

    public Keybind getColorRed() {
        return colorRed;
    }

    public Keybind getColorGreen() {
        return colorGreen;
    }

    public Keybind getColorBlue() {
        return colorBlue;
    }

    public static ArrayList<Keybind> getKeybinds() {
        return keybinds;
    }

    public static void setKeybinds(ArrayList<String> newKeybinds) {
        keybinds.get(0).setKeybind(newKeybinds.get(0));
        keybinds.get(1).setKeybind(newKeybinds.get(1));
        keybinds.get(2).setKeybind(newKeybinds.get(2));
        keybinds.get(3).setKeybind(newKeybinds.get(3));
        keybinds.get(4).setKeybind(newKeybinds.get(4));
    }
}
