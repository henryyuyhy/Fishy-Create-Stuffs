# Fishy Create Stuffs — NeoForge 1.21.1

1.20.1 多加载器工程（`common/` + `fabric/`）迁移到 **Minecraft 1.21.1 / NeoForge 21.1.x** 的单加载器版本。
内容照搬 Fabric 版行为：**Through Shaft Gearbox（穿轴齿轮箱）** 和 **Train Stair（列车楼梯）**，含齿轮箱的 Ponder 教学。

原 1.20.1 工程仍在同级目录 `../Fishy-Create-Stuffs-1.20.1`，未做任何改动。

## 版本矩阵（已实机验证）

| 项 | 版本 |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.249 |
| Create | 6.0.10-280（`create-1.21.1`） |
| Ponder | 1.0.82+mc1.21.1 |
| Flywheel | 1.0.6（api `compileOnly` + 本体 `runtimeOnly`） |
| Registrate | MC1.21-1.3.0+67 |
| moddev-gradle | 2.0.146 |
| Gradle wrapper | 9.2.1 |
| JDK | 21 |
| Parchment | 2024.11.17（1.21.1） |

依赖坐标与仓库照官方 [Depending on Create → NeoForge 1.21.1](https://wiki.createmod.net/developers/depend-on-create/neoforge-1.21.1) 页面钉选。

## 构建与运行

```bat
gradlew.bat build          :: 产物 build\libs\fishycreatestuffs-1.0.2.1.jar
gradlew.bat runClient      :: 开发客户端
gradlew.bat runServer      :: 开发服务端（run-server\eula.txt 已置 eula=true）
gradlew.bat runData        :: 数据生成（本工程未声明 datagen provider）
```

> **本机 JAVA_HOME 说明（重要）**
> 这台机器没有设置 `JAVA_HOME`，`PATH` 里也没有 `java`，Gradle 根本无法启动。为此加了两处 workaround：
> 1. `gradlew.bat` 顶部：`JAVA_HOME` 为空时回退到 `C:\Users\henry\Desktop\PCL2\jdk-21.0.6`；
> 2. `gradle.properties`：`org.gradle.java.installations.paths` 登记同一个 JDK，避免 foojay 联网下载 toolchain。
>
> 建议设置系统环境变量 `JAVA_HOME`，然后删掉这两处；否则换机器或换 JDK 路径就要重新改。

## 源码结构

```
src/main/java/com/yu/fishycreatestuffs/
  FishyCreateStuffs.java               @Mod 主类：CreateRegistrate、创造标签页归属
  FishyCreateStuffsClient.java         @Mod(dist=CLIENT)：FMLClientSetupEvent 注册 Ponder 插件
  FishyCreateStuffsPonderPlugin.java   PonderPlugin 实现（场景 + 标签）
  Blocks.java                          方块注册（Registrate）
  BlockEntityTypes.java                方块实体注册（visual + renderer）
  Items.java                           占位（原工程也没有独立物品）
  content/throughshaftgearbox/
    ThroughShaftGearboxBlock.java      方块（AXIS + FACING，扳手旋转，结构变换）
    ThroughShaftGearboxEntity.java     SplitShaftBlockEntity，按 FACING 反转单面转速
    ThroughShaftGearboxVisual.java     Flywheel 1.0 实例化渲染
    ThroughShaftGearboxRenderer.java   非实例化的后备渲染
  content/trainstair/TrainStairBlock.java
  ponder/ThroughShaftGearboxScenes.java
src/main/resources/
  META-INF/neoforge.mods.toml          modLoader/loaderVersion 已显式声明（1.21.1 必须）
  assets/fishycreatestuffs/            模型、贴图、方块状态、语言、ponder schematic（原样搬运）
  data/                                loot_table / recipe / tags/block（1.21 单数目录名）
```

## 迁移中改动的 API（1.20.1 → 1.21.1 / Create 6.0）

| 1.20.1 | 1.21.1 |
|---|---|
| `ModInitializer` / `ClientModInitializer` | `@Mod` 构造器 + `@Mod(dist = Dist.CLIENT)` + `FMLClientSetupEvent` |
| `ItemGroupEvents.modifyEntriesEvent` | `CreateRegistrate.defaultCreativeTab(...)` |
| `BlockStressDefaults.setNoImpact()` | 删除（见下） |
| `ITransformableBlock` | `com.simibubi.create.api.contraption.transformable.TransformableBlock` |
| `VoxelShaper` / `Iterate`（Create 包内） | `net.createmod.catnip.math.VoxelShaper` / `net.createmod.catnip.data.Iterate` |
| `KineticBlockEntityInstance` + `RotatingData` | `KineticBlockEntityVisual` + `RotatingInstance`（Flywheel 1.0 把 Instance 改名 Visual） |
| `com.jozufozu.flywheel.*` | `dev.engine_room.flywheel.*` |
| `CachedBufferer` / `Backend.canUseInstancing` | `CachedBuffers` / `VisualizationManager.supportsVisualization` |
| `PonderRegistrationHelper` + `PonderRegistry.TAGS` | `PonderPlugin` + `PonderIndex.addPlugin` + `AllCreatePonderTags` |
| `scene.world.showSection(...)`（字段） | `scene.world().showSection(...)`（访问器） |
| `BlockPickInteractionAware`（Fabric 专属） | 删除（`getCloneItemStack` 默认实现即返回方块物品） |
| `data/…/loot_tables`、`recipes`、`tags/blocks` | `loot_table`、`recipe`、`tags/block`（1.21 目录单数化） |
| 配方 `"result": {"item": …}` | `"result": {"id": …}`（1.21 的 ItemStack codec 要求，否则报 `No key id`） |
| `HorizontalDirectionalBlock` 子类 | 需补 `codec()`（1.21 方块 codec 体系） |

## 与 1.20.1 版的有意差异

1. **移除应力值设置**。Create 6.0 的 `CStress` 对非 Create 方块直接抛
   `IllegalStateException: Non-Create blocks cannot be added to Create's config.`；
   而 `BlockStressValues.getImpact` 对未注册方块本来就返回 0，所以删掉调用后行为等价（齿轮箱依旧零应力影响）。
2. **扳手旋转不再“破坏 + 重新放置”**。原版靠 `world.removeBlock` + `setBlockAndUpdate` 逼动力学重新传播；
   6.0 改为重写 `areStatesKineticallyEquivalent()` 把 `FACING` 一并纳入比较，交给
   `KineticBlockEntity.switchToBlockState` 正常重算，更安全也更贴合 Create 的扩展点。
3. **后备渲染与实例化路径对齐**。1.20.1 的非实例化渲染画了 6 面轴，而实例化路径只画 4 面，
   且 `hasShaftTowards` 对轴向面返回 false —— 现统一为只画 4 面。
4. **补全方块状态**。原工程手写的 blockstate 只覆盖 12/18 个 `AXIS × FACING` 组合，缺的 6 个
   （`FACING` 与 `AXIS` 平行）在连续扳手旋转后会出现“缺失模型”紫黑方块并刷警告。
   已按同组既有旋转规则补齐：`axis=x,east/west`、`axis=y,up/down`、`axis=z,north/south`。

## 验证情况

已实机跑通（`runClient` / `runServer`，日志见 `run/logs/latest.log` 与 `run-server/logs/latest.log`）：

- 模组正常加载，Mod 列表出现 `Fishy Create Stuffs 1.0.2.1`；
- 创造模式背包可正常打开，两个方块出现在 **Create 的 Base 标签页**，并同时进入搜索标签页；
- 3 个配方全部解析成功（服务端 `Loaded 2819 recipes`，迁移前为 2816）；
- 方块状态零 `missing model` 警告；
- Ponder 场景与标签注册成功，schematic 无缺失报错，游戏内打开 Ponder 界面播放正常；
- 调试期间修掉的两个真实崩溃（`CStress` 拒绝附属方块、创造标签页重复条目）均已复测通过；
- 全程无崩溃报告，仅剩一条 Ponder 自带的 MixinExtras 注解类缺失 WARN（开发环境无害）。

## 备注

- `run/`、`run-server/` 是开发运行产物（含测试世界 `New World`），已在 `.gitignore` 中排除；
  早期调试遗留的两份 crash report 已清理。
- Ponder 用的是 1.20.1 时期的 `.nbt` schematic，能正常加载；若日后发现教学里个别方块缺失，
  用 Create 的 ponder 编辑模式重新导出一份即可。
- `Blocks.addLayer(...)` 在 1.21 标记为 deprecated(forRemoval) 但仍生效，它同时决定 BlockItem 的渲染层，
  1.20.1 的行为即由此保证，故保留（类上加 `@SuppressWarnings("removal")`）。
  将来若改用模型 JSON 的 `render_type`，注意方块模型和物品渲染层要一起迁。
- 许可证沿用原工程的 AGPL-3.0（见 `LICENSE`）。
