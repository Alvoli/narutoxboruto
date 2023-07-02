package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;

public class ReleaseDnaBottleItem extends DnaBottleItem{
    Item release;
    public ReleaseDnaBottleItem(Properties properties, Item pRelease) {
        super(properties);
        this.release = pRelease;
    }

    @Override
    public Item getRelease() {
        return release;
    }
}
