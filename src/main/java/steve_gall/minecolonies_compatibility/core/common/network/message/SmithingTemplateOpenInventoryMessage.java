package steve_gall.minecolonies_compatibility.core.common.network.message;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import steve_gall.minecolonies_compatibility.core.common.building.module.SmithingTemplateCraftingModule;
import steve_gall.minecolonies_compatibility.core.common.inventory.SmithingTemplateInventoryMenu;

public class SmithingTemplateOpenInventoryMessage extends ModuleMenuOpenMessage
{
	public SmithingTemplateOpenInventoryMessage(IBuildingModuleView module)
	{
		super(module);
	}

	public SmithingTemplateOpenInventoryMessage(FriendlyByteBuf buffer)
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
		return new SmithingTemplateInventoryMenu(windowId, inventory, (SmithingTemplateCraftingModule) module);
	}

	@Override
	protected Component getDisplayName()
	{
		return Component.translatable("minecolonies_compatibility.text.smithing_template_inventory");
	}

	@Override
	protected void toBuffer(FriendlyByteBuf buffer, IBuildingModule module)
	{
		super.toBuffer(buffer, module);
	}

}
