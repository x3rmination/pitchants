package com.github.x3rmination.common.enchantments.pants;

import com.github.x3rmination.init.EnchantmentInit;
import com.github.x3rmination.pitchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid=pitchants.MODID)
public class EnchantmentBooboo extends Enchantment {
    private boolean isReady = true;
    public EnchantmentBooboo() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
        this.setName("boo-boo");
        this.setRegistryName(new ResourceLocation(pitchants.MODID + ":boo-boo"));

        EnchantmentInit.ENCHANTMENTS.add(this);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 20 * enchantmentLevel;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 10;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {

        EntityPlayer player = event.player;

        int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BOOBOO, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
        if (level > 0 && isReady) {
            isReady = false;
            int waitAmount = 6000 - (level * 1000);
            new Thread(() -> {
                try {
                    Thread.sleep(waitAmount);
                    isReady = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            player.heal(2);

        }
    }
}