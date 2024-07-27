package steve_gall.minecolonies_compatibility.module.common.thermal;

import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import cofh.lib.common.block.CropBlockCoFH;
import cofh.lib.common.block.CropBlockMushroom;
import cofh.lib.util.helpers.MathHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedCrop;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedFruit;
import steve_gall.minecolonies_compatibility.api.common.plant.HarvesterContext;
import steve_gall.minecolonies_compatibility.api.common.plant.PlantBlockContext;
import steve_gall.minecolonies_compatibility.mixin.common.thermal.CropBlockCoFHAccessor;
import steve_gall.minecolonies_compatibility.module.common.AbstractModule;

public class ThermalModule extends AbstractModule
{
	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			CustomizedCrop.register(new PerennialCrop());

			for (var block : ForgeRegistries.BLOCKS.getValues())
			{
				if (block instanceof CropBlockMushroom mushroom)
				{
					CustomizedFruit.register(new MushroomFruit(mushroom));
				}

			}
		});
	}

	public static List<ItemStack> harvest(@NotNull PlantBlockContext context, @NotNull HarvesterContext harvester)
	{
		if (context.getLevel() instanceof LevelWriter level)
		{
			var state = context.getState();
			var block = (CropBlockCoFH) state.getBlock();
			var accessor = (CropBlockCoFHAccessor) block;
			var postHarvestAge = accessor.invokeGetPostHarvestAge();

			if (postHarvestAge >= 0)
			{
				var fortune = 0;
				var drop = new ItemStack(accessor.invokeGetCropItem(), 2 + MathHelper.binomialDist(fortune, 0.5D));
				level.setBlock(context.getPosition(), block.getStateForAge(postHarvestAge), Block.UPDATE_CLIENTS);
				return Collections.singletonList(drop);
			}
			else
			{
				var drops = context.getDrops(harvester);
				PlantBlockContext.replant(context, drops, block.getStateForAge(0));
				return drops;
			}

		}
		else
		{
			return Collections.emptyList();
		}

	}

}
