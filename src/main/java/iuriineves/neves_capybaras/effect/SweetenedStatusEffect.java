package iuriineves.neves_capybaras.effect;

import iuriineves.neves_capybaras.NevesCapybaras;
import iuriineves.neves_capybaras.event.ConsumeItemCallback;
import iuriineves.neves_capybaras.init.ModStatusEffects;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

import java.util.Objects;

public class SweetenedStatusEffect extends StatusEffect {

    public SweetenedStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!(entity instanceof PlayerEntity player)) return;
        if ((entity.getWorld().isClient())) return;


        ConsumeItemCallback.EVENT.register((itemStack, user) -> {
            if (user == player && player.hasStatusEffect(ModStatusEffects.SWEETENED)) {
                NevesCapybaras.LOGGER.info("Player ate food.");
                player.getHungerManager().add((itemStack.getFoodComponent().getHunger() / 4), 0.3f);
            }
            return ActionResult.SUCCESS;
        });
    }
}
