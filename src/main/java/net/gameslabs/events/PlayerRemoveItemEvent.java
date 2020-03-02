package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.model.Item;

public class PlayerRemoveItemEvent extends PlayerItemEvent {

    private boolean removed;

    public PlayerRemoveItemEvent(Player player, Item item) {
        super(player, item);
        this.removed = false;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}
