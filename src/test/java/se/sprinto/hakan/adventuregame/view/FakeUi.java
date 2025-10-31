package se.sprinto.hakan.adventuregame.view;

import se.sprinto.hakan.adventuregame.view.UI;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FakeUi implements UI {
    private final Queue<String> inputs = new LinkedList<>();
    private String input;
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput(String prompt) {
        return inputs.poll();
    }

    public void setInput(String input) {
        inputs.clear();
        inputs.add(input);
    }

    public void setMultipleInputs(String ... multipleInputs) {
        inputs.clear();
        inputs.addAll(List.of(multipleInputs));
    }
}
