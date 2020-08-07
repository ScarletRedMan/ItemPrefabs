package ru.dragonestia.itemlib.item;

public class InvalidIdForCustomItemError extends Error {

    @Override
    public String getMessage() {
        return "Custom Item`s id must not be over or equal zero";
    }

}
