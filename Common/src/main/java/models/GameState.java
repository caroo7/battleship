package models;

public enum GameState {
    NotYourTurn(false), YouCanPlay(false), YouArePlaying(true);

    private boolean buttonsActivity;

    GameState(boolean buttonsActivity) {
        this.buttonsActivity = buttonsActivity;
    }

    public boolean getButtonsActivity() {
        return buttonsActivity;
    }
}
