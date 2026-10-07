package minecraftbackpack;


public class Main {
    public static void main(String[] args) {
        // 1. 创建一个容量为 36 的背包
        Inventory inv = new Inventory();
        System.out.println("=== 1. 创建容量为 36 的背包 ===");
        inv.display();

        // 2. 添加 30 个"石头"
        int t2 = inv.addItem(new ItemStack(new Item("石头"), 30));
        System.out.println("\n=== 2. 添加 30 个石头 ===");
        System.out.println("addItem 返回(剩余容量)：" + t2);
        inv.display();

        // 3. 再添加 40 个"石头"（测试堆叠：预期 一个 64 堆 + 一个 6 堆）
        int t3 = inv.addItem(new ItemStack(new Item("石头"), 40));
        System.out.println("\n=== 3. 再添加 40 个石头（测试堆叠）===");
        System.out.println("addItem 返回(剩余容量)：" + t3);
        inv.display();

        // 4. 添加 1 把"钻石剑"（不可堆叠，独占一格）
        int t4 = inv.addItem(new ItemStack(new Item("钻石剑"), 1));
        System.out.println("\n=== 4. 添加 1 把钻石剑 ===");
        System.out.println("addItem 返回(剩余容量)：" + t4);
        inv.display();

        // 5. 测试溢出(木板没法实现一个一堆，用剑代替)
        int count = 0;
        for(int i = 0; i < 36; i++){
            int t5 = inv.addItem(new ItemStack(new Item("钻石剑"),1));
            if (t5 < 0) count -= t5;
        }
        System.out.println("\n=== 5. 测试溢出 ===");
        System.out.println("放入失败 " + count +" 把钻石剑");
        inv.display();

        // 6. 移除 10 个"石头"
        System.out.println("\n=== 6. 移除 10 个石头 ===");
        inv.removeItem("石头", 10);
        inv.display();

        // 统计各物品总数
        System.out.println("\n=== 统计 getItemCount() ===");
        System.out.println("石头：" + inv.getItemCount("石头") + " 个");
        System.out.println("木板：" + inv.getItemCount("木板") + " 个");
        System.out.println("钻石剑：" + inv.getItemCount("钻石剑") + " 个");
    }
}
