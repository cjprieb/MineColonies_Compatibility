package steve_gall.minecolonies_compatibility.core.common.entity.ai.guard;

import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.entity.ai.statemachine.tickratestatemachine.ITickRateStateMachine;
import com.minecolonies.api.entity.citizen.VisibleCitizenStatus;
import com.minecolonies.api.entity.pathfinding.PathResult;
import com.minecolonies.api.entity.pathfinding.PathingOptions;
import com.minecolonies.api.util.BlockPosUtil;
import com.minecolonies.api.util.constant.Constants;
import com.minecolonies.api.util.constant.GuardConstants;
import com.minecolonies.core.colony.buildings.AbstractBuildingGuards;
import com.minecolonies.core.colony.buildings.modules.settings.GuardTaskSetting;
import com.minecolonies.core.colony.jobs.AbstractJobGuard;
import com.minecolonies.core.entity.ai.citizen.guard.AbstractEntityAIGuard;
import com.minecolonies.core.entity.citizen.EntityCitizen;
import com.minecolonies.core.entity.pathfinding.MinecoloniesAdvancedPathNavigate;
import com.minecolonies.core.entity.pathfinding.pathjobs.AbstractPathJob;
import com.minecolonies.core.entity.pathfinding.pathjobs.PathJobCanSee;
import com.minecolonies.core.entity.pathfinding.pathjobs.PathJobMoveAwayFromLocation;
import com.minecolonies.core.entity.pathfinding.pathjobs.PathJobMoveToLocation;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import steve_gall.minecolonies_compatibility.api.common.entity.ai.CustomizedAIAttack;
import steve_gall.minecolonies_compatibility.api.common.entity.ai.ICustomizableEntityAI;
import steve_gall.minecolonies_compatibility.api.common.entity.ai.guard.CustomizableAISimpleGuard;

public class GunnerCombatAI<T extends AbstractEntityAIGuard<J, B> & ICustomizableEntityAI, J extends AbstractJobGuard<J>, B extends AbstractBuildingGuards> extends CustomizableAISimpleGuard<T, J, B>
{
	public static final int FLEE_CHANCE = 3;

	private final static VisibleCitizenStatus ARCHER_COMBAT = new VisibleCitizenStatus(new ResourceLocation(Constants.MOD_ID, "textures/icons/work/archer_combat.png"), "com.minecolonies.gui.visiblestatus.archer_combat");

	private final PathingOptions combatPathingOptions;

	public GunnerCombatAI(EntityCitizen owner, ITickRateStateMachine<?> stateMachine, T parentAI)
	{
		super(owner, stateMachine, parentAI);

		this.combatPathingOptions = new PathingOptions();
		this.combatPathingOptions.setEnterDoors(true);
		this.combatPathingOptions.setCanOpenDoors(true);
		this.combatPathingOptions.setCanSwim(true);
		this.combatPathingOptions.withOnPathCost(0.8D);
		this.combatPathingOptions.withJumpCost(0.01D);
		this.combatPathingOptions.withDropCost(1.5D);
	}

	@Override
	protected void doAttack(LivingEntity target)
	{
		super.doAttack(target);

		this.tryFlee(target);

		this.user.getCitizenData().setVisibleStatus(ARCHER_COMBAT);
	}

	public void tryFlee(LivingEntity target)
	{
		var user = this.user;

		if (user.distanceToSqr(target) < GuardConstants.RANGED_FLEE_SQDIST)
		{
			if (user.getRandom().nextInt(FLEE_CHANCE) == 0 && !((AbstractBuildingGuards) user.getCitizenData().getWorkBuilding()).getTask().equals(GuardTaskSetting.GUARD))
			{
				var parentAI = this.getParentAI();

				if (parentAI.getSelectedAI() instanceof CustomizedAIAttack attack)
				{
					var speed = attack.getJobPathSpeed(parentAI.getAIContext());
					user.getNavigation().moveAwayFromLivingEntity(target, getAttackDistance() / 2.0D, speed);
				}

			}

		}
		else
		{
			user.getNavigation().stop();
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	@Nullable
	public PathResult createPathResult(LivingEntity target, EntityCitizen user, double speed)
	{
		var job = this.createPathJob(target, user);
		var pathResult = ((MinecoloniesAdvancedPathNavigate) user.getNavigation()).setPathJob(job, null, speed, true);
		job.setPathingOptions(this.combatPathingOptions);
		return pathResult;
	}

	private AbstractPathJob createPathJob(LivingEntity target, EntityCitizen user)
	{
		var level = user.getLevel();
		var userPos = user.blockPosition();
		var targetPos = target.blockPosition();

		if (BlockPosUtil.getDistanceSquared(targetPos, userPos) <= 4.0D)
		{
			return new PathJobMoveAwayFromLocation(level, AbstractPathJob.prepareStart(target), targetPos, 7, (int) user.getAttribute(Attributes.FOLLOW_RANGE).getValue(), user);
		}
		else if (BlockPosUtil.getDistance2D(targetPos, userPos) >= 20.0D)
		{
			return new PathJobMoveToLocation(level, AbstractPathJob.prepareStart(user), targetPos, 200, user);
		}
		else
		{
			return new PathJobCanSee(user, target, level, ((AbstractBuildingGuards) user.getCitizenData().getWorkBuilding()).getGuardPos(), 40);
		}

	}

}
