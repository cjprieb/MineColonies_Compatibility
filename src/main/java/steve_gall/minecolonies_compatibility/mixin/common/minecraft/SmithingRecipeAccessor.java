package steve_gall.minecolonies_compatibility.mixin.common.minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.UpgradeRecipe;

@Mixin(value = UpgradeRecipe.class, remap = true)
public interface SmithingRecipeAccessor
{
	@Accessor(value = "base", remap = true)
	Ingredient getBase();

	@Accessor(value = "addition", remap = true)
	Ingredient getAddition();
}
