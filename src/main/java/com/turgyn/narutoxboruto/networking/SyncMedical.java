package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncMedical {
	private final int medical;

	public SyncMedical(int medical) {
		this.medical = medical;
	}

	public SyncMedical(FriendlyByteBuf buf) {
		this.medical = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(medical);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setMedical(medical));
	}
}
