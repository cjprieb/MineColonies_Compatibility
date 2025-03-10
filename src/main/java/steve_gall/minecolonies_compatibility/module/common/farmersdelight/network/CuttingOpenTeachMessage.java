package steve_gall.minecolonies_compatibility.module.common.farmersdelight.network;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;
import com.minecolonies.api.util.constant.IToolType;
import com.minecolonies.api.util.constant.ToolType;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import steve_gall.minecolonies_compatibility.core.common.network.message.ModuleMenuOpenMessage;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.CuttingTeachMenu;

public class CuttingOpenTeachMessage extends ModuleMenuOpenMessage
{
	private final IToolType toolType;

	public CuttingOpenTeachMessage(IBuildingModuleView module, IToolType toolType)
	{
		super(module);

		this.toolType = toolType;
	}

	public CuttingOpenTeachMessage(FriendlyByteBuf buffer)
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
		return new CuttingTeachMenu(windowId, inventory, module, this.toolType);
	}

	@Override
	protected void toBuffer(FriendlyByteBuf buffer, IBuildingModule module)
	{
		super.toBuffer(buffer, module);

		buffer.writeUtf(this.getToolType().getName());
	}

	public IToolType getToolType()
	{
		return this.toolType;
	}

}
