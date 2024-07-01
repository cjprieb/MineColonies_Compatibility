package steve_gall.minecolonies_compatibility.api.common.inventory;

import net.minecraftforge.fluids.FluidStack;

public interface IFluidGhostMenu
{
	void onGhostAcceptFluid(int slotNumber, FluidStack stack);
}
