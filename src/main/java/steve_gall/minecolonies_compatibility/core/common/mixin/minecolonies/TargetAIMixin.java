package steve_gall.minecolonies_compatibility.core.common.mixin.minecolonies;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecolonies.core.entity.ai.combat.TargetAI;

import steve_gall.minecolonies_compatibility.api.common.entity.CustomizedCitizenAIAttack;
import steve_gall.minecolonies_compatibility.api.common.entity.ICustomizableAttackMoveAI;

@Mixin(value = TargetAI.class, remap = false)
public class TargetAIMixin
{
	@Inject(method = "getSearchRange", at = @At(value = "HEAD"), cancellable = true)
	private void getSearchRange(CallbackInfoReturnable<Integer> cir)
	{
		if (this instanceof ICustomizableAttackMoveAI<?, ?> self)
		{
			var parentAI = self.getParentAI();

			if (parentAI.getSelectedAI() instanceof CustomizedCitizenAIAttack attack)
			{
				cir.setReturnValue(attack.getHorizontalSearchRange(parentAI.getAIContext()));
			}
			else
			{
				cir.setReturnValue(0);
			}

		}

	}

	@Inject(method = "getYSearchRange", at = @At(value = "HEAD"), cancellable = true)
	private void getYSearchRange(CallbackInfoReturnable<Integer> cir)
	{
		if (this instanceof ICustomizableAttackMoveAI<?, ?> self)
		{
			var parentAI = self.getParentAI();

			if (parentAI.getSelectedAI() instanceof CustomizedCitizenAIAttack attack)
			{
				cir.setReturnValue(attack.getVerticalSearchRange(parentAI.getAIContext()));
			}
			else
			{
				cir.setReturnValue(0);
			}

		}

	}

}
