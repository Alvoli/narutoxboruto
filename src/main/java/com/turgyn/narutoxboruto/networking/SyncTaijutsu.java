package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncTaijutsu {
	private final int taijutsu;

	public SyncTaijutsu(int taijutsu) {
		this.taijutsu = taijutsu;
	}

	public SyncTaijutsu(FriendlyByteBuf buf) {
		this.taijutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(taijutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setTaijutsu(taijutsu));
	}
}
