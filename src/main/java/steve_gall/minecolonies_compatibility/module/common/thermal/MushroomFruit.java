package steve_gall.minecolonies_compatibility.module.common.thermal;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import cofh.lib.block.CropBlockMushroom;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedFruit;
import steve_gall.minecolonies_compatibility.api.common.plant.HarvesterContext;
import steve_gall.minecolonies_compatibility.api.common.plant.PlantBlockContext;
import steve_gall.minecolonies_compatibility.mixin.common.minecraft.CropBlockAccessor;

public class MushroomFruit extends CustomizedFruit
{
	private final CropBlockMushroom block;

	public MushroomFruit(CropBlockMushroom block)
	{
		this.block = block;
	}

	@Override
	public @NotNull ResourceLocation getId()
	{
		return ForgeRegistries.BLOCKS.getKey(this.block);
	}

	@Override
	public @NotNull List<ItemStack> getBlockIcons()
	{
		var seed = ((CropBlockAccessor) this.block).invokeGetBaseSeedId();

		if (this.block.asItem() == Items.AIR || this.block.asItem() == seed)
		{
			return Arrays.asList(new ItemStack(seed));
		}
		else
		{
			return Arrays.asList(new ItemStack(seed), new ItemStack(this.block));
		}

	}

	@Override
	public @NotNull List<ItemStack> getItemIcons()
	{
		return Arrays.asList(new ItemStack(this.block));
	}

	@Override
	public boolean test(@NotNull PlantBlockContext context)
	{
		return context.getState().getBlock() == this.block;
	}

	@Override
	public boolean canHarvest(@NotNull PlantBlockContext context)
	{
		return this.block.canHarvest(context.getState());
	}

	@Override
	public boolean isMaxHarvest(@NotNull PlantBlockContext context)
	{
		return this.block.isMaxAge(context.getState());
	}

	@Override
	public @NotNull List<ItemStack> harvest(@NotNull PlantBlockContext context, @NotNull HarvesterContext harvester)
	{
		return ThermalModule.harvest(context, harvester);
	}

}
