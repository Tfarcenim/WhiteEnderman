package tfar.whiteenderman;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.phys.AABB;

import java.util.Iterator;
import java.util.List;

public class CustomHurtByTargetGoal extends HurtByTargetGoal {
    private final Class<? extends Mob>[] toAlert;

    public CustomHurtByTargetGoal(PathfinderMob $$0, Class<?>[] $$1, Class<? extends Mob>[] toAlert) {
        super($$0, $$1);
        this.toAlert = toAlert;
        alertSameType = true;
    }

    @Override
    protected void alertOthers() {
        super.alertOthers();
        double d0 = this.getFollowDistance();
        AABB aabb = AABB.unitCubeFromLowerCorner(this.mob.position()).inflate(d0, 10.0D, d0);
        for (Class<? extends Mob> clazz : toAlert) {
            List<? extends Mob> list = this.mob.level().getEntitiesOfClass(clazz, aabb, EntitySelector.NO_SPECTATORS);
            Iterator<? extends Mob> iterator = list.iterator();

            while (true) {
                Mob mob;
                while (true) {
                    if (!iterator.hasNext()) {
                        return;
                    }

                    mob = iterator.next();
                    if (this.mob != mob && mob.getTarget() == null && (!(this.mob instanceof TamableAnimal) || ((TamableAnimal) this.mob).getOwner() == ((TamableAnimal) mob).getOwner()) && !mob.isAlliedTo(this.mob.getLastHurtByMob())) {
                        if (this.toIgnoreAlert == null) {
                            break;
                        }

                        boolean flag = false;

                        for (Class<?> oclass : this.toIgnoreAlert) {
                            if (mob.getClass() == oclass) {
                                flag = true;
                                break;
                            }
                        }

                        if (!flag) {
                            break;
                        }
                    }
                }
                this.alertOther(mob, this.mob.getLastHurtByMob());
            }
        }
    }
}
