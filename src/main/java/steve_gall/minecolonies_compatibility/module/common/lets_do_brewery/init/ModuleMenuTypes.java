package steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_brewery.menu.SiloTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<MenuType<SiloTeachMenu>> SILO_TEACH = REGISTER.register("lets_do_brewery_silo_teach", () -> IForgeMenuType.create(SiloTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
