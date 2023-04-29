package com.turgyn.narutomod.networking;

import com.turgyn.narutomod.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPacketHandler {
	private static SimpleChannel INSTANCE;

	private static int packetId = 0;

	private static int id() {
		return packetId++;
	}

	public static void register() {
		SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Main.MOD_ID, "messages"))
				.networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true)
				.simpleChannel();
		INSTANCE = net;
		net.messageBuilder(ChakraHandlerC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER).decoder(
				ChakraHandlerC2SPacket::new).encoder(ChakraHandlerC2SPacket::toBytes).consumerMainThread(
				ChakraHandlerC2SPacket::handle).add();
		net.messageBuilder(ChakraSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				ChakraSyncS2CPacket::new).encoder(ChakraSyncS2CPacket::toBytes).consumerMainThread(
				ChakraSyncS2CPacket::handle).add();
		net.messageBuilder(GenjutsuSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				GenjutsuSyncS2CPacket::new).encoder(GenjutsuSyncS2CPacket::toBytes).consumerMainThread(
				GenjutsuSyncS2CPacket::handle).add();
		net.messageBuilder(KenjutsuSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				KenjutsuSyncS2CPacket::new).encoder(KenjutsuSyncS2CPacket::toBytes).consumerMainThread(
				KenjutsuSyncS2CPacket::handle).add();
		net.messageBuilder(KinjutsuSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				KinjutsuSyncS2CPacket::new).encoder(KinjutsuSyncS2CPacket::toBytes).consumerMainThread(
				KinjutsuSyncS2CPacket::handle).add();
		net.messageBuilder(MedicalSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				MedicalSyncS2CPacket::new).encoder(MedicalSyncS2CPacket::toBytes).consumerMainThread(
				MedicalSyncS2CPacket::handle).add();
		net.messageBuilder(NinjutsuSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				NinjutsuSyncS2CPacket::new).encoder(NinjutsuSyncS2CPacket::toBytes).consumerMainThread(
				NinjutsuSyncS2CPacket::handle).add();
		net.messageBuilder(SenjutsuSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				SenjutsuSyncS2CPacket::new).encoder(SenjutsuSyncS2CPacket::toBytes).consumerMainThread(
				SenjutsuSyncS2CPacket::handle).add();
		net.messageBuilder(ShinobiPointsSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				ShinobiPointsSyncS2CPacket::new).encoder(ShinobiPointsSyncS2CPacket::toBytes).consumerMainThread(
				ShinobiPointsSyncS2CPacket::handle).add();
		net.messageBuilder(ShurikenjutsuSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				ShurikenjutsuSyncS2CPacket::new).encoder(ShurikenjutsuSyncS2CPacket::toBytes).consumerMainThread(
				ShurikenjutsuSyncS2CPacket::handle).add();
		net.messageBuilder(SpeedSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				SpeedSyncS2CPacket::new).encoder(SpeedSyncS2CPacket::toBytes).consumerMainThread(
				SpeedSyncS2CPacket::handle).add();
		net.messageBuilder(SummoningSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				SummoningSyncS2CPacket::new).encoder(SummoningSyncS2CPacket::toBytes).consumerMainThread(
				SummoningSyncS2CPacket::handle).add();
		net.messageBuilder(TaijutsuSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				TaijutsuSyncS2CPacket::new).encoder(TaijutsuSyncS2CPacket::toBytes).consumerMainThread(
				TaijutsuSyncS2CPacket::handle).add();
	}

	public static <MSG> void sendToServer(MSG message) {
		INSTANCE.sendToServer(message);
	}

	public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
	}
}
