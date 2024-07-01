package steve_gall.minecolonies_compatibility.api.client.jei;

public class ItemGhostVirtualTarget extends ItemGhostTarget
{
	public ItemGhostVirtualTarget(int x, int y, int slotNumber)
	{
		super(x, y, slotNumber);
	}

	@Override
	public boolean isVirtual()
	{
		return true;
	}

}
