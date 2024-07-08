package steve_gall.minecolonies_compatibility.core.common.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.inventory.IItemGhostSlot;

public class TeachInputSlot extends Slot implements IItemGhostSlot
{
	public TeachInputSlot(Container container, int slot, int x, int y)
	{
		super(container, slot, x, y);
	}

	@Override
	public boolean mayPickup(Player player)
	{
		return false;
	}

	@Override
	public boolean mayPlace(ItemStack stack)
	{
		return false;
	}

}
