package ru.dragonestia.itemlib.item;

import ru.dragonestia.itemlib.ItemPrefabs;
import ru.dragonestia.itemlib.util.ItemJSON;

public class CustomItem {

    private final cn.nukkit.item.Item item;

    private final int id;

    public CustomItem(int id, cn.nukkit.item.Item item){
        if(id <= ItemPrefabs.BORDER) throw new InvalidIdForCustomItemError();

        this.item = item;
        this.id = id;
    }

    //always: id > ItemPrefabs.BORDER
    public int getId(){
        return id;
    }

    public cn.nukkit.item.Item getItem(int count){
        cn.nukkit.item.Item item = this.item.clone();
        item.setCount(count);
        return item;
    }

    public cn.nukkit.item.Item getItem(){
        return getItem(1);
    }

    public ItemJSON getItemJsonObject(){
        return ItemJSON.get(item);
    }

    public static CustomItem get(int id){
        return ItemPrefabs.getInstance().getPrefabManager().get(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CustomItem){
            return ((CustomItem) obj).item.equals(item);
        }else if(obj instanceof Item){
            return obj.equals(item);
        }
        return false;
    }

    public static class Item {

        public static cn.nukkit.item.Item get(int id, int damage, int count){
            return id > 1000? CustomItem.get(id).getItem(count) : cn.nukkit.item.Item.get(id, damage, count);
        }

        public static cn.nukkit.item.Item get(int id, int damage){
            return get(id, damage, 1);
        }

        public static cn.nukkit.item.Item get(int id){
            return get(id, 0, 1);
        }

    }

}
