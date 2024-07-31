package steve_gall.minecolonies_compatibility.module.common.farmersdelight;

import java.util.Collections;
import java.util.List;

import com.minecolonies.api.colony.buildings.ModBuildings;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedCrop;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedFruit;
import steve_gall.minecolonies_compatibility.module.client.farmersdelight.CookingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.farmersdelight.CuttingTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.AbstractModule;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.crafting.CookingRecipeStorage;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.crafting.CuttingRecipeStorage;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.init.ModuleBuildingModules;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.init.ModuleCraftingTypes;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.init.ModuleJobs;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.init.ModuleMenuTypes;
import steve_gall.minecolonies_tweaks.api.common.crafting.CustomizedRecipeStorageRegistry;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

public class FarmersDelightModule extends AbstractModule
{
	@Override
	protected void onLoad()
	{
		super.onLoad();

		var fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModuleCraftingTypes.REGISTER.register(fml_bus);
		ModuleMenuTypes.REGISTER.register(fml_bus);
		ModuleJobs.REGISTER.register(fml_bus);

		CustomizedRecipeStorageRegistry.INSTANCE.register(CuttingRecipeStorage.ID, CuttingRecipeStorage::serialize, CuttingRecipeStorage::deserialize);
		CustomizedRecipeStorageRegistry.INSTANCE.register(CookingRecipeStorage.ID, CookingRecipeStorage::serialize, CookingRecipeStorage::deserialize);
	}

	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			CustomizedCrop.register(new TomatoCrop());

			for (var block : ForgeRegistries.BLOCKS.getValues())
			{
				if (block instanceof MushroomColonyBlock mushroomColony)
				{
					CustomizedFruit.register(new MushroomColonyFruit(mushroomColony));
				}

			}

			CustomizedFruit.register(new RiceFruit());

			ModBuildings.kitchen.get().getModuleProducers().add(ModuleBuildingModules.CHEF_CUTTING);
			ModBuildings.kitchen.get().getModuleProducers().add(ModuleBuildingModules.CHEF_COOKING);
		});
	}

	@Override
	protected void onFMLClientSetup(FMLClientSetupEvent e)
	{
		super.onFMLClientSetup(e);

		MenuScreens.register(ModuleMenuTypes.CUTTING_TEACH.get(), CuttingTeachScreen::new);
		MenuScreens.register(ModuleMenuTypes.COOKING_TEACH.get(), CookingTeachScreen::new);
	}

	public static List<Component> getChanceTooltip(float chance)
	{
		if (chance != 1.0F)
		{
			return Collections.singletonList(createChanceTooltip(chance));
		}
		else
		{
			return Collections.emptyList();
		}

	}

	public static Component createChanceTooltip(float chance)
	{
		return Component.translatable("farmersdelight.jei.chance", chance < 0.01 ? "<1" : (int) (chance * 100)).withStyle(ChatFormatting.GOLD);
	}

}
