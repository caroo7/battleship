package models;

public final class UserBoardMessage {

    // isAvailableInfo
    boolean isGameAvailable;

    // isYourTurnInfo
    boolean isYourTurn;

    // actual board states info
    // probably Map <Point, BoardElementState>

    public UserBoardMessage(boolean isGameAvailable, boolean isYourTurn /* Map <Point, BoardElementState*/) {
        this.isGameAvailable = isGameAvailable;
        this.isYourTurn = isYourTurn;
    }

    public boolean isGameAvailable() {
        return isGameAvailable;
    }

    public boolean isYourTurn() {
        return isYourTurn;
    }
}