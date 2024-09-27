package steve_gall.minecolonies_compatibility.module.common.delightful;

import java.util.Collections;

import com.minecolonies.core.colony.crafting.LootTableAnalyzer;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.data.gen.DelightfulEntityTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import steve_gall.minecolonies_compatibility.api.common.event.AnimalHerdingLootEvent;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedFruit;
import steve_gall.minecolonies_compatibility.core.common.init.ModToolTypes;
import steve_gall.minecolonies_compatibility.module.common.AbstractModule;

public class DelightfulModule extends AbstractModule
{
	@Override
	protected void onLoad()
	{
		super.onLoad();

		var forge_bus = MinecraftForge.EVENT_BUS;
		forge_bus.addListener(this::onAnimalHerdingLoot);
	}

	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			CustomizedFruit.register(new SalmonberryFruit());
		});
	}

	private void onAnimalHerdingLoot(AnimalHerdingLootEvent e)
	{
		if (e.getRecipe().getRequiredTool() == ModToolTypes.KNIFE.getToolType())
		{
			var type = e.getRecipe().getRequiredEntity().getType();

			if (type.is(DelightfulEntityTags.FATTY_ANIMALS))
			{
				e.register(new LootTableAnalyzer.LootDrop(Collections.singletonList(new ItemStack(DelightfulItems.ANIMAL_FAT.get())), 0.6F, 1, false));
			}

		}

	}

}
