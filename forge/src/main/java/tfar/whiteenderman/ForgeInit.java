package tfar.whiteenderman;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ForgeInit {
    public static final ResourceKey<BiomeModifier> SPAWNS = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, WhiteEnderman.id("spawns"));

    public static TagKey<Biome> BIOME_SPAWNS = TagKey.create(Registries.BIOME, WhiteEnderman.id("spawns"));
}
