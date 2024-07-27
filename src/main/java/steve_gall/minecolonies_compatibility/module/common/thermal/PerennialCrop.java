package steve_gall.minecolonies_compatibility.module.common.thermal;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cofh.lib.common.block.CropBlockPerennial;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedCrop;
import steve_gall.minecolonies_compatibility.api.common.plant.HarvesterContext;
import steve_gall.minecolonies_compatibility.api.common.plant.PlantBlockContext;

public class PerennialCrop extends CustomizedCrop
{
	@Override
	public boolean isCrop(@NotNull PlantBlockContext context)
	{
		return context.getState().getBlock() instanceof CropBlockPerennial;
	}

	@Override
	public @Nullable SpecialHarvestPositionFunction getSpecialHarvestPosition(@NotNull PlantBlockContext context)
	{
		return this::getHarvestPosition;
	}

	@Override
	public @Nullable SpecialHarvestMethodFunction getSpecialHarvestMethod(@NotNull PlantBlockContext context)
	{
		return this::harvest;
	}

	private BlockPos getHarvestPosition(@NotNull PlantBlockContext context)
	{
		var state = context.getState();

		if (state.getBlock() instanceof CropBlockPerennial block && block.canHarvest(state))
		{
			return context.getPosition();
		}
		else
		{
			return null;
		}

	}

	private List<ItemStack> harvest(@NotNull PlantBlockContext context, @NotNull HarvesterContext harvester)
	{
		return ThermalModule.harvest(context, harvester);
	}

}
