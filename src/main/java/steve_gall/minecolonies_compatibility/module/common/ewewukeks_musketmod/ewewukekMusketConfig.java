package steve_gall.minecolonies_compatibility.module.common.ewewukeks_musketmod;

import net.minecraftforge.common.ForgeConfigSpec;
import steve_gall.minecolonies_compatibility.core.common.entity.ai.AttackDamageConfig;
import steve_gall.minecolonies_compatibility.core.common.entity.ai.AttackDelayConfig;
import steve_gall.minecolonies_compatibility.core.common.entity.ai.guard.GunnerConfig;
import steve_gall.minecolonies_compatibility.module.common.AbstractModuleConfig;

public class ewewukekMusketConfig extends AbstractModuleConfig
{
	public final JobConfig job;

	public ewewukekMusketConfig(ForgeConfigSpec.Builder builder)
	{
		super(builder);

		builder.push("job");
		this.job = new JobConfig(builder);
		builder.pop();
	}

	public class JobConfig
	{
		public final GunnerGunConfig gunnerPistol;
		public final GunnerGunConfig gunnerMusket;

		public JobConfig(ForgeConfigSpec.Builder builder)
		{
			builder.push("gunner_pistol");
			this.gunnerPistol = new GunnerGunConfig(builder);
			builder.pop();

			builder.push("gunner_musket");
			this.gunnerMusket = new GunnerGunConfig(builder);
			builder.pop();
		}

		public class GunnerGunConfig
		{
			public final int reloadDuration = 30 + 10;

			public final AttackDelayConfig attackDelay;
			public final AttackDamageConfig defaultBulletDamage;

			public GunnerGunConfig(ForgeConfigSpec.Builder builder)
			{
				builder.push("attackDelay");
				builder.comment("will reload before every shot and reload duration is '" + this.reloadDuration + "' ticks");
				this.attackDelay = new AttackDelayConfig(builder, GunnerConfig.getDefaultDelay().base(28));
				builder.pop();

				builder.push("defaultBulletDamage");
				this.defaultBulletDamage = new AttackDamageConfig(builder, GunnerConfig.getDefaultDamage());
				builder.pop();
			}

		}

	}

}
