package com.turgyn.narutoxboruto.items;

import net.minecraft.world.item.Item;
import static com.turgyn.narutoxboruto.items.ModItems.YIN_RELEASE;

public class YinDnaBottle extends DnaBottleItem{
    public YinDnaBottle(Properties properties) {
        super(properties);
    }

    @Override
    public Item getRelease() {
        return YIN_RELEASE.get();
    }
}

