package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.menu.BakingTeachMenu;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.menu.BowlTeachMenu;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.menu.CookingTeachMenu;
import steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.menu.StoveTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineColoniesCompatibility.MOD_ID);
	public static final RegistryObject<MenuType<StoveTeachMenu>> STOVE_TEACH = REGISTER.register("lets_do_bakery_stove_teach", () -> IForgeMenuType.create(StoveTeachMenu::new));
	public static final RegistryObject<MenuType<BowlTeachMenu>> BOWL_TEACH = REGISTER.register("lets_do_bakery_bowl_teach", () -> IForgeMenuType.create(BowlTeachMenu::new));
	public static final RegistryObject<MenuType<BakingTeachMenu>> BAKING_TEACH = REGISTER.register("lets_do_bakery_baking_teach", () -> IForgeMenuType.create(BakingTeachMenu::new));
	public static final RegistryObject<MenuType<CookingTeachMenu>> COOKING_TEACH = REGISTER.register("lets_do_bakery_cooking_teach", () -> IForgeMenuType.create(CookingTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
