package steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.init;

import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.satisfy.brewery.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleCraftingType;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.crafting.SiloGenericRecipe;
import steve_gall.minecolonies_tweaks.api.registries.DeferredRegisterHelper;

public class ModuleCraftingTypes
{
	public static final DeferredRegister<CraftingType> REGISTER = DeferredRegisterHelper.craftingTypes(MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<CraftingType> SILO = DeferredRegisterHelper.registerCraftingType(REGISTER, "lets_do_brewery_silo", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.SILO_RECIPE_TYPE, SiloGenericRecipe::new));

	private ModuleCraftingTypes()
	{

	}

}
