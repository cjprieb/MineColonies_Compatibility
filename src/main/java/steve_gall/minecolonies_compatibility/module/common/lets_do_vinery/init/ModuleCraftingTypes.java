package steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.init;

import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.crafting.ApplePressCraftingType;
import steve_gall.minecolonies_tweaks.api.registries.DeferredRegisterHelper;

public class ModuleCraftingTypes
{
	public static final DeferredRegister<CraftingType> REGISTER = DeferredRegisterHelper.craftingTypes(MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<CraftingType> APPLE_PRESS = DeferredRegisterHelper.registerCraftingType(REGISTER, "lets_do_vinery_apple_press", ApplePressCraftingType::new);

	private ModuleCraftingTypes()
	{

	}

}
