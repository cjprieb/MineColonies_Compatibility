package steve_gall.minecolonies_compatibility.mixin.common.minecolonies;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.lothrazar.cyclic.util.FluidHelpers.FluidAttributes;
import com.minecolonies.api.colony.jobs.IJob;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.core.compatibility.jei.GenericRecipeCategory;
import com.minecolonies.core.compatibility.jei.JobBasedRecipeCategory;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.crafting.IRecipeSlotTooltipableGenericRecipe;
import steve_gall.minecolonies_compatibility.api.common.crafting.RecipeSlotRole;
import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingGenericRecipe;

@Mixin(value = GenericRecipeCategory.class, remap = false)
public abstract class GenericRecipeCategoryMixin extends JobBasedRecipeCategory<IGenericRecipe>
{
	protected GenericRecipeCategoryMixin(@NotNull IJob<?> job, @NotNull RecipeType<IGenericRecipe> type, @NotNull ItemStack icon, @NotNull IGuiHelper guiHelper)
	{
		super(job, type, icon, guiHelper);
	}

	@Unique
	private int minecolonies_compatibility$inputIndex = 0;
	@Unique
	private int minecolonies_compatibility$outputIndex = 0;
	@Unique
	private int minecolonies_compatibility$catalystIndex = 0;
	@Unique
	private IRecipeSlotTooltipableGenericRecipe minecolonies_compatibility$recipe = null;

	@Shadow(remap = false)
	private int outputSlotX;
	@Shadow(remap = false)
	private int outputSlotY;

	@Inject(method = "setNormalRecipe", remap = false, at = @At(value = "HEAD"), cancellable = true)
	private void setNormalRecipe_Head(@NotNull IRecipeLayoutBuilder builder, @NotNull IGenericRecipe recipe, @NotNull IFocusGroup focuses, CallbackInfo ci)
	{
		this.minecolonies_compatibility$inputIndex = 0;
		this.minecolonies_compatibility$outputIndex = 0;
		this.minecolonies_compatibility$catalystIndex = 0;
		this.minecolonies_compatibility$recipe = recipe instanceof IRecipeSlotTooltipableGenericRecipe genericRecipe ? genericRecipe : null;

		if (recipe instanceof BucketFillingGenericRecipe fillingRecipe)
		{
			var slot = builder.addSlot(RecipeIngredientRole.INPUT, this.outputSlotX, CITIZEN_Y + 1);
			slot.addFluidStack(fillingRecipe.getFluid(), FluidAttributes.BUCKET_VOLUME);
			slot.setFluidRenderer(FluidAttributes.BUCKET_VOLUME, false, 16, 16);
			slot.setBackground(this.slot, -1, -1);
		}

	}

	@Redirect(method = "setNormalRecipe", remap = false, at = @At(value = "INVOKE", target = "Lmezz/jei/api/gui/builder/IRecipeLayoutBuilder;addSlot"))
	private IRecipeSlotBuilder setNormalRecipe_addSlot(IRecipeLayoutBuilder builder, RecipeIngredientRole recipeIngredientRole, int x, int y)
	{
		var slotBuilder = builder.addSlot(recipeIngredientRole, x, y);

		if (this.minecolonies_compatibility$recipe != null)
		{
			RecipeSlotRole role = null;
			var index = -1;

			if (recipeIngredientRole == RecipeIngredientRole.INPUT)
			{
				role = RecipeSlotRole.INPUT;
				index = this.minecolonies_compatibility$inputIndex;
				this.minecolonies_compatibility$inputIndex++;
			}
			else if (recipeIngredientRole == RecipeIngredientRole.OUTPUT)
			{
				role = RecipeSlotRole.OUTPUT;
				index = this.minecolonies_compatibility$outputIndex;
				this.minecolonies_compatibility$outputIndex++;
			}
			else if (recipeIngredientRole == RecipeIngredientRole.CATALYST)
			{
				role = RecipeSlotRole.CATALYST;
				index = this.minecolonies_compatibility$catalystIndex;
				this.minecolonies_compatibility$catalystIndex++;
			}

			if (role != null && index > -1)
			{
				final var tooltipRole = role;
				final var toolTipIndex = index;
				slotBuilder.addTooltipCallback((recipeSlotView, tooltip) -> tooltip.addAll(1, this.minecolonies_compatibility$recipe.getRecipeSlotToolTip(tooltipRole, toolTipIndex)));
			}

		}

		return slotBuilder;
	}

}
