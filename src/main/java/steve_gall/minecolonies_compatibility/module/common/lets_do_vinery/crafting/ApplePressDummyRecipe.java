package steve_gall.minecolonies_compatibility.module.common.lets_do_vinery.crafting;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import steve_gall.minecolonies_compatibility.core.common.item.ItemStackHelper;

public class ApplePressDummyRecipe implements Recipe<Container>
{
	private final ResourceLocation id;
	private final Item ingredient;
	private final ItemStack result;

	public ApplePressDummyRecipe(Item ingredient, ItemStack result)
	{
		var ingredientId = ForgeRegistries.ITEMS.getKey(ingredient);
		this.id = new ResourceLocation(ForgeRegistries.ITEMS.getKey(result.getItem()).getPath(), ingredientId.getNamespace() + "_" + ingredientId.getPath());
		this.ingredient = ingredient;
		this.result = result.copy();
	}

	public ApplePressDummyRecipe(CompoundTag tag)
	{
		this.id = new ResourceLocation(tag.getString("id"));
		this.ingredient = ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString("ingredient")));
		this.result = ItemStack.of(tag.getCompound("result"));
	}

	@Override
	public int hashCode()
	{
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		else if (obj == null)
		{
			return false;
		}
		else if (obj instanceof ApplePressDummyRecipe other)
		{
			return this.ingredient == other.ingredient && ItemStackHelper.equals(this.result, other.result);
		}
		else
		{
			return false;
		}

	}

	public CompoundTag serializeNBT()
	{
		var tag = new CompoundTag();
		tag.putString("id", this.id.toString());
		tag.putString("ingredient", ForgeRegistries.ITEMS.getKey(this.ingredient).toString());
		tag.put("result", this.result.serializeNBT());
		return tag;
	}

	@Override
	public NonNullList<Ingredient> getIngredients()
	{
		var i = Ingredient.of(this.ingredient);
		return NonNullList.of(i, i);
	}

	@Override
	public boolean matches(Container p_44002_, Level p_44003_)
	{
		return this.ingredient == p_44002_.getItem(0).getItem();
	}

	@Override
	public ItemStack assemble(Container p_44001_)
	{
		return this.getResultItem();
	}

	@Override
	public boolean canCraftInDimensions(int p_43999_, int p_44000_)
	{
		return true;
	}

	@Override
	public ItemStack getResultItem()
	{
		return this.result.copy();
	}

	@Override
	public ResourceLocation getId()
	{
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return null;
	}

	@Override
	public RecipeType<?> getType()
	{
		return null;
	}

}
