package steve_gall.minecolonies_compatibility.core.common.network.message;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.NetworkHooks;

public abstract class ModuleMenuOpenMessage extends BuildingModuleMessage
{
	protected final String desc;

	public ModuleMenuOpenMessage(IBuildingModuleView module)
	{
		super(module);

		this.desc = module.getDesc();
	}

	public ModuleMenuOpenMessage(FriendlyByteBuf buffer)
	{
		super(buffer);

		this.desc = buffer.readUtf();
	}

	@Override
	public void encode(FriendlyByteBuf buffer)
	{
		super.encode(buffer);

		buffer.writeUtf(this.desc);
	}

	@Override
	public void handle(Context context)
	{
		super.handle(context);

		var player = context.getSender();

		if (player == null)
		{
			return;
		}

		var module = this.getModule();

		if (module == null)
		{
			return;
		}

		NetworkHooks.openScreen(player, new MenuProvider()
		{
			@Override
			public Component getDisplayName()
			{
				return ModuleMenuOpenMessage.this.getDisplayName();
			}

			@Override
			public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player)
			{
				return ModuleMenuOpenMessage.this.createMenu(windowId, inventory, player, module);
			}

		}, buffer -> this.toBuffer(buffer, module));
	}

	protected Component getDisplayName()
	{
		return Component.translatable(this.desc + ".menu");
	}

	protected abstract AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player, IBuildingModule module);

	protected void toBuffer(FriendlyByteBuf buffer, IBuildingModule module)
	{
		this.getModulePos().serializeBuffer(buffer);
	}

}
