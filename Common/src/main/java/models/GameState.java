package models;

public enum GameState {
    NotYourTurn(false), YourTurn(false), Playing(true);

    private boolean buttonsActivity;

    GameState(boolean buttonsActivity) {
        this.buttonsActivity = buttonsActivity;
    }

    public boolean getButtonsActivity() {
        return buttonsActivity;
    }
}
