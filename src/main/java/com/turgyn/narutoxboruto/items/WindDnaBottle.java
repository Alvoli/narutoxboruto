package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;

import static com.turgyn.narutoxboruto.items.ModItems.WIND_RELEASE;

public class WindDnaBottle extends DnaBottleItem{

    public WindDnaBottle(Properties properties) {
        super(properties);
    }
    @Override
    public Item getRelease() {
        return WIND_RELEASE.get();
    }
}
