package ru.dragonestia.itemlib.command;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.item.Item;
import ru.dragonestia.itemlib.item.CustomItem;

public class GiveCommand extends Command {

    public GiveCommand() {
        super("give", "Выдать предмет", "/give <id> [damage] [count] [Игрок]");

        addCommandParameters("", new CommandParameter[]{
                new CommandParameter("id", CommandParameter.ARG_TYPE_INT),
                new CommandParameter("damage", CommandParameter.ARG_TYPE_INT),
                new CommandParameter("count", CommandParameter.ARG_TYPE_INT)
        });

        setPermission("item.command.give");
    }

    @Override
    public boolean execute(CommandSender commandSender, String label, String[] args) {
        Player player = null;
        int id, damage = 0, count = 1;

        if(args.length < 4){
            if(commandSender instanceof Player) player = (Player) commandSender;
            else{
                commandSender.sendMessage("Вы не указали игрока, которому хотите выдать предмет.");
                return false;
            }
        }else{
            player = Server.getInstance().getPlayer(args[3]);
            if(player == null){
                commandSender.sendMessage("Игрок не найден!");
                return false;
            }
        }

        if(args.length > 2){
            try{
                count = Integer.parseInt(args[2]);

                if(count < 1) throw new NumberFormatException();
            } catch (NumberFormatException | ClassCastException ex){
                commandSender.sendMessage("Введено неверное количество выдаваемых предметов.");
                return false;
            }
        }

        if(args.length > 1){
            try{
                damage = Integer.parseInt(args[1]);

                if(damage < 0) throw new NumberFormatException();
            } catch (NumberFormatException | ClassCastException ex){
                commandSender.sendMessage("Введено неверное в поле damage.");
                return false;
            }
        }

        if(args.length == 0){
            commandSender.sendMessage(getUsage());
            return false;
        }

        try{
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException | ClassCastException ex){
            commandSender.sendMessage("Введено неверное значение в поле id.");
            return false;
        }

        Item item = null;
        if(id < 0){
            item = CustomItem.get(id).getItem(count);
        }else item = new Item(id, damage, count);

        if(item == null){
            commandSender.sendMessage("Не удалось выдать предмет.");
            return false;
        }

        player.getInventory().addItem(item);
        commandSender.sendMessage("Вы успешно выдали \"" + (item.hasCustomName()? item.getCustomName() : item.getName()) + "\"(" + id + ":" + damage + ") " + count + "шт. игроку " + player.getName() + "!");
        return true;
    }

}
