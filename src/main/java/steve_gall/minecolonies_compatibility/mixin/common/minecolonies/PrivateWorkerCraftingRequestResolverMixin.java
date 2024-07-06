package steve_gall.minecolonies_compatibility.mixin.common.minecolonies;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecolonies.api.crafting.IRecipeStorage;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import com.minecolonies.core.colony.requestsystem.resolvers.PrivateWorkerCraftingRequestResolver;

import steve_gall.minecolonies_compatibility.core.common.crafting.BucketFillingRecipeStorage;
import steve_gall.minecolonies_tweaks.api.common.crafting.ICustomizableRecipeStorage;

@Mixin(value = PrivateWorkerCraftingRequestResolver.class, remap = false)
public abstract class PrivateWorkerCraftingRequestResolverMixin
{
	@Inject(method = "canBuildingCraftRecipe", remap = false, at = @At(value = "HEAD"), cancellable = true)
	private void canBuildingCraftRecipe(AbstractBuilding building, IRecipeStorage recipe, CallbackInfoReturnable<Boolean> cir)
	{
		if (recipe instanceof ICustomizableRecipeStorage crs && crs.getImpl() instanceof BucketFillingRecipeStorage)
		{
			cir.setReturnValue(false);
		}

	}

}
