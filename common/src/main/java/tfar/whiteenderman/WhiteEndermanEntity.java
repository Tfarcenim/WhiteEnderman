package tfar.whiteenderman;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class WhiteEndermanEntity extends EnderMan {
    public WhiteEndermanEntity(EntityType<? extends EnderMan> $$0, Level $$1) {
        super($$0, $$1);
        this.setPathfindingMalus(BlockPathTypes.WATER, 8.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new EndermanFreezeWhenLookedAt(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new EndermanLeaveBlockGoal(this));
        this.goalSelector.addGoal(11, new EndermanTakeBlockGoal(this));
        this.targetSelector.addGoal(1, new EndermanLookForPlayerGoal(this, this::isAngryAt));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(EnderMan.class));

    }

    @Override
    public boolean isSensitiveToWater() {
        return false;
    }

    public static AttributeSupplier.Builder createWhiteAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 60).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.ATTACK_DAMAGE, 10);
    }
}
