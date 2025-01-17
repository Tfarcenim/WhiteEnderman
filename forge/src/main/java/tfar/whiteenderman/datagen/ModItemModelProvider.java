package tfar.whiteenderman.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import tfar.whiteenderman.WhiteEnderman;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WhiteEnderman.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("white_enderman_spawn_egg", new ResourceLocation("item/template_spawn_egg"));
    }


}
