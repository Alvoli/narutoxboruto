package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;

import static com.turgyn.narutoxboruto.items.ModItems.WATER_RELEASE;

public class WaterDnaBottle extends DnaBottleItem{
    public WaterDnaBottle(Properties properties) {
        super(properties);
    }

    @Override
    public Item getRelease() {
        return WATER_RELEASE.get();
    }
}
