package com.yu.fishycreatestuffs;

import com.yu.fishycreatestuffs.ponder.ThroughShaftGearboxScenes;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;

/**
 * Steps to add ponders for an existing component:
 * 1. Create a schematic .nbt file in resources/ponder
 * 2. Create a [Component]Scenes file in src/ponder with scene instructions
 * 3. Register the file and the scenes here
 * 4. Remove the placeholder tooltip in resources/lang/default/tooltips.json
 */
public class Ponders {

    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(FishyCreateStuffs.ID);

    public static void register() {
        HELPER.forComponents(Blocks.THROUGH_SHAFT_GEARBOX)
                        .addStoryBoard("through_shaft_gearbox", ThroughShaftGearboxScenes::ThroughShaftGearbox, AllPonderTags.KINETIC_RELAYS);
        PonderRegistry.TAGS.forTag(AllPonderTags.KINETIC_RELAYS)

                .add(Blocks.THROUGH_SHAFT_GEARBOX)
                ;
    }
}
