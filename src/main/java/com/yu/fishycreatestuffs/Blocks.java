package com.yu.fishycreatestuffs;

import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.item.render.CustomItemModels;
import com.yu.fishycreatestuffs.content.throughshaftgearbox.ThroughShaftGearboxBlock;
import com.simibubi.create.*;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

import static com.simibubi.create.foundation.data.AssetLookup.partialBaseModel;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

@SuppressWarnings("removal")
public class Blocks {
    public static final BlockEntry<ThroughShaftGearboxBlock> THROUGH_SHAFT_GEARBOX = FishyCreateStuffs.REGISTRATE.block("through_shaft_gearbox", ThroughShaftGearboxBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(BlockStressDefaults.setNoImpact())
            //.transform(FeatureToggle.register()) //fixme
            .transform(axeOrPickaxe())
            .lang("Through Shaft Gearbox")
            .blockstate((c, p) -> axisBlock(c, p, $ -> partialBaseModel(c, p), false))
            .item()
            .transform(customItemModel())
			//.simpleItem()
            .register();


////////////////////////////////////////////////////////////////
    /*
	public static final Map<BlockSetType, BlockEntry<LinkedButtonBlock>> LINKED_BUTTONS = new HashMap<>();
	
    static {
        BlockSetType.values().forEach(type -> {
            Block button = BuiltInRegistries.BLOCK.get(new ResourceLocation(type.name() + "_button"));
            if (button == null) return;
            if (!(button instanceof ButtonBlock buttonBlock))
                return;
            String namePath = type.name().contains(":") ? type.name().replace(':', '_') : type.name();
            LINKED_BUTTONS.put(type, REGISTRATE
                    .block("linked_" + namePath + "_button", properties -> new LinkedButtonBlock(properties, buttonBlock))
                    .initialProperties(() -> buttonBlock)
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .transform(LinkedTransmitterItem.register())
                    .onRegister(PreciseItemUseOverrides::addBlock)
                    //.blockstate(CCBlockStateGen.linkedButton(new ResourceLocation("block/" + namePath + "_button"),new ResourceLocation("block/" + namePath + "_button_pressed")))
                    .register());
        });
    }
	
    public static final BlockEntry<LinkedLeverBlock> LINKED_LEVER = REGISTRATE
            .block("linked_lever", properties -> new LinkedLeverBlock(properties, (LeverBlock) Blocks.LEVER))
            .initialProperties(() -> Blocks.LEVER)
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
            .transform(LinkedTransmitterItem.register())
            .onRegister(PreciseItemUseOverrides::addBlock)
            //.blockstate(CCBlockStateGen.linkedLever(new ResourceLocation("block/lever"),new ResourceLocation("block/lever_on")))
            .register();

    public static final BlockEntry<LinkedAnalogLeverBlock> LINKED_ANALOG_LEVER = REGISTRATE
            .block("linked_analog_lever", properties -> new LinkedAnalogLeverBlock(properties, AllBlocks.ANALOG_LEVER))
            .initialProperties(() -> Blocks.LEVER)
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
            .transform(LinkedTransmitterItem.register())
            .onRegister(PreciseItemUseOverrides::addBlock)
            //.blockstate(CCBlockStateGen.linkedLever(Create.asResource("block/analog_lever/block"),Create.asResource("block/analog_lever/block")))
            .register();
	*/

	static {

		ItemGroupEvents.modifyEntriesEvent(AllCreativeModeTabs.BASE_CREATIVE_TAB.key()).register(content -> {
			content.accept(THROUGH_SHAFT_GEARBOX);
		});
	}
    public static void register() {
    }
	/*
    private static Function<BlockState, ModelFile> forBoolean(DataGenContext<?, ?> ctx,
                                                              Function<BlockState, Boolean> condition,
                                                              String key,
                                                              RegistrateBlockstateProvider prov) {
        return state -> condition.apply(state) ? partialBaseModel(ctx, prov, key)
                : partialBaseModel(ctx, prov);
    }
	*/
}
