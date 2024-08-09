package steve_gall.minecolonies_compatibility.module.common.ewewukeks_musketmod;

import ewewukek.musketmod.GunItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

public class DummyGun extends GunItem
{
	private GunItem parent;
	private float damageMultiplier;

	public DummyGun()
	{
		super(new Item.Properties());
	}

	public GunItem getParent()
	{
		return this.parent;
	}

	public void setParent(GunItem parent)
	{
		this.parent = parent;
	}

	public float getDamageMultiplier()
	{
		return this.damageMultiplier;
	}

	public void setDamageMultiplier(float damageMultiplier)
	{
		this.damageMultiplier = damageMultiplier;
	}

	@Override
	public float bulletSpeed()
	{
		return this.parent.bulletSpeed();
	}

	@Override
	public float bulletStdDev()
	{
		return this.parent.bulletStdDev();
	}

	@Override
	public float damageMultiplierMax()
	{
		return this.damageMultiplier;
	}

	@Override
	public float damageMultiplierMin()
	{
		return this.damageMultiplier;
	}

	@Override
	public SoundEvent fireSound()
	{
		return this.parent.fireSound();
	}

	@Override
	public boolean ignoreInvulnerableTime()
	{
		return this.parent.ignoreInvulnerableTime();
	}

	@Override
	public boolean twoHanded()
	{
		return this.parent.twoHanded();
	}

}
