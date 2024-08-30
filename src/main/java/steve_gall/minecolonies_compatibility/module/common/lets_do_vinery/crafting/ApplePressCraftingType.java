package steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import satisfyu.vinery.registry.ObjectRegistry;

public class ApplePressCraftingType extends CraftingType
{
	public ApplePressCraftingType(@NotNull ResourceLocation id)
	{
		super(id);
	}

	@Override
	public @NotNull List<IGenericRecipe> findRecipes(@NotNull RecipeManager recipeManager, @Nullable Level world)
	{
		var list = new ArrayList<IGenericRecipe>();

		for (var pair : getRecipes().entrySet())
		{
			list.add(new ApplePressGenericRecipe(new ApplePressDummyRecipe(pair.getKey(), pair.getValue())));
		}

		return list;
	}

	public static Map<Item, ItemStack> getRecipes()
	{
		var map = new HashMap<Item, ItemStack>();
		map.put(Items.APPLE, new ItemStack(ObjectRegistry.APPLE_MASH.get(), 1));
		return map;
	}

}
