package steve_gall.minecolonies_compatibility.module.common.lets_do_bakery.crafting;

import java.util.ArrayList;
import java.util.List;

import com.minecolonies.api.colony.requestsystem.StandardFactoryController;
import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleRecipeStorage;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;

public class CookingRecipeStorage extends SimpleRecipeStorage<CookingGenericRecipe>
{
	public static final ResourceLocation ID = MineColoniesCompatibility.rl("lets_do_bakery_cooking");

	private final ItemStorage container;

	public CookingRecipeStorage(CompoundTag tag)
	{
		super(tag);

		this.container = StandardFactoryController.getInstance().deserialize(tag.getCompound("container"));
	}

	public CookingRecipeStorage(ResourceLocation recipeId, List<ItemStorage> ingredients, ItemStorage container, ItemStack output)
	{
		super(recipeId, ingredients, output);

		this.container = container;
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	public List<ItemStorage> getInput()
	{
		var input = new ArrayList<>(super.getInput());

		if (!this.container.isEmpty())
		{
			input.add(this.container);
		}

		return input;
	}

	@Override
	public void serialize(CompoundTag tag)
	{
		super.serialize(tag);

		tag.put("container", StandardFactoryController.getInstance().serialize(this.container));
	}

	@Override
	protected GenericRecipeFactory<CookingGenericRecipe> getGenericRecipeFactory()
	{
		return (recipeId, ingredients, output) -> new CookingGenericRecipe(recipeId, ingredients, this.container.getItemStack(), output);
	}

}
