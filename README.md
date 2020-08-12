# ItemPrefabs

Плагин для создания готовых наборов предметов с имеющимеся изменениями в виде зачарований, описания, названия и тд. 
Каждый префаб имеет собственный числовой id(>1000), который записывается в конфиг. 
Предметы можно легко легко получать и сравнивать с помощью специальных методов.


## Использование
### Создание префаба
Чтобы добавить собственный предмет в список префабов используйте классы `CustomItem` и `PrefabManager` в пакете ru.dragonestia.itemlib.item и обратитесь к методу `getPrefabManager()` через класс ru.dragonestia.itemlib.`ItemPrefabs`.
Чтобы создать префаб можно пойти двумя путями:
- Создать обект класса Item и применить к нему необходимые изменения
```java
@Override
public void onLoad(){
  Item item = Item.get(1);
  item.setCustomName("Нереальный камушек");
  
  PrefabManager manager = ItemPrefabs.getInstance().getPrefabManager(); 
  manager.registerCustomItem(1001, item);
}
```
- Расширить класс CustomItem

> UnrealStoneCustomItem.java
```java
public class UnrealStoneCustomItem extends CustomItem {
  
  public UnrealStoneCustomItem(){
    cn.nukkit.item.Item item = Item.get(1);
    item.setCustomName("Нереальный камушек");
    
    super(1001, item);
  }
  
}
```
> Тело плагина
```java
@Override
public void onLoad(){
  PrefabManager manager = ItemPrefabs.getInstance().getPrefabManager(); 
  manager.registerCustomItem(new UnrealStoneCustomItem());
}
```
### Поучение префаба
Для того чтобы получить префаб воспользуйтесь методом ru.dragonestia.itemlib.item.`CustomItem.Item.get()`.

Пример:
```java
Item item;
CustomItem prefab = CustomItem.get(1001); //Получаем объект префаба с id 1001
item = prefab.getItem(12); //Получаем объект Item с префаба в кол-ве 12шт.
item = CustomItem.Item.get(1001, 0, 12); //Получаем объект Item с префаба с id 1001 c кол-вом в 12шт.
item = CustomItem.Item.get(1, 0, 12); //Получаем обычный камень с id 1 с кол-вом 12шт.
```
### Сравнение через equals()
Для того чтобы проверить является ли предмет определенным префабом можно поспользоваться методом `.equals()` от объекта префаба(экземпляр класса CustomItem).
```java
Item item;
CustomItem prefab = CustomPrefab.get(1001);

item = Item.get(1);
prefab.equals(item); //false

item = prefab.getItem();
prefab.equals(item); //true

```
