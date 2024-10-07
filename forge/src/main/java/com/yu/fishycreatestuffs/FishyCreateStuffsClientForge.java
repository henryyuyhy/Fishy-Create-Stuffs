package com.yu.fishycreatestuffs;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class FishyCreateStuffsClientForge {
    public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(FishyCreateStuffsClientForge::init);

    }

    public static void init(final FMLClientSetupEvent event) {
        Ponders.register();
    }
}
