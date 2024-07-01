package steve_gall.minecolonies_compatibility.api.common.inventory;

import net.minecraft.world.item.ItemStack;

public interface IItemGhostMenu
{
	void onGhostAcceptItem(int slotNumber, ItemStack stack, boolean isVirtual);
}
