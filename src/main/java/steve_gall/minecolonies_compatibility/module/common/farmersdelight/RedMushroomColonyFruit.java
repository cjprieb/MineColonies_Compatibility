package steve_gall.minecolonies_compatibility.module.common.farmersdelight;

import org.jetbrains.annotations.NotNull;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class RedMushroomColonyFruit extends MushroomColonyFruit
{
	@Override
	public @NotNull ResourceLocation getId()
	{
		return ModBlocks.RED_MUSHROOM_COLONY.getId();
	}

	@Override
	public Block getBlock()
	{
		return ModBlocks.RED_MUSHROOM_COLONY.get();
	}

	@Override
	public Item getItem()
	{
		return Items.RED_MUSHROOM;
	}

}
