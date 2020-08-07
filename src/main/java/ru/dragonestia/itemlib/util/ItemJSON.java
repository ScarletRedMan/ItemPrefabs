package ru.dragonestia.itemlib.util;

import cn.nukkit.item.Item;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ItemJSON {

    @SerializedName("id")
    private int id;

    @SerializedName("damage")
    private int damage;

    @SerializedName("data")
    private byte[] data;

    private ItemJSON(int id, int damage, byte[] data){
        this.id = id;
        this.damage = damage;
        this.data = data;
    }

    private ItemJSON(Item item){
        this(item.getId(), item.getDamage(), item.getCompoundTag());
    }

    public int getId() {
        return id;
    }

    public int getDamage() {
        return damage;
    }

    public byte[] getData() {
        return data;
    }

    public Item getItem(){
        return getItem(1);
    }

    public Item getItem(int count){
        return Item.get(id, damage, count).setCompoundTag(data);
    }

    public String getJson(){
        return new Gson().toJson(this);
    }

    public static ItemJSON get(Item item){
        return new ItemJSON(item);
    }

    public static ItemJSON get(String json){
        return new Gson().fromJson(json, ItemJSON.class);
    }

}
