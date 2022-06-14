package com.github.diegonighty.sign.core.v1_18_R1;

import com.github.diegonighty.sign.api.gui.SignConversation;
import com.github.diegonighty.sign.core.AbstractSignService;
import com.github.diegonighty.sign.core.packet.PacketChannelDuplexHandler;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayInUpdateSign;
import net.minecraft.network.protocol.game.PacketPlayOutOpenSignEditor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SignGUIService_v1_18_R1 extends AbstractSignService {

	@Override
	protected void addInterceptors() {
		PacketChannelDuplexHandler.addInterceptor(PacketPlayInUpdateSign.class, new SignListener(this));
	}

	@Override
	public void openFakeSign(Player player, Location location) {
		BlockPosition position = new BlockPosition(
				location.getBlockX(),
				location.getBlockY(),
				location.getBlockZ()
		);

		Packet<?> packet = new PacketPlayOutOpenSignEditor(position);
		((CraftPlayer) player)
				.getHandle()
				.b
				.a(packet);
	}

	@Override
	public void injectPlayer(Player player) {
		((CraftPlayer) player).getHandle()
				.b.a.k.pipeline()
				.addBefore("packet_handler", CHANNEL, new PacketChannelDuplexHandler(player));
	}
}
