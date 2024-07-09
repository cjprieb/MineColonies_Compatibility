package steve_gall.minecolonies_compatibility.core.common.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class TeachContainer implements Container
{
	private final NonNullList<ItemStack> items;
	private final AbstractContainerMenu menu;

	public TeachContainer(AbstractContainerMenu menu, int count)
	{
		this.items = NonNullList.withSize(count, ItemStack.EMPTY);
		this.menu = menu;
	}

	@Override
	public int getContainerSize()
	{
		return this.items.size();
	}

	@Override
	public boolean isEmpty()
	{
		for (var itemstack : this.items)
		{
			if (!itemstack.isEmpty())
			{
				return false;
			}

		}

		return true;
	}

	@Override
	public ItemStack getItem(int slot)
	{
		return slot >= this.getContainerSize() ? ItemStack.EMPTY : this.items.get(slot);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot)
	{
		return ContainerHelper.takeItem(this.items, slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount)
	{
		return ContainerHelper.removeItem(this.items, slot, amount);
	}

	@Override
	public void setItem(int slot, ItemStack stack)
	{
		this.items.set(slot, stack);
	}

	@Override
	public void setChanged()
	{
		this.menu.slotsChanged(this);
	}

	@Override
	public boolean stillValid(Player player)
	{
		return true;
	}

	@Override
	public void clearContent()
	{
		this.items.clear();
	}

}
