package steve_gall.minecolonies_compatibility.module.common.farmersdelight.init;

import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.crafting.CookingCraftingType;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.crafting.CuttingCraftingType;
import steve_gall.minecolonies_tweaks.api.registries.DeferredRegisterHelper;

public class ModuleCraftingTypes
{
	public static final DeferredRegister<CraftingType> REGISTER = DeferredRegisterHelper.craftingTypes(MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<CuttingCraftingType> CUTTING = DeferredRegisterHelper.registerCraftingType(REGISTER, "farmers_cutting", CuttingCraftingType::new);
	public static final RegistryObject<CookingCraftingType> COOKING = DeferredRegisterHelper.registerCraftingType(REGISTER, "farmers_cooking", CookingCraftingType::new);

	private ModuleCraftingTypes()
	{

	}

}
