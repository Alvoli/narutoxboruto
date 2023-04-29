package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.client.PlayerData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MedicalSyncS2CPacket {
	private final int medical;

	public MedicalSyncS2CPacket(int medical) {
		this.medical = medical;
	}

	public MedicalSyncS2CPacket(FriendlyByteBuf buf) {
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
