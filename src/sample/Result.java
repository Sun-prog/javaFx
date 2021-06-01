package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Result {
    private SimpleIntegerProperty fish   = new SimpleIntegerProperty();
    private SimpleIntegerProperty bulls  = new SimpleIntegerProperty();
    private SimpleIntegerProperty cows   = new SimpleIntegerProperty();
    private SimpleStringProperty  carrot = new SimpleStringProperty();

    public int getFish() {
        return fish.get();
    }

    public SimpleIntegerProperty fishProperty() {
        return fish;
    }

    public void setFish(int fish) {
        this.fish.set(fish);
    }

    public int getBulls() {
        return bulls.get();
    }

    public SimpleIntegerProperty bullsProperty() {
        return bulls;
    }

    public void setBulls(int bulls) {
        this.bulls.set(bulls);
    }

    public int getCows() {
        return cows.get();
    }

    public SimpleIntegerProperty cowsProperty() {
        return cows;
    }

    public void setCows(int cows) {
        this.cows.set(cows);
    }

    public String getCarrot() {
        return carrot.get();
    }

    public SimpleStringProperty carrotProperty() {
        return carrot;
    }

    public void setCarrot(String carrot) {
        this.carrot.set(carrot);
    }
}
