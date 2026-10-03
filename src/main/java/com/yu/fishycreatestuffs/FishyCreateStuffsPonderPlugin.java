package com.yu.fishycreatestuffs;

import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;

import com.yu.fishycreatestuffs.ponder.ThroughShaftGearboxScenes;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

/**
 * Steps to add ponders for an existing component:
 * 1. Create a schematic .nbt file in resources/assets/&lt;modid&gt;/ponder
 * 2. Create a [Component]Scenes file in src/ponder with scene instructions
 * 3. Register the file and the scenes here
 * 4. Remove the placeholder tooltip in resources/lang/default/tooltips.json
 *
 * Ponder 1.0 (shipped with Create 6.0) replaced the old {@code PonderRegistrationHelper}
 * with a per-mod plugin, registered through {@code PonderIndex.addPlugin}.
 */
public class FishyCreateStuffsPonderPlugin implements PonderPlugin {

	@Override
	public String getModId() {
		return FishyCreateStuffs.ID;
	}

	@Override
	public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
		PonderSceneRegistrationHelper<ItemProviderEntry<?, ?>> sceneHelper = helper.withKeyFunction(RegistryEntry::getId);

		sceneHelper.forComponents(Blocks.THROUGH_SHAFT_GEARBOX)
				.addStoryBoard("through_shaft_gearbox", ThroughShaftGearboxScenes::throughShaftGearbox,
						AllCreatePonderTags.KINETIC_RELAYS);
	}

	@Override
	public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
		PonderTagRegistrationHelper<RegistryEntry<?, ?>> tagHelper = helper.withKeyFunction(RegistryEntry::getId);

		tagHelper.addToTag(AllCreatePonderTags.KINETIC_RELAYS)
				.add(Blocks.THROUGH_SHAFT_GEARBOX);
	}
}
