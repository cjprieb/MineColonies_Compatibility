package steve_gall.minecolonies_compatibility.core.common.inventory;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import steve_gall.minecolonies_tweaks.api.common.building.module.ModulePos;

public abstract class ModuleMenu extends BaseMenu
{
	protected final ModulePos modulePos;

	protected ModuleMenu(MenuType<?> menuType, int windowId, Inventory inventory, IBuildingModule module)
	{
		super(menuType, windowId, inventory);
		this.modulePos = new ModulePos(module);
	}

	protected ModuleMenu(MenuType<?> menuType, int windowId, Inventory inventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, inventory);
		this.modulePos = new ModulePos(buffer);
	}

	public ModulePos getModulePos()
	{
		return this.modulePos;
	}

}
