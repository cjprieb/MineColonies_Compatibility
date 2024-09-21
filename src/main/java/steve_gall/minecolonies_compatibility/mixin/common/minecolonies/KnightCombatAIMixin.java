package steve_gall.minecolonies_compatibility.mixin.common.minecolonies;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.minecolonies.api.compatibility.tinkers.TinkersToolHelper;
import com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickRateStateMachine;
import com.minecolonies.api.equipment.ModEquipmentTypes;
import com.minecolonies.api.equipment.registry.EquipmentTypeEntry;
import com.minecolonies.api.util.InventoryUtils;
import com.minecolonies.api.util.constant.GuardConstants;
import com.minecolonies.core.entity.ai.combat.AttackMoveAI;
import com.minecolonies.core.entity.ai.workers.guard.KnightCombatAI;
import com.minecolonies.core.entity.citizen.EntityCitizen;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import steve_gall.minecolonies_compatibility.core.common.init.ModToolTypes;

@Mixin(value = KnightCombatAI.class, remap = false)
public abstract class KnightCombatAIMixin extends AttackMoveAI<EntityCitizen>
{
	public KnightCombatAIMixin(EntityCitizen owner, ITickRateStateMachine<?> stateMachine)
	{
		super(owner, stateMachine);
	}

	@Redirect(method = "canAttack", remap = false, at = @At(value = "INVOKE", target = "com/minecolonies/api/util/InventoryUtils.getFirstSlotOfItemHandlerContainingEquipment"))
	private int canAttack_getFirstSlotOfItemHandlerContainingEquipment(IItemHandler itemHandler, EquipmentTypeEntry equipmentType, int minimalLevel, int maximumLevel)
	{
		if (equipmentType == ModEquipmentTypes.sword.get())
		{
			equipmentType = ModToolTypes.KNIGHT_WEAPON.getToolType();
		}

		return InventoryUtils.getFirstSlotOfItemHandlerContainingEquipment(itemHandler, equipmentType, minimalLevel, maximumLevel);
	}

	@Redirect(method = "getAttackDamage", remap = false, at = @At(value = "INVOKE", target = "com/minecolonies/api/compatibility/tinkers/TinkersToolHelper.getDamage"))
	private double getAttackDamage_getDamage(ItemStack stack)
	{
		if (!TinkersToolHelper.isTinkersSword(stack))
		{
			var amount = this.getAdditionsAmount(stack, Attributes.ATTACK_DAMAGE);
			return amount + GuardConstants.BASE_PHYSICAL_DAMAGE;
		}
		else
		{
			return TinkersToolHelper.getDamage(stack);
		}

	}

	@ModifyConstant(method = "getAttackDelay", remap = false, constant = @Constant(intValue = 32))
	private int modifyDelay(int KNIGHT_ATTACK_DELAY_BASE)
	{
		var stack = user.getItemInHand(InteractionHand.MAIN_HAND);

		if (!TinkersToolHelper.isTinkersSword(stack))
		{
			var base = Attributes.ATTACK_SPEED.getDefaultValue();
			var amount = this.getAdditionsAmount(stack, Attributes.ATTACK_SPEED);
			return (int) (KNIGHT_ATTACK_DELAY_BASE * ((base - 2.4D) / (base + amount)));

		}
		else
		{
			return KNIGHT_ATTACK_DELAY_BASE;
		}

	}

	private double getAdditionsAmount(ItemStack stack, Attribute attribute)
	{
		var amount = 0.0D;
		var modifiers = stack.getAttributeModifiers(EquipmentSlot.MAINHAND).get(attribute);

		for (var modifier : modifiers)
		{
			if (modifier.getOperation() == AttributeModifier.Operation.ADDITION)
			{
				amount += modifier.getAmount();
			}

		}

		return amount;
	}

}
