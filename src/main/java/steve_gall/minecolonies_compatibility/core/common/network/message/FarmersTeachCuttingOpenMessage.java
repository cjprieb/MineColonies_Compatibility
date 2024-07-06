package steve_gall.minecolonies_compatibility.core.common.network.message;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;
import com.minecolonies.api.util.constant.IToolType;
import com.minecolonies.api.util.constant.ToolType;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.TeachCuttingMenu;

public class FarmersTeachCuttingOpenMessage extends ModuleMenuOpenMessage
{
	private final IToolType toolType;

	public FarmersTeachCuttingOpenMessage(IBuildingModuleView module, IToolType toolType)
	{
		super(module);

		this.toolType = toolType;
	}

	public FarmersTeachCuttingOpenMessage(FriendlyByteBuf buffer)
	{
		super(buffer);

		this.toolType = ToolType.getToolType(buffer.readUtf());
	}

	@Override
	public void encode(FriendlyByteBuf buffer)
	{
		super.encode(buffer);

		buffer.writeUtf(this.toolType.getName());
	}

	@Override
	protected AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player, IBuildingModule module)
	{
		return new TeachCuttingMenu(windowId, inventory, module, this.toolType);
	}

	@Override
	protected void toBuffer(FriendlyByteBuf buffer)
	{
		super.toBuffer(buffer);

		buffer.writeUtf(this.getToolType().getName());
	}

	public IToolType getToolType()
	{
		return this.toolType;
	}

}
