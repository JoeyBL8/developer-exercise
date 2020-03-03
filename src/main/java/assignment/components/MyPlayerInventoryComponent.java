package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.GivePlayerItemEvent;
import net.gameslabs.events.PlayerHasItemEvent;
import net.gameslabs.events.PlayerRemoveItemEvent;
import net.gameslabs.model.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class MyPlayerInventoryComponent extends Component {

    private static Map<String, PlayerInventory> inventories = new HashMap<>();

    @Override
    public void onLoad() {
        registerEvent(GivePlayerItemEvent.class, this::onPlayerGiveItemEvent);
        registerEvent(PlayerHasItemEvent.class, this::onPlayerHasItemEvent);
        registerEvent(PlayerRemoveItemEvent.class, this::onPlayerRemoveItemEvent);
    }

    private void onPlayerGiveItemEvent(GivePlayerItemEvent event) {
        getPlayerInventory(event.getPlayer()).addItem(event.getItem());
    }

    private void onPlayerRemoveItemEvent(PlayerRemoveItemEvent event) {
        PlayerInventory playerInventory = getPlayerInventory(event.getPlayer());
        if (playerInventory.hasItem(event.getItem())) {
            event.setRemoved(playerInventory.removeItem(event.getItem()));
        }
    }

    private void onPlayerHasItemEvent(PlayerHasItemEvent event) {
        PlayerInventory playerInventory = getPlayerInventory(event.getPlayer());
        event.setHasItem(playerInventory.hasItem(event.getItem()));
    }

    @Override
    public void onUnload() {

    }

    private PlayerInventory getPlayerInventory(Player player) {
        if (!inventories.containsKey(player.getId())) {
            inventories.put(player.getId(), new PlayerInventory());
        }
        return inventories.get(player.getId());
    }
}
