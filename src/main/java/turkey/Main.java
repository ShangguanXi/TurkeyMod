package turkey;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import org.lwjgl.system.CallbackI;
import turkey.Item.TurkeyEggItem;
import turkey.Item.TurkeyNoodlesItem;
import turkey.entity.Turkey.TurkeyEntity;
import turkey.entity.TurkeyEgg.TurkeyEggEntity;

public class Main implements ModInitializer {
    public static final Item turkey_meat_raw = new Item(new FabricItemSettings()
            .food((new FoodComponent.Builder()
                    .meat()
                    .hunger(2)
                    .saturationModifier(0.8F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 1), 0.45F))
                    .build())
            .group(ItemGroup.FOOD));
    public static final Item turkey_meat_cooked = new Item(new FabricItemSettings()
            .food((new FoodComponent.Builder()
                    .meat()
                    .hunger(6)
                    .saturationModifier(6.4F))
                    .build())
            .group(ItemGroup.FOOD));
    public static final Item turkey_egg = new TurkeyEggItem(new FabricItemSettings().maxCount(16).group(ItemGroup.MATERIALS));
    public static final Item turkey_noodles = new TurkeyNoodlesItem(new FabricItemSettings()
            .food((new FoodComponent.Builder()
                    .hunger(12)
                    .saturationModifier(8.4F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 400, 0), 1.0F))
                    .build())
            .maxCount(1)
            .group(ItemGroup.FOOD));

    public static final EntityType<TurkeyEntity> turkey = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("turkey", "turkey"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TurkeyEntity::new)
                    .dimensions(EntityDimensions.fixed(0.3F, 0.3F))
                    .build()
    );
    public static final EntityType<TurkeyEggEntity> turkey_egg_entity = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("turkey", "turkey_egg_entity"),
            FabricEntityTypeBuilder.<TurkeyEggEntity>create(SpawnGroup.MISC, TurkeyEggEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .build()
    );

    public static final Item turkey_spawn_egg = new SpawnEggItem(turkey, 0xA0522D, 0x8B4513, new FabricItemSettings().group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(turkey, TurkeyEntity.createTurkeyAttributes());
        Registry.register(Registry.ITEM, new Identifier("turkey", "turkey_spawn_egg"), turkey_spawn_egg);
        Registry.register(Registry.ITEM, new Identifier("turkey", "turkey_meat_raw"), turkey_meat_raw);
        Registry.register(Registry.ITEM, new Identifier("turkey", "turkey_meat_cooked"), turkey_meat_cooked);
        Registry.register(Registry.ITEM, new Identifier("turkey", "turkey_egg"), turkey_egg);
        Registry.register(Registry.ITEM, new Identifier("turkey", "turkey_noodles"), turkey_noodles);

        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.PLAINS), SpawnGroup.CREATURE, turkey, 400, 1, 2);
    }
    public static Identifier id(String path) {
        return new Identifier("turkey", path);
    }

}
