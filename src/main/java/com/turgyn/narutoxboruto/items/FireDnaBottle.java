package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;

import static com.turgyn.narutoxboruto.items.ModItems.FIRE_RELEASE;

public class FireDnaBottle extends DnaBottleItem {

    public FireDnaBottle(Properties properties) {
        super(properties);
    }
    @Override
    public Item getRelease() {
        return FIRE_RELEASE.get();
    }
}