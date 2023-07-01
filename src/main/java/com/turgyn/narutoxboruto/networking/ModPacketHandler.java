package com.turgyn.narutoxboruto.networking;

import com.turgyn.narutoxboruto.Main;
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
		net.messageBuilder(SyncAffiliation.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncAffiliation::new)
				.encoder(SyncAffiliation::toBytes).consumerMainThread(SyncAffiliation::handle).add();
		net.messageBuilder(SyncChakra.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncChakra::new).encoder(
				SyncChakra::toBytes).consumerMainThread(SyncChakra::handle).add();
		net.messageBuilder(SyncClan.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncClan::new).encoder(
				SyncClan::toBytes).consumerMainThread(SyncClan::handle).add();
		net.messageBuilder(SyncGenjutsu.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncGenjutsu::new)
				.encoder(SyncGenjutsu::toBytes).consumerMainThread(SyncGenjutsu::handle).add();
		net.messageBuilder(SyncKenjutsu.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncKenjutsu::new)
				.encoder(SyncKenjutsu::toBytes).consumerMainThread(SyncKenjutsu::handle).add();
		net.messageBuilder(SyncKinjutsu.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncKinjutsu::new)
				.encoder(SyncKinjutsu::toBytes).consumerMainThread(SyncKinjutsu::handle).add();
		net.messageBuilder(SyncMedical.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncMedical::new).encoder(
				SyncMedical::toBytes).consumerMainThread(SyncMedical::handle).add();
		net.messageBuilder(SyncNinjutsu.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncNinjutsu::new)
				.encoder(SyncNinjutsu::toBytes).consumerMainThread(SyncNinjutsu::handle).add();
		net.messageBuilder(SyncRank.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncRank::new).encoder(
				SyncRank::toBytes).consumerMainThread(SyncRank::handle).add();
		net.messageBuilder(SyncSenjutsu.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncSenjutsu::new)
				.encoder(SyncSenjutsu::toBytes).consumerMainThread(SyncSenjutsu::handle).add();
		net.messageBuilder(SyncShinobiPoints.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				SyncShinobiPoints::new).encoder(SyncShinobiPoints::toBytes).consumerMainThread(
				SyncShinobiPoints::handle).add();
		net.messageBuilder(SyncShurikenjutsu.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(
				SyncShurikenjutsu::new).encoder(SyncShurikenjutsu::toBytes).consumerMainThread(
				SyncShurikenjutsu::handle).add();
		net.messageBuilder(SyncSpeed.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncSpeed::new).encoder(
				SyncSpeed::toBytes).consumerMainThread(SyncSpeed::handle).add();
		net.messageBuilder(SyncSummoning.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncSummoning::new)
				.encoder(SyncSummoning::toBytes).consumerMainThread(SyncSummoning::handle).add();
		net.messageBuilder(SyncTaijutsu.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(SyncTaijutsu::new)
				.encoder(SyncTaijutsu::toBytes).consumerMainThread(SyncTaijutsu::handle).add();
	}

	public static <MSG> void sendToServer(MSG message) {
		INSTANCE.sendToServer(message);
	}

	public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
		INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
	}
}
