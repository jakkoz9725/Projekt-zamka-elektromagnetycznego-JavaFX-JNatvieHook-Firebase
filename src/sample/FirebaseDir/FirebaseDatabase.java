package sample.FirebaseDir;

public class FirebaseDatabase {

    private int cardsBlocked;

    private int openByApp;

    private int openByCard;

    private int colorBlue;

    private int colorRed;

    private int colorGreen;



    private int getCardsBlocked() {
        return cardsBlocked;
    }

    public void setCardsBlocked(int cardsBlocked) {
        this.cardsBlocked = cardsBlocked;
    }

    private int getOpenByApp() {
        return openByApp;
    }

    public void setOpenByApp(int openByApp) {
        this.openByApp = openByApp;
    }

    private int getOpenByCard() {
        return openByCard;
    }

    public void setOpenByCard(int openByCard) {
        this.openByCard = openByCard;
    }

    public int getColorBlue() {
        return colorBlue;
    }

    public void setColorBlue(int colorBlue) {
        this.colorBlue = colorBlue;
    }

    public int getColorRed() {
        return colorRed;
    }

    public void setColorRed(int colorRed) {
        this.colorRed = colorRed;
    }

    public int getColorGreen() {
        return colorGreen;
    }

    public void setColorGreen(int colorGreen) {
        this.colorGreen = colorGreen;
    }


    @Override
    public String toString() {
        return "FirebaseDatabase{" +
                "cardsBlocked=" + cardsBlocked + "\n" +
                "openByApp=" + openByApp + "\n" +
                "penByCard=" + openByCard + "\n" +
                "colorBlue=" + colorBlue + "\n" +
                "colorRed=" + colorRed + "\n" +
                "colorGreen=" + colorGreen + "\n";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj == this || !(obj instanceof FirebaseDatabase))
            return false;

        FirebaseDatabase firebaseDatabase = (FirebaseDatabase) obj;
        if (firebaseDatabase.getColorGreen() != this.colorGreen)
        {  return false;}
        if (firebaseDatabase.getColorRed() != this.colorRed)
        {  return false;}
        if (firebaseDatabase.getColorBlue() != this.colorBlue)
        { return false;}
        if (firebaseDatabase.getCardsBlocked() != this.cardsBlocked)
        {return false;}
        if (firebaseDatabase.getOpenByApp() != this.openByApp)
        {return false;}
        if(firebaseDatabase.getOpenByCard() != this.openByCard)
        { return false;}

        return true;
    }

}
