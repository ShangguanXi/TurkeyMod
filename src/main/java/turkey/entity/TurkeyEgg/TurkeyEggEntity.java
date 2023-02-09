package turkey.entity.TurkeyEgg;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import turkey.Main;
import turkey.entity.Turkey.TurkeyEntity;

import java.util.Objects;

public class TurkeyEggEntity extends ThrownItemEntity {
    public TurkeyEggEntity(EntityType<? extends TurkeyEggEntity> entityType, World world) {
        super(entityType, world);
    }

    public TurkeyEggEntity(World world, LivingEntity owner) {
        super(Main.turkey_egg_entity, owner, world);
    }
    public void handleStatus(byte status) {
        if (status == 3) {

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08, ((double)this.random.nextFloat() - 0.5) * 0.08);
            }
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 0.0F);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }

                for(int j = 0; j < i; ++j) {
                    TurkeyEntity turkeyEntity = Main.turkey.create(this.world);
                    Objects.requireNonNull(turkeyEntity).setBreedingAge(-24000);
                    turkeyEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                    this.world.spawnEntity(turkeyEntity);
                }
            }

            this.world.sendEntityStatus(this, (byte)3);
            this.discard();
        }

    }

    protected Item getDefaultItem() {
        return Main.turkey_egg;
    }
}
