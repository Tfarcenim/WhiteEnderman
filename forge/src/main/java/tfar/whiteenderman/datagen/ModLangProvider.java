package tfar.whiteenderman.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
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
        // addTranslatableComponent(ModCreativeTabs.TITLE,"Nations3");
        //  addDefaultBlock(() -> ModBlocks.CLAIMING_TABLE);
        //  addDefaultBlock(() -> ModBlocks.DEPOSIT_STATION);
    }


    protected void addDefaultItem(Supplier<? extends Item> supplier) {
        addItem(supplier, getNameFromItem(supplier.get()));
    }

    protected void addDefaultBlock(Supplier<? extends Block> supplier) {
        addBlock(supplier, getNameFromBlock(supplier.get()));
    }

    protected void addDefaultEnchantment(Supplier<? extends Enchantment> supplier) {
        addEnchantment(supplier, getNameFromEnchantment(supplier.get()));
    }

    protected void addDefaultEntityType(Supplier<EntityType<?>> supplier) {
        addEntityType(supplier, getNameFromEntity(supplier.get()));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromBlock(Block block) {
        return StringUtils.capitaliseAllWords(block.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromEnchantment(Enchantment enchantment) {
        return StringUtils.capitaliseAllWords(enchantment.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromEntity(EntityType<?> entity) {
        return StringUtils.capitaliseAllWords(entity.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    protected void addTranslatableComponent(MutableComponent component, String text) {
        ComponentContents contents = component.getContents();
        if (contents instanceof TranslatableContents translatableContents) {
            add(translatableContents.getKey(), text);
        } else {
            throw new UnsupportedOperationException(component + " is not translatable");
        }
    }
}
