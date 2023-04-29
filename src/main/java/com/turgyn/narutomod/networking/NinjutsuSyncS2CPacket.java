package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class NinjutsuSyncS2CPacket {
	private final int ninjutsu;

	public NinjutsuSyncS2CPacket(int ninjutsu) {
		this.ninjutsu = ninjutsu;
	}

	public NinjutsuSyncS2CPacket(FriendlyByteBuf buf) {
		this.ninjutsu = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(ninjutsu);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> PlayerData.setNinjutsu(ninjutsu));
	}
}
