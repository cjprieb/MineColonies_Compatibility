package steve_gall.minecolonies_compatibility.api.common.entity.ai;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.core.entity.pathfinding.navigation.MinecoloniesAdvancedPathNavigate;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public abstract class CustomizedAIAttack extends CustomizedAI
{
	public void atBuildingActions(@NotNull CustomizedAIContext context)
	{

	}

	/**
	 *
	 * @return Whether citizen is ready to attack the target
	 */
	public boolean canAttack(@NotNull CustomizedAIContext context, @NotNull LivingEntity target)
	{
		return true;
	}

	public void doAttack(@NotNull CustomizedAIContext context, @NotNull LivingEntity target)
	{

	}

	public int getAttackDelay(@NotNull CustomizedAIContext context, @NotNull LivingEntity target)
	{
		return 40;
	}

	public double getAttackDistance(@NotNull CustomizedAIContext context, @NotNull LivingEntity target)
	{
		return 5.0D;
	}

	public double getHorizontalSearchRange(@NotNull CustomizedAIContext context)
	{
		return 16.0D;
	}

	public double getVerticalSearchRange(@NotNull CustomizedAIContext context)
	{
		return 3.0D;
	}

	public double getCombatMovementSpeed(@NotNull CustomizedAIContext context)
	{
		return 1.0D;
	}

	public double getJobPathSpeed(@NotNull CustomizedAIContext context)
	{
		var speed = this.getCombatMovementSpeed(context);
		var min = MinecoloniesAdvancedPathNavigate.MIN_SPEED_ALLOWED;
		var max = MinecoloniesAdvancedPathNavigate.MAX_SPEED_ALLOWED;
		return Mth.clamp(speed, min, max);
	}

}
