package models;

public enum GameState {

    NotYourTurn(false), YouCanPlay(true), YouArePlaying(true);

    private final boolean buttonsActivity;

    GameState(boolean buttonsActivity) {
        this.buttonsActivity = buttonsActivity;
    }

    public boolean getButtonsActivity() {
        return buttonsActivity;
    }
}
