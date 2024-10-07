package com.yu.fishycreatestuffs.ponder;

import com.simibubi.create.foundation.ponder.PonderPalette;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import net.minecraft.core.Direction;

public class ThroughShaftGearboxScenes {
    public static void ThroughShaftGearbox(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("through_shaft_gearbox", "A new gearbox that might be useful in your creations");
        scene.configureBasePlate(1, 1, 5);
        scene.setSceneOffsetY(-1);
        scene.world.showSection(util.select.layer(0)
                        .add(util.select.position(3,1,6))
                        .add(util.select.position(3,2,6))
                        .add(util.select.position(3,3,6)),
                Direction.UP);
        scene.idle(10);
        scene.world.showSection(util.select.layer(1)
                        .substract(util.select.position(3,1,6))
                , Direction.DOWN);
        scene.overlay.showText(70)
                        .text("This is a through shaft gearbox")
                        .placeNearTarget()
                        .pointAt(util.vector.centerOf(3,1,4));
        scene.idle(80);
        scene.world.showSection(util.select.layersFrom(2)
                        .substract(util.select.position(3,3,6))
                , Direction.DOWN);
        scene.overlay.showText(70)
                .text("This is what it looks like on the inside")
                .placeNearTarget()
                .pointAt(util.vector.centerOf(3,3,4));
        scene.idle(80);
        scene.overlay.showText(70)
                .colored(PonderPalette.GREEN)
                .text("Use this shaft that goes all the way through wisely")
                .placeNearTarget()
                .pointAt(util.vector.of(3.8,3.2,3.8));
        scene.idle(80);
    }
}
