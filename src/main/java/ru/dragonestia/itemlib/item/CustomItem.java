package ru.dragonestia.itemlib.item;

import cn.nukkit.item.Item;
import ru.dragonestia.itemlib.ItemPrefabs;
import ru.dragonestia.itemlib.util.ItemJSON;

import java.util.Arrays;

public class CustomItem {

    private final Item item;

    private final int id;

    public CustomItem(int id, Item item){
        if(id >= 0) throw new InvalidIdForCustomItemError();

        this.item = item;
        this.id = id;
    }

    //always: id < 0
    public int getId(){
        return id;
    }

    public Item getItem(int count){
        Item item = this.item.clone();
        item.setCount(count);
        return item;
    }

    public Item getItem(){
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

}
