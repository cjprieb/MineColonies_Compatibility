package steve_gall.minecolonies_compatibility.api.client.jei;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.inventory.Slot;

public class ItemGhostSlotTarget extends ItemGhostTarget
{
	private final Slot slot;

	public ItemGhostSlotTarget(@NotNull Slot slot, int slotNumber)
	{
		super(slot.x, slot.y, slotNumber);

		this.slot = slot;
	}

	@NotNull
	public Slot getSlot()
	{
		return this.slot;
	}

	@Override
	public boolean isVirtual()
	{
		return false;
	}

}
