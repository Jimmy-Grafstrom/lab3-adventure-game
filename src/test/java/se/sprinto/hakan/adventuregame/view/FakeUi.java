package se.sprinto.hakan.adventuregame.view;

public class FakeUi implements UI {
    private String input;
    @Override
    public void showMessage(String message) {
//        Kan vara tom, behövs inte.
    }

    @Override
    public String getInput(String prompt) {
        return this.input;
    } // Returnerar fake-inmatningen

    public void setInput(String input) { // Simulerar fake-inmatning
        this.input = input;
    }
}