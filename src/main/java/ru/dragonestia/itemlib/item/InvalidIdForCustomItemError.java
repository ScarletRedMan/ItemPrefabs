package ru.dragonestia.itemlib.item;

import ru.dragonestia.itemlib.ItemPrefabs;

public class InvalidIdForCustomItemError extends Error {

    @Override
    public String getMessage() {
        return "Custom Item`s id must not be over or equal " + ItemPrefabs.BORDER;
    }

}
