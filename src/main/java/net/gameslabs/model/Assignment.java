package net.gameslabs.model;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.*;
import net.gameslabs.implem.PlayerImplem;

import java.util.Arrays;

public class Assignment {

    protected final ComponentRegistry registry;
    private final Player mainPlayer;
    private final String COINS = "misc.coins";

    public Assignment(Component ... myComponentsToAdd) {
        registry = new ComponentRegistry();
        Arrays.asList(myComponentsToAdd).forEach(registry::registerComponent);
        registry.registerComponent(new ChartComponent());
        registry.load();
        mainPlayer = PlayerImplem.newPlayer("MyPlayer");
    }

    public final void run() {
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.CONSTRUCTION, 25));
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.EXPLORATION, 25));
        registry.sendEvent(new GivePlayerItemEvent(mainPlayer, new Item(COINS, 100)));
        registry.sendEvent(new PlayerRemoveItemEvent(mainPlayer, new Item(COINS,  50)));
        registry.sendEvent(new PlayerMineOreEvent(mainPlayer, Ore.TIN));
        registry.sendEvent(new PlayerMineOreEvent(mainPlayer, Ore.COAL));
        registry.sendEvent(new PlayerMineOreEvent(mainPlayer, Ore.IRON));
        registry.sendEvent(new PlayerMineOreEvent(mainPlayer, Ore.COAL));
        GetPlayerLevel getPlayerLevel = new GetPlayerLevel(mainPlayer, Skill.CONSTRUCTION);
        log("Player level", mainPlayer, getPlayerLevel.getLevel());
        runChecks();
        registry.unload();
    }

    private void runChecks() {
        if (getLevel(Skill.EXPLORATION) != 1) throw new AssignmentFailed("Exploration XP should be set to level 1");
        if (getLevel(Skill.CONSTRUCTION) != 2) throw new AssignmentFailed("Construction XP should be set to level 2");
        checkPlayerHasItem(mainPlayer, new Item(COINS, 50));
        checkPlayerHasItem(mainPlayer, new Item(Ore.TIN.getItem(), 1));
        checkPlayerHasItem(mainPlayer, new Item(Ore.IRON.getItem(), 1));
        checkPlayerHasItem(mainPlayer, new Item(Ore.COAL.getItem(), 1));
        PlayerHasItemEvent event = new PlayerHasItemEvent(mainPlayer, new Item(Ore.COAL.getItem(), 2));
        registry.sendEvent(event);
        if (event.hasItem()) throw new AssignmentFailed("Player should not have more than 1 coal");
    }

    private void checkPlayerHasItem(Player player, Item item) {
        PlayerHasItemEvent hasItemEvent = new PlayerHasItemEvent(player, item);
        registry.sendEvent(hasItemEvent);
        if (!hasItemEvent.hasItem()) throw new AssignmentFailed("Player should have " + item.getAmount() + " " + item.getId());
    }

    private int getLevel(Skill skill) {
        GetPlayerLevel getPlayerLevel = new GetPlayerLevel(mainPlayer, skill);
        registry.sendEvent(getPlayerLevel);
        return getPlayerLevel.getLevel();
    }

    public void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }
}
