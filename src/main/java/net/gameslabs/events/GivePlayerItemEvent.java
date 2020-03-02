package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.model.Item;

public class GivePlayerItemEvent extends PlayerItemEvent {

    public GivePlayerItemEvent(Player player, Item item) {
        super(player, item);
    }
}
