package net.gameslabs.model;

public class Ore {
    private OreType oreType;
    private int xp;
    private int level;
    private String item;

    public static final Ore TIN = new Ore(OreType.TIN, 150, 1, "mining.tin");
    public static final Ore IRON = new Ore(OreType.IRON, 300, 3, "mining.iron");
    public static final Ore COAL = new Ore(OreType.COAL, 500, 5, "mining.coal");

    private Ore(OreType oreType, int xp, int level, String item) {
        this.oreType = oreType;
        this.xp = xp;
        this.level = level;
        this.item = item;
    }

    public OreType getOreType() {
        return oreType;
    }

    public void setOreType(OreType oreType) {
        this.oreType = oreType;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
