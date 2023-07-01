package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;

import static com.turgyn.narutoxboruto.items.ModItems.EARTH_RELEASE;

public class EarthDnaBottle extends DnaBottleItem{

    public EarthDnaBottle(Properties properties) {
        super(properties);
    }
    @Override
    public Item getRelease() {
        return EARTH_RELEASE.get();
    }
}