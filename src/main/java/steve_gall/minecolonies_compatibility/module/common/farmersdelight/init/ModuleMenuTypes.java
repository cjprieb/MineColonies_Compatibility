package steve_gall.minecolonies_compatibility.module.common.farmersdelight.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.CookingTeachMenu;
import steve_gall.minecolonies_compatibility.module.common.farmersdelight.menu.CuttingTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<MenuType<CuttingTeachMenu>> CUTTING_TEACH = REGISTER.register("farmers_cutting_teach", () -> IForgeMenuType.create(CuttingTeachMenu::new));
	public static final RegistryObject<MenuType<CookingTeachMenu>> COOKING_TEACH = REGISTER.register("farmers_cooking_teach", () -> IForgeMenuType.create(CookingTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
