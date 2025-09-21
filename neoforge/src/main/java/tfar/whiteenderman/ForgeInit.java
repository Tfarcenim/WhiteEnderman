package tfar.whiteenderman;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ForgeInit {
    public static final ResourceKey<BiomeModifier> SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, WhiteEnderman.id("spawns"));

    public static TagKey<Biome> BIOME_SPAWNS = TagKey.create(Registries.BIOME, WhiteEnderman.id("spawns"));
}
