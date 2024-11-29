package steve_gall.minecolonies_compatibility.module.common.fruitfulfun;

import snownee.fruits.block.FruitLeavesBlock;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedFruit;
import steve_gall.minecolonies_compatibility.module.common.AbstractModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FruitfulFunModule extends AbstractModule
{
	public static final Logger LOGGER = LogManager.getLogger();

	public static String FRUIT_PREFIX = "fruitfulfun";
	public static String MOD_ID = "fruitfulfun";

	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			// LOGGER.debug("cjp-fruitfulfun");
			for (var block : ForgeRegistries.BLOCKS.getValues())
			{
				if (block instanceof FruitLeavesBlock fruitBlock)
				// if (id.getNamespace().equals(MOD_ID) && id.getPath().startsWith(FRUIT_PREFIX))
				{
					// LOGGER.debug("\tfound matching fruit: " + fruitBlock);
					// var block = ForgeRegistries.BLOCKS.getValue(id);
					// var name = fruitBlock.getPath().substring(FRUIT_PREFIX.length());
					var fruit = fruitBlock.type.get().fruit.get();
					var sapling = fruitBlock.type.get().sapling.get();
					// var sapling = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(MOD_ID, name + "_sapling"));
					CustomizedFruit.register(new FruitfulFunFruit(block, sapling, fruit));
				}

			}

		});
	}

}
