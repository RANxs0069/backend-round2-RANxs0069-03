package minecraftbackpack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventory {
    private List<ItemStack> items;
    private final int capacity = 36;

    public Inventory() {
        items = new ArrayList<ItemStack>();
    }

    public int addItem(ItemStack itemStackToAdd) {
        Item item = itemStackToAdd.getItem();
        int amount = itemStackToAdd.getItemQuantity();

        for (ItemStack itemStack : items) {
            if(itemStack.getItem().equals (item) && itemStack.getItemQuantity() < item.getMaxStackSize()){
                if(amount <= item.getMaxStackSize() - itemStack.getItemQuantity()){
                    itemStack.setItemQuantity(itemStack.getItemQuantity() + amount);
                    amount = 0;
                }
                else {
                    int space = item.getMaxStackSize() - itemStack.getItemQuantity();
                    itemStack.setItemQuantity(item.getMaxStackSize());
                    amount -= space;
                }
            }
        }

        while (amount > 0) {
            if (capacity - items.size() > 0 && amount >= item.getMaxStackSize()) {
                items.add(new ItemStack(item, item.getMaxStackSize()));
                amount -= item.getMaxStackSize();
            } else if (capacity - items.size() > 0 ) {
                items.add(new ItemStack(item, amount));
                amount = 0;
            } else {
                return -amount;
            }
        }
        return capacity - items.size();
    }

    public void removeItem(String itemName, int amount) {
        if (amount < 0) throw new IllegalArgumentException("取出数量不得小于0！！！");
        Item item = new Item(itemName);

        Iterator<ItemStack> ite = items.iterator();
        while (ite.hasNext() && amount > 0) {
            ItemStack next = ite.next();
            if (!next.getItem().equals(item)) continue;
            if (next.getItemQuantity() <= amount) {   // 整堆取走
                amount -= next.getItemQuantity();
                ite.remove();
            } else {                                  // 只取一部分
                next.setItemQuantity(next.getItemQuantity() - amount);
                amount = 0;
            }
        }
        if (amount > 0) {
            System.out.println("物品数量不足，还有" + amount + "个物品没有取出");
        }
    }

    public int getItemCount(String itemName){
        Item item = new Item(itemName);
        Iterator<ItemStack> ite = items.iterator();
        int count = 0;
        while (ite.hasNext()) {
            ItemStack next = ite.next();
            if (!next.getItem().equals(item)) continue;
            count += next.getItemQuantity();
        }
        return count;
    }

    public void display(){
        System.out.println("背包中的物品有：");
        for (ItemStack itemStack : items) {
            System.out.println("- " + itemStack.getItem().getName() + " x" + itemStack.getItemQuantity());
        }
        System.out.println("剩余格子：" + (capacity - items.size()) + "/" + capacity);
    }
}
