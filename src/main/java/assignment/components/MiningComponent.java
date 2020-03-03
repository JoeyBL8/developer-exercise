package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GivePlayerItemEvent;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.events.PlayerMineOreEvent;
import net.gameslabs.model.Item;
import net.gameslabs.model.Ore;
import net.gameslabs.model.Skill;

public class MiningComponent extends Component {

    private static final Skill SKILL = Skill.MINING;

    @Override
    public void onLoad() {
        registerEvent(PlayerMineOreEvent.class, this::onPlayerMineOreEvent);
    }

    private void onPlayerMineOreEvent(PlayerMineOreEvent event) {
        if (hasLevelForOre(event.getOre(), event.getPlayer())) {
            GivePlayerItemEvent givePlayerItemEvent = new GivePlayerItemEvent(event.getPlayer(), new Item(event.getOre().getItem(), 1));
            send(givePlayerItemEvent);
            GiveXpEvent giveXpEvent = new GiveXpEvent(event.getPlayer(), Skill.MINING, event.getOre().getXp());
            send(giveXpEvent);
        } else {
            event.setCancelled(true);
        }
    }

    private boolean hasLevelForOre(Ore ore, Player player) {
        GetPlayerLevel playerLevel = new GetPlayerLevel(player, SKILL);
        send(playerLevel);
        return ore.getLevel() <= playerLevel.getLevel();
    }

    @Override
    public void onUnload() {

    }
}
