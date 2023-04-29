package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TaijutsuSyncS2CPacket {
	private final int taijutsu;

	public TaijutsuSyncS2CPacket(int taijutsu) {
		this.taijutsu = taijutsu;
	}

	public TaijutsuSyncS2CPacket(FriendlyByteBuf buf) {
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
