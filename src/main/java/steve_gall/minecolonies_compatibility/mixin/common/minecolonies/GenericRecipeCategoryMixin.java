package steve_gall.minecolonies_compatibility.mixin.common.minecolonies;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.minecolonies.api.colony.jobs.IJob;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.core.colony.crafting.LootTableAnalyzer;
import com.minecolonies.core.compatibility.jei.GenericRecipeCategory;
import com.minecolonies.core.compatibility.jei.JobBasedRecipeCategory;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidType;
import steve_gall.minecolonies_compatibility.api.common.crafting.IRecipeSlotModifiableGenericRecipe;
import steve_gall.minecolonies_compatibility.api.common.crafting.RecipeSlotRole;
import steve_gall.minecolonies_compatibility.api.common.event.AnimalHerdingLootEvent;
import steve_gall.minecolonies_compatibility.core.common.crafting.AnimalHerdingLootGenericRecipe;
import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingGenericRecipe;

@Mixin(value = GenericRecipeCategory.class, remap = false)
public abstract class GenericRecipeCategoryMixin extends JobBasedRecipeCategory<IGenericRecipe>
{
	protected GenericRecipeCategoryMixin(@NotNull IJob<?> job, @NotNull RecipeType<IGenericRecipe> type, @NotNull ItemStack icon, @NotNull IGuiHelper guiHelper)
	{
		super(job, type, icon, guiHelper);
	}

	@Unique
	private RecipeSlotRole minecolonies_compatibility$role = null;
	@Unique
	private int minecolonies_compatibility$roleIndex = 0;

	@Unique
	private int minecolonies_compatibility$inputIndex = 0;
	@Unique
	private int minecolonies_compatibility$outputIndex = 0;
	@Unique
	private int minecolonies_compatibility$catalystIndex = 0;
	@Unique
	private IRecipeSlotModifiableGenericRecipe minecolonies_compatibility$recipe = null;
	@Unique
	private AnimalHerdingLootGenericRecipe minecolonies_compatibility$animalHerdingLoot = null;

	@Shadow(remap = false)
	private int outputSlotX;
	@Shadow(remap = false)
	private int outputSlotY;

	@Shadow(remap = false)
	private static List<LootTableAnalyzer.LootDrop> getLootDrops(@NotNull ResourceLocation lootTableId)
	{
		throw new AssertionError();
	}

	@Inject(method = "setLootBasedRecipe", remap = false, at = @At(value = "HEAD"), cancellable = true)
	private void setLootBasedRecipe_Head(@NotNull IRecipeLayoutBuilder builder, @NotNull IGenericRecipe recipe, @NotNull IFocusGroup focuses, CallbackInfo ci)
	{
		this.minecolonies_compatibility$animalHerdingLoot = recipe instanceof AnimalHerdingLootGenericRecipe animalHerdingLoot ? animalHerdingLoot : null;
	}

	@Redirect(method = "setLootBasedRecipe", remap = false, at = @At(value = "INVOKE", target = "getLootDrops"))
	private List<LootTableAnalyzer.LootDrop> setLootBasedRecipe_getLootDrops(@NotNull ResourceLocation lootTableId)
	{
		var list = getLootDrops(lootTableId);

		if (this.minecolonies_compatibility$animalHerdingLoot != null)
		{
			var drops = new ArrayList<LootTableAnalyzer.LootDrop>();
			var event = new AnimalHerdingLootEvent(minecolonies_compatibility$animalHerdingLoot, drops::add);
			MinecraftForge.EVENT_BUS.post(event);

			if (drops.size() > 0)
			{
				var newList = new ArrayList<>(list);
				newList.addAll(drops);
				return newList;
			}

		}

		return list;
	}

	@Inject(method = "setNormalRecipe", remap = false, at = @At(value = "HEAD"), cancellable = true)
	private void setNormalRecipe_Head(@NotNull IRecipeLayoutBuilder builder, @NotNull IGenericRecipe recipe, @NotNull IFocusGroup focuses, CallbackInfo ci)
	{
		this.minecolonies_compatibility$role = null;
		this.minecolonies_compatibility$roleIndex = 0;

		this.minecolonies_compatibility$inputIndex = 0;
		this.minecolonies_compatibility$outputIndex = 0;
		this.minecolonies_compatibility$catalystIndex = 0;
		this.minecolonies_compatibility$recipe = recipe instanceof IRecipeSlotModifiableGenericRecipe genericRecipe ? genericRecipe : null;
		this.minecolonies_compatibility$animalHerdingLoot = null;

		if (recipe instanceof BucketFillingGenericRecipe fillingRecipe)
		{
			var slot = builder.addSlot(RecipeIngredientRole.INPUT, this.outputSlotX, CITIZEN_Y + 1);
			slot.addFluidStack(fillingRecipe.getFluid(), FluidType.BUCKET_VOLUME, fillingRecipe.getFluidTag());
			slot.setFluidRenderer(FluidType.BUCKET_VOLUME, false, 16, 16);
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

			this.minecolonies_compatibility$role = role;
			this.minecolonies_compatibility$roleIndex = index;

			if (role != null && index > -1)
			{
				final var tooltipRecipe = this.minecolonies_compatibility$recipe;
				final var tooltipRole = role;
				final var toolTipIndex = index;

				if (tooltipRecipe.isRecipeSlotOptional(tooltipRole, toolTipIndex))
				{
					slotBuilder.setBackground(this.chanceSlot, -1, -1);
				}

				slotBuilder.addTooltipCallback((recipeSlotView, tooltip) -> tooltip.addAll(1, tooltipRecipe.getRecipeSlotToolTip(tooltipRole, toolTipIndex)));
			}

		}
		else
		{
			this.minecolonies_compatibility$role = null;
		}

		return slotBuilder;
	}

	@Redirect(method = "setNormalRecipe", remap = false, at = @At(value = "INVOKE", target = "Lmezz/jei/api/gui/builder/IRecipeSlotBuilder;setBackground"))
	private IRecipeSlotBuilder setNormalRecipe_setBackground(IRecipeSlotBuilder builder, IDrawable background, int xOffset, int yOffset)
	{
		var recipe = this.minecolonies_compatibility$recipe;
		var role = this.minecolonies_compatibility$role;

		if (recipe != null && role != null)
		{
			var index = this.minecolonies_compatibility$roleIndex;

			if (recipe.isRecipeSlotOptional(role, index))
			{
				return builder.setBackground(this.chanceSlot, -1, -1);
			}

		}

		return builder.setBackground(background, xOffset, yOffset);
	}

}
