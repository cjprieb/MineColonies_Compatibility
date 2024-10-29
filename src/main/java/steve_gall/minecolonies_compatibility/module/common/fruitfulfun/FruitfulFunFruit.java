package steve_gall.minecolonies_compatibility.module.common.fruitfulfun;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import snownee.fruits.block.FruitLeavesBlock;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.ForgeRegistries;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedFruit;
import steve_gall.minecolonies_compatibility.api.common.plant.HarvesterContext;
import steve_gall.minecolonies_compatibility.api.common.plant.PlantBlockContext;

public class FruitfulFunFruit extends CustomizedFruit
{
	private final Block block;
	private final Block sapling;
	private final Item fruit;

	public FruitfulFunFruit(Block block, Block sapling, Item fruit)
	{
		this.block = block;
		this.sapling = sapling;
		this.fruit = fruit;
	}

	@Override
	public @NotNull ResourceLocation getId()
	{
		return ForgeRegistries.BLOCKS.getKey(this.block);
	}

	@Override
	public @NotNull List<ItemStack> getBlockIcons()
	{
		return Arrays.asList(new ItemStack(this.sapling), new ItemStack(this.block));
	}

	@Override
	public @NotNull List<ItemStack> getItemIcons()
	{
		return Arrays.asList(new ItemStack(this.fruit));
	}

	@Override
	public boolean test(@NotNull PlantBlockContext context)
	{
		return context.getState().getBlock() == this.block;
	}

	@Override
	public boolean canHarvest(@NotNull PlantBlockContext context)
	{
		var state = context.getState();
		var block = state.getBlock();

		if (block instanceof FruitLeavesBlock fruit && state.getValue(BlockStateProperties.AGE_3) == 3)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	@Override
	public boolean isMaxHarvest(@NotNull PlantBlockContext context)
	{
		return true;
	}

	@Override
	@NotNull
	public List<ItemStack> harvest(@NotNull PlantBlockContext context, @NotNull HarvesterContext harvester)
	{
		if (context.getLevel() instanceof LevelWriter level)
		{
			var newState = context.getState().getBlock().defaultBlockState();
			level.setBlock(context.getPosition(), newState, Block.UPDATE_CLIENTS);
		}

		return context.getDrops(null);
	}

}
