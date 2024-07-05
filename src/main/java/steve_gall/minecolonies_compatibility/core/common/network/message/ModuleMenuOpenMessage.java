package steve_gall.minecolonies_compatibility.core.common.network.message;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.MenuProvider;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.NetworkHooks;

public abstract class ModuleMenuOpenMessage extends BuildingModuleMessage
{
	public ModuleMenuOpenMessage(IBuildingModuleView module)
	{
		super(module);
	}

	public ModuleMenuOpenMessage(FriendlyByteBuf buffer)
	{
		super(buffer);
	}

	@Override
	public void encode(FriendlyByteBuf buffer)
	{
		super.encode(buffer);
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

		NetworkHooks.openScreen(player, this.createMenuProvider(module), this::toBuffer);
	}

	protected abstract MenuProvider createMenuProvider(IBuildingModule module);

	protected void toBuffer(FriendlyByteBuf buffer)
	{
		this.getModulePos().serializeBuffer(buffer);
	}

}
