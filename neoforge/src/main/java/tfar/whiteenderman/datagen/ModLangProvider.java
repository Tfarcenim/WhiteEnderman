package tfar.whiteenderman.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;
import tfar.whiteenderman.WhiteEnderman;

import java.util.function.Supplier;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, WhiteEnderman.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addDefaultItem(WhiteEnderman.WHITE_ENDERMAN_SPAWN_EGG::get);
        addDefaultEntityType(WhiteEnderman.WHITE_ENDERMAN::get);
    }


    protected void addDefaultItem(Supplier<? extends Item> supplier) {
        addItem(supplier, getNameFromItem(supplier.get()));
    }

    protected void addDefaultEntityType(Supplier<EntityType<?>> supplier) {
        addEntityType(supplier, getNameFromEntity(supplier.get()));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromEntity(EntityType<?> entity) {
        return StringUtils.capitaliseAllWords(entity.getDescriptionId().split("\\.")[2].replace("_", " "));
    }
}
