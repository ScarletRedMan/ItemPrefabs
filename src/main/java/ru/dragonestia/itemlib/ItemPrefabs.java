package ru.dragonestia.itemlib;

import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.plugin.PluginBase;
import ru.dragonestia.itemlib.command.GiveCommand;
import ru.dragonestia.itemlib.item.PrefabManager;

public class ItemPrefabs extends PluginBase {

    private final PrefabManager prefabManager = new PrefabManager(this);

    private static ItemPrefabs instance;

    @Override
    public void onLoad() {
        instance = this;

        prefabManager.initConfig();
    }

    @Override
    public void onEnable() {
        SimpleCommandMap commandMap = getServer().getCommandMap();
        commandMap.getCommand("give").unregister(commandMap);

        commandMap.register("", new GiveCommand());

        prefabManager.loadItemsFromConfig();
    }

    @Override
    public void onDisable() {

    }

    public PrefabManager getPrefabManager(){
        return prefabManager;
    }

    public static ItemPrefabs getInstance(){
        return instance;
    }

}
