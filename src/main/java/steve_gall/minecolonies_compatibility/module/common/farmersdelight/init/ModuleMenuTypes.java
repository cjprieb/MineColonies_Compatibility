package steve_gall.minecolonies_compatibility.module.common.farmersdelight.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.FarmersCookingTeachMenu;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.FarmersCuttingTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<MenuType<FarmersCuttingTeachMenu>> FARMERS_CUTTING_TEACH = REGISTER.register("farmers_cutting_teach", () -> IForgeMenuType.create(FarmersCuttingTeachMenu::new));
	public static final RegistryObject<MenuType<FarmersCookingTeachMenu>> FARMERS_COOKING_TEACH = REGISTER.register("farmers_cooking_teach", () -> IForgeMenuType.create(FarmersCookingTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
