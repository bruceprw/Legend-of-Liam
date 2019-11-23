package cell;

public class TokenDoor extends Cell {
    private int tokenNumToUnlockDoor;
    private String path = "Images\\token_door.jpg";

    public TokenDoor(int tokenNumToUnlockDoor) {
        super(false, true, false, "", Item.NONE);
        this.tokenNumToUnlockDoor = tokenNumToUnlockDoor;
    }

    @Override
    public boolean isPlayerMoved() {
    }

	public String getTokenNum()
	{
		
		return tokenNumToUnlockDoor;
	}
}
