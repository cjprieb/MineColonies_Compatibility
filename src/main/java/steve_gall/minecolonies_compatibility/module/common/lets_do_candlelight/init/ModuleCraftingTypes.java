package steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.init;

import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import satisfyu.candlelight.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleCraftingType;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.crafting.PanGenericRecipe;
import steve_gall.minecolonies_compatibility.module.common.lets_do_candlelight.crafting.PotGenericRecipe;
import steve_gall.minecolonies_tweaks.api.registries.DeferredRegisterHelper;

public class ModuleCraftingTypes
{
	public static final DeferredRegister<CraftingType> REGISTER = DeferredRegisterHelper.craftingTypes(MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<CraftingType> PAN = DeferredRegisterHelper.registerCraftingType(REGISTER, "lets_do_candlelight_pan", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.COOKING_PAN_RECIPE_TYPE, PanGenericRecipe::new));
	public static final RegistryObject<CraftingType> POT = DeferredRegisterHelper.registerCraftingType(REGISTER, "lets_do_candlelight_pot", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE, PotGenericRecipe::new));

	private ModuleCraftingTypes()
	{

	}

}
