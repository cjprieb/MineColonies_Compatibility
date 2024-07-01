package steve_gall.minecolonies_compatibility.api.client.jei;

import net.minecraftforge.fluids.FluidStack;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.core.common.network.message.JEIGhostAcceptFluidMessage;

public class FluidGhostTarget extends GhostTarget<FluidStack>
{
	public FluidGhostTarget(int x, int y, int slotNumber)
	{
		super(x, y, slotNumber);
	}

	@Override
	public void accept(FluidStack ingredient)
	{
		MineColoniesCompatibility.network().sendToServer(new JEIGhostAcceptFluidMessage(this.getSlotNumber(), ingredient));
	}

}
