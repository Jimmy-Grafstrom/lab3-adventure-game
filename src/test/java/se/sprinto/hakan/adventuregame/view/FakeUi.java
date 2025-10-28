package se.sprinto.hakan.adventuregame.view;

import se.sprinto.hakan.adventuregame.view.UI;

public class FakeUi implements UI {
    private String input;
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput(String prompt) {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
