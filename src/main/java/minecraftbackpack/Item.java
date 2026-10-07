package minecraftbackpack;

public class Item {

    private final String name;
    private int maxStackSize;

    public enum Type{
        WOOD("木板"),STONE("石头"),DIMONDSWORE("钻石剑");//三个常量
        private final String value;

        Type(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }

        public static Type of(String v){//按中文value查找，Type.valueOf()按常量名(WOOD等)查找，所以必须自己写
            for (Type t : values()){
                if (t.value.equals(v)) return t;
            }
            throw new IllegalArgumentException("物品只能是木板/石头/钻石剑");
        }
    }

    public Item(String name) {
        this.name = Type.of(name).getValue();//用of()按中文名查表，查不到会抛异常，起到检验作用
        if(name.equals("石头") || name.equals("木板")){
            maxStackSize = 64;
        }else if(name.equals("钻石剑")) {
            maxStackSize = 1;
        }//控制一下物品的最大堆叠数量，这个数量不能随意传，而是定好的
    }

    public String getName() {
        return name;
    }
    public int getMaxStackSize() {
        return maxStackSize;
    }


    /*
    为了修bug重写了equals（），
    这几行确实是AI写的，我暂时只作学习了解
     */
    @Override
    public boolean equals(Object o) {//不能直接签名Item，那就不是重写了。
        if (this == o) return true;//先比地址，地址相同一定相同（相当于快速通道，可有可无）
        if (!(o instanceof Item)) return false;//比类型，类型不同一定不同。（同时也为下一行的强转作保证，不可省略）
        return name.equals(((Item) o).name);   // 放到Item这里，name相同就可以认为相同。
    }
    @Override
    public int hashCode() {//hashcode与地址有关，一般为了规范，重写equals（）就要顺便重写HashCode().
        return name.hashCode();
    }
}
