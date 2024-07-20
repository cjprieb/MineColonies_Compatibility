package steve_gall.minecolonies_compatibility.api.common.crafting;

import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.crafting.IGenericRecipe;

import net.minecraft.network.chat.Component;

public interface IRecipeSlotModifiableGenericRecipe extends IGenericRecipe
{
	default boolean isRecipeSlotOptional(@NotNull RecipeSlotRole role, int index)
	{
		return false;
	}

	@NotNull
	default List<Component> getRecipeSlotToolTip(@NotNull RecipeSlotRole role, int index)
	{
		return Collections.emptyList();
	}

}
