package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Ore;

public class PlayerMineOreEvent extends PlayerEvent {

    private Ore ore;

    public PlayerMineOreEvent(Player player, Ore ore) {
        super(player);
        this.ore = ore;
    }

    public Ore getOre() {
        return ore;
    }

    public void setOre(Ore ore) {
        this.ore = ore;
    }
}
