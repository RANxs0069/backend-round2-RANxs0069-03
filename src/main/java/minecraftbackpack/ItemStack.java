package minecraftbackpack;

public class ItemStack {
    private final Item item;
    private int quantity;

    public ItemStack(Item item, int quantity) {
        this.item = item;
        if (quantity < 0)
            throw new IllegalArgumentException("数量必须大于等于0！！！");
        this.quantity = quantity;
    }
    /*这里构造方法不检验quantity是否超过maxStackSize，是为了满足题目"一次添加可以多于最大堆叠数"的要求：
    * quantity表示"本次要加入的量"，可以超过单堆上限；真正的拆分（每堆不超过maxStackSize）放在addItem()中完成*/

    public Item getItem() {
        return item;
    }
    public int getItemQuantity() {
        return quantity;
    }
    public void setItemQuantity(int quantity) {
        this.quantity = quantity;
    }

}
