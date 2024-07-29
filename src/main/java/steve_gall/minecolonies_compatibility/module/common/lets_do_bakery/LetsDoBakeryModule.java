package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery;

import com.minecolonies.api.colony.buildings.ModBuildings;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.BakingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.CookingTeachScreen;
import steve_gall.minecolonies_compatibility.module.client.lets_do_bakery.StoveTeachScreen;
import steve_gall.minecolonies_compatibility.module.common.AbstractModule;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting.BakingRecipeStorage;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting.CookingRecipeStorage;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting.StoveRecipeStorage;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.init.ModuleBuildingModules;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.init.ModuleCraftingTypes;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.init.ModuleMenuTypes;
import steve_gall.minecolonies_tweaks.api.common.crafting.CustomizedRecipeStorageRegistry;

public class LetsDoBakeryModule extends AbstractModule
{
	@Override
	protected void onLoad()
	{
		super.onLoad();

		var fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModuleCraftingTypes.REGISTER.register(fml_bus);
		ModuleMenuTypes.REGISTER.register(fml_bus);

		CustomizedRecipeStorageRegistry.INSTANCE.register(StoveRecipeStorage.ID, (tag, storage) -> storage.serialize(tag), StoveRecipeStorage::new);
		CustomizedRecipeStorageRegistry.INSTANCE.register(BakingRecipeStorage.ID, (tag, storage) -> storage.serialize(tag), BakingRecipeStorage::new);
		CustomizedRecipeStorageRegistry.INSTANCE.register(CookingRecipeStorage.ID, (tag, storage) -> storage.serialize(tag), CookingRecipeStorage::new);
	}

	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			ModBuildings.bakery.get().getModuleProducers().add(ModuleBuildingModules.BAKER_STOVE);
			ModBuildings.bakery.get().getModuleProducers().add(ModuleBuildingModules.BAKER_BAKING);
			ModBuildings.cook.get().getModuleProducers().add(ModuleBuildingModules.COOKASSISTANT_COOKING);
		});
	}

	@Override
	protected void onFMLClientSetup(FMLClientSetupEvent e)
	{
		super.onFMLClientSetup(e);

		MenuScreens.register(ModuleMenuTypes.STOVE_TEACH.get(), StoveTeachScreen::new);
		MenuScreens.register(ModuleMenuTypes.BAKING_TEACH.get(), BakingTeachScreen::new);
		MenuScreens.register(ModuleMenuTypes.COOKING_TEACH.get(), CookingTeachScreen::new);
	}

}
