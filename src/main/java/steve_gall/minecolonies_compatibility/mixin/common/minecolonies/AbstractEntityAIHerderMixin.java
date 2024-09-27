package steve_gall.minecolonies_compatibility.mixin.common.minecolonies;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.minecolonies.api.equipment.ModEquipmentTypes;
import com.minecolonies.api.equipment.registry.EquipmentTypeEntry;
import com.minecolonies.core.colony.buildings.AbstractBuilding;
import com.minecolonies.core.colony.jobs.AbstractJob;
import com.minecolonies.core.entity.ai.workers.AbstractEntityAIInteract;
import com.minecolonies.core.entity.ai.workers.production.herders.AbstractEntityAIHerder;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraftforge.common.util.FakePlayer;
import steve_gall.minecolonies_compatibility.core.common.init.ModToolTypes;

@Mixin(value = AbstractEntityAIHerder.class, remap = false)
public abstract class AbstractEntityAIHerderMixin<J extends AbstractJob<?, J>, B extends AbstractBuilding> extends AbstractEntityAIInteract<J, B>
{
	public AbstractEntityAIHerderMixin(@NotNull J job)
	{
		super(job);
	}

	@Inject(method = "getExtraToolsNeeded", remap = false, at = @At(value = "TAIL"), cancellable = true)
	private void getExtraToolsNeeded(CallbackInfoReturnable<List<EquipmentTypeEntry>> cir)
	{
		var changed = false;
		var newList = new ArrayList<EquipmentTypeEntry>();

		for (var toolType : cir.getReturnValue())
		{
			if (toolType == ModEquipmentTypes.axe.get())
			{
				newList.add(ModToolTypes.BUTCHER_TOOL.getToolType());
				changed = true;
			}
			else
			{
				newList.add(toolType);
			}

		}

		if (changed)
		{
			cir.setReturnValue(newList);
		}

	}

	@ModifyVariable(method = "getToolSlot", remap = false, at = @At(value = "HEAD"), ordinal = 0)
	private EquipmentTypeEntry getToolSlot(EquipmentTypeEntry toolType)
	{
		if (toolType == ModEquipmentTypes.axe.get())
		{
			return ModToolTypes.BUTCHER_TOOL.getToolType();
		}

		return toolType;
	}

	@Redirect(method = "butcherAnimal", remap = false, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Animal;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", remap = true))
	private boolean butcherAnimal_hurt(Animal animal, DamageSource source, float damage)
	{
		var damageType = this.worker.level().registryAccess().registry(Registries.DAMAGE_TYPE).get().get(DamageTypes.PLAYER_ATTACK);

		if (source.type() == damageType && source.getEntity() instanceof FakePlayer player)
		{
			var hand = InteractionHand.MAIN_HAND;
			var prev = player.getItemInHand(hand);
			player.setItemInHand(hand, this.worker.getItemInHand(hand).copy());
			var result = animal.hurt(source, damage);
			player.setItemInHand(hand, prev);
			return result;
		}
		else
		{
			return animal.hurt(source, damage);
		}

	}

}
