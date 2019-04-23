package sample.FileDir;

public class Keybind {

    Keybind(String keybindExample) {
        this.keybindExample = keybindExample;
    }

    private String keybind;

    private String keybindExample;

    public String getKeybind() {
        return keybind;
    }

    void setKeybind(String keybind) {
        this.keybind = keybind;
    }

    String getKeybindExample() {
        return keybindExample;
    }

    public void setKeybindExample(String keybindExample) {
        this.keybindExample = keybindExample;
    }
}
