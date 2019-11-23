package cell;

public class TokenDoor extends Cell {
    private int tokenNumToUnlockDoor;

    public TokenDoor(int tokenNumToUnlockDoor) {
        super(false, true, false, "", Item.NONE);
        this.tokenNumToUnlockDoor = tokenNumToUnlockDoor;
    }

    @Override
    public boolean isPlayerMoved() {
    }
}
