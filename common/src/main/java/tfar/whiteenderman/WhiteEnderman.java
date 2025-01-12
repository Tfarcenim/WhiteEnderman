package tfar.whiteenderman;

import net.minecraft.resources.ResourceLocation;
import tfar.whiteenderman.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Really rare.
//White skin, like the picture.
//Still passive and picks blocks normally.
//You CAN look at him. Won't be enraged by being looked at.
//If you damage him, he's enraged and will attack you like a normal enderman.
//Higher health and damage than normal.
//Does NOT take damage from water and will follow you into water.
//If there are other normal endermen around, they will come to his defense.
//Drops 1-2 Eye of Ender (guaranteed) and a low chance of an End Crystal drop.
public class WhiteEnderman {

    public static final String MOD_ID = "whiteenderman";
    public static final String MOD_NAME = "WhiteEnderman";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {


    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }
}