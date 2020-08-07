package ru.dragonestia.itemlib.item;

import cn.nukkit.item.Item;
import cn.nukkit.utils.Config;
import ru.dragonestia.itemlib.ItemPrefabs;
import ru.dragonestia.itemlib.util.ItemJSON;

import java.util.HashMap;

public class PrefabManager {

    private final ItemPrefabs main;

    private final HashMap<Integer, CustomItem> customItems = new HashMap<>();

    private Config items;

    public PrefabManager(ItemPrefabs main){
        this.main = main;
    }

    public void initConfig(){
        items = new Config("plugins/ItemPrefabs/items.yml", Config.YAML);
    }

    public void loadItemsFromConfig(){
        int id;
        for(String key: items.getKeys()){
            try{
                id = Integer.parseInt(key);
            }catch (ClassCastException | NumberFormatException ignore){
                continue;
            }
            customItems.put(id, new CustomItem(id, getItemById(id)));
        }

        main.getLogger().info("Было зарегистрировано " + customItems.size() + " пользовательских предметов.");
    }

    private Item getItemById(int id){
        return ItemJSON.get(items.getString(id + "")).getItem();
    }

    public void registerCustomItem(int id, Item item){
        registerCustomItem(new CustomItem(id, item));
    }

    public void registerCustomItem(CustomItem item){
        items.set(item.getId() + "", item.getItemJsonObject().getJson());
        items.save();
        main.getLogger().info("Пользовательский предмет \"" + item.getItem().getCustomName() + "\" с id " + item.getId() + " был добавлен в очередь на регистрацию.");
    }

    public CustomItem get(int id){
        if(!items.exists(id + "")) return null;
        return new CustomItem(id, ItemJSON.get(getItemById(id)).getItem());
    }

    public CustomItem get(Item item){
        for(CustomItem customItem: customItems.values()){
            if(customItem.equals(item)) return customItem;
        }
        return null;
    }

}
