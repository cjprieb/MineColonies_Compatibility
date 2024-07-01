package steve_gall.minecolonies_compatibility.api.client.jei;

import net.minecraft.client.renderer.Rect2i;

public abstract class GhostTarget<I>
{
	private final int x;
	private final int y;
	private final int slotNumber;

	public GhostTarget(int x, int y, int slotNumber)
	{
		this.x = x;
		this.y = y;
		this.slotNumber = slotNumber;
	}

	public abstract void accept(I ingredient);

	public int getSlotNumber()
	{
		return this.slotNumber;
	}

	public Rect2i getArea()
	{
		return new Rect2i(this.x, this.y, 16, 16);
	}

}
