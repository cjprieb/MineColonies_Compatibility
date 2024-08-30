package steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.menu.ApplePressTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<MenuType<ApplePressTeachMenu>> APPLE_PRESS_TEACH = REGISTER.register("lets_do_vinery_apple_press_teach", () -> IForgeMenuType.create(ApplePressTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
