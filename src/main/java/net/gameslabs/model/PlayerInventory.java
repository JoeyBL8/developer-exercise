package net.gameslabs.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlayerInventory {

    private List<Item> items;

    public PlayerInventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        Optional<Item> foundItem = getItem(item.getId());
        if (foundItem.isPresent()) {
            Item actualItem = foundItem.get();
            actualItem.setAmount(actualItem.getAmount() + item.getAmount());
        } else {
            items.add(new Item(item.getId(), item.getAmount()));
        }
    }

    private Optional<Item> getItem(String id) {
        return this.items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public boolean hasItem(Item item) {
        return this.items.stream().anyMatch(i -> item.getAmount() <= i.getAmount() && i.getId().equals(item.getId()));
    }

    public boolean removeItem(Item item) {
        if (items.removeIf((i) -> i.equals(item))) {
            return true;
        }
        Optional<Item> foundItem = getItem(item.getId());
        if (foundItem.isPresent()) {
            Item actual = foundItem.get();
            if (actual.getAmount() > item.getAmount()) {
                actual.setAmount(actual.getAmount() - item.getAmount());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
