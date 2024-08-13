package steve_gall.minecolonies_compatibility.core.common.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class InteractionMessageHelper
{
	public static final Component WORKING_BLOCK_NOT_FOUND = Component.translatable("minecolonies_compatibility.interaction.no_working_block");

	public static Component getWorkingBlockNotFound()
	{
		return WORKING_BLOCK_NOT_FOUND;
	}

	public static Component getWorkingBlockNotFound(Block block)
	{
		var key = ForgeRegistries.BLOCKS.getKey(block);
		var modDisplayName = ModList.get().getModContainerById(key.getNamespace()).map(c -> c.getModInfo().getDisplayName()).orElse(key.getNamespace());
		return Component.translatable("minecolonies_compatibility.interaction.no_working_block_2", modDisplayName, Component.translatable(block.getDescriptionId()));
	}

	public static Component getWorkingBlockAndUnderHeatSourceNotFound(Block block)
	{
		var key = ForgeRegistries.BLOCKS.getKey(block);
		var modDisplayName = ModList.get().getModContainerById(key.getNamespace()).map(c -> c.getModInfo().getDisplayName()).orElse(key.getNamespace());
		return Component.translatable("minecolonies_compatibility.interaction.no_working_block_and_under_heat_source", modDisplayName, Component.translatable(block.getDescriptionId()));
	}

	private InteractionMessageHelper()
	{

	}

}
