package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncChakra {
	private final int chakra;

	public SyncChakra(int chakra) {
		this.chakra = chakra;
	}

	public SyncChakra(FriendlyByteBuf buf) {
		this.chakra = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(chakra);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			PlayerData.setChakra(chakra);
			PlayerData.setCurrentMaxChakra(50);
		});
	}
}
