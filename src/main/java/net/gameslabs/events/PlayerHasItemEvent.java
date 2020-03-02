package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.model.Item;

public class PlayerHasItemEvent extends PlayerItemEvent {

    private boolean hasItem;

    public PlayerHasItemEvent(Player player, Item item) {
        super(player, item);
    }

    public boolean hasItem() {
        return hasItem;
    }

    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }
}
