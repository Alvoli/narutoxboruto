package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;

import static com.turgyn.narutoxboruto.items.ModItems.LIGHTNING_RELEASE;

public class LightningDnaBottle extends DnaBottleItem {

    public LightningDnaBottle(Properties properties) {
        super(properties);
    }

    @Override
    public Item getRelease() {
        return LIGHTNING_RELEASE.get();
    }
}
