package models.interfaces;

import java.util.UUID;

public interface ITransferable {
    public void transferToAnotherClub(UUID oldClubId, UUID newClubId);
}
