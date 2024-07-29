package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.init;

import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import satisfy.bakery.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleCraftingType;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting.BakingGenericRecipe;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting.CookingGenericRecipe;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting.StoveGenericRecipe;
import steve_gall.minecolonies_tweaks.api.registries.DeferredRegisterHelper;

public class ModuleCraftingTypes
{
	public static final DeferredRegister<CraftingType> REGISTER = DeferredRegisterHelper.craftingTypes(MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<CraftingType> STOVE = DeferredRegisterHelper.registerCraftingType(REGISTER, "lets_do_bakery_stove", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.STOVE_RECIPE_TYPE, StoveGenericRecipe::new));
	public static final RegistryObject<CraftingType> BAKING = DeferredRegisterHelper.registerCraftingType(REGISTER, "lets_do_bakery_baking", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.BAKER_STATION_RECIPE_TYPE, BakingGenericRecipe::new));
	public static final RegistryObject<CraftingType> COOKING = DeferredRegisterHelper.registerCraftingType(REGISTER, "lets_do_bakery_cooking", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE, CookingGenericRecipe::new));

	private ModuleCraftingTypes()
	{

	}

}
