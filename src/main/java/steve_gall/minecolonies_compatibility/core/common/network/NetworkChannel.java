package steve_gall.minecolonies_compatibility.core.common.network;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.core.common.network.message.BucketFillingOpenTeachMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.FarmersCookingOpenTeachMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.FarmersCuttingOpenTeachMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.JEIGhostAcceptFluidMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.JEIGhostAcceptItemMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.JEIRecipeTransferMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.LetsDoBakeryBakingOpenTeachMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.LetsDoBakeryBowlOpenTeachMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.LetsDoBakeryCookingOpenTeachMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.LetsDoBakeryStoveOpenTeachMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.NetworkStorageRefreshMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.PolymorphTeachResultItemMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.RestrictGiveToolMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.RestrictSetAreaMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.RestrictSetEnabledMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.SmithingOpenTeachMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.SmithingTemplateOpenInventoryMessage;
import steve_gall.minecolonies_compatibility.core.common.network.message.TeachRecipeMenuNewResultMessage;

public class NetworkChannel
{
	private final SimpleChannel rawChannel;
	private final AtomicInteger idx;

	public NetworkChannel(String channelName)
	{
		var modVersion = ModList.get().getModContainerById(MineColoniesCompatibility.MOD_ID).get().getModInfo().getVersion().toString();
		this.rawChannel = NetworkRegistry.newSimpleChannel(MineColoniesCompatibility.rl(channelName), () -> modVersion, str -> str.equals(modVersion), str -> str.equals(modVersion));
		this.idx = new AtomicInteger();

		this.registerMessage(TeachRecipeMenuNewResultMessage.class, TeachRecipeMenuNewResultMessage::new);
		this.registerMessage(PolymorphTeachResultItemMessage.class, PolymorphTeachResultItemMessage::new);
		this.registerMessage(JEIGhostAcceptItemMessage.class, JEIGhostAcceptItemMessage::new);
		this.registerMessage(JEIGhostAcceptFluidMessage.class, JEIGhostAcceptFluidMessage::new);
		this.registerMessage(JEIRecipeTransferMessage.class, JEIRecipeTransferMessage::new);
		this.registerMessage(NetworkStorageRefreshMessage.class, NetworkStorageRefreshMessage::new);
		this.registerMessage(RestrictSetEnabledMessage.class, RestrictSetEnabledMessage::new);
		this.registerMessage(RestrictSetAreaMessage.class, RestrictSetAreaMessage::new);
		this.registerMessage(RestrictGiveToolMessage.class, RestrictGiveToolMessage::new);
		this.registerMessage(BucketFillingOpenTeachMessage.class, BucketFillingOpenTeachMessage::new);
		this.registerMessage(SmithingOpenTeachMessage.class, SmithingOpenTeachMessage::new);
		this.registerMessage(SmithingTemplateOpenInventoryMessage.class, SmithingTemplateOpenInventoryMessage::new);
		this.registerMessage(FarmersCuttingOpenTeachMessage.class, FarmersCuttingOpenTeachMessage::new);
		this.registerMessage(FarmersCookingOpenTeachMessage.class, FarmersCookingOpenTeachMessage::new);
		this.registerMessage(LetsDoBakeryStoveOpenTeachMessage.class, LetsDoBakeryStoveOpenTeachMessage::new);
		this.registerMessage(LetsDoBakeryBowlOpenTeachMessage.class, LetsDoBakeryBowlOpenTeachMessage::new);
		this.registerMessage(LetsDoBakeryBakingOpenTeachMessage.class, LetsDoBakeryBakingOpenTeachMessage::new);
		this.registerMessage(LetsDoBakeryCookingOpenTeachMessage.class, LetsDoBakeryCookingOpenTeachMessage::new);
	}

	public void sendToServer(AbstractMessage message)
	{
		this.rawChannel.sendToServer(message);
	}

	public void sendToPlayer(AbstractMessage message, ServerPlayer player)
	{
		this.rawChannel.sendTo(message, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
	}

	private <MSG extends AbstractMessage> void registerMessage(Class<MSG> messageType, Function<FriendlyByteBuf, MSG> decoder)
	{
		this.rawChannel.registerMessage(this.idx.incrementAndGet(), messageType, MSG::encode, decoder, (msg, supplier) ->
		{
			var context = supplier.get();
			context.setPacketHandled(true);
			context.enqueueWork(() -> msg.handle(context));
		});
	}

}
