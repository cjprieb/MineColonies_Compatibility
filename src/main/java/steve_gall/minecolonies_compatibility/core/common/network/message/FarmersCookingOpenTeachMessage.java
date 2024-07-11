package steve_gall.minecolonies_compatibility.core.common.network.message;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.TeachCookingMenu;

public class FarmersCookingOpenTeachMessage extends ModuleMenuOpenMessage
{
	public FarmersCookingOpenTeachMessage(IBuildingModuleView module)
	{
		super(module);
	}

	public FarmersCookingOpenTeachMessage(FriendlyByteBuf buffer)
	{
		super(buffer);
	}

	@Override
	public void encode(FriendlyByteBuf buffer)
	{
		super.encode(buffer);
	}

	@Override
	protected AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player, IBuildingModule module)
	{
		return new TeachCookingMenu(windowId, inventory, module);
	}

	@Override
	protected void toBuffer(FriendlyByteBuf buffer, IBuildingModule module)
	{
		super.toBuffer(buffer, module);
	}

}
