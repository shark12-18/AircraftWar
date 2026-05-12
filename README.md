# AircraftWar 飞机大战实验项目

## 项目简介

AircraftWar 是一个基于 Java Swing 的 2D 飞机射击游戏实验项目。玩家通过鼠标控制英雄机移动，自动发射子弹，击毁敌机获得分数，并通过拾取不同道具改变战斗状态。

本项目围绕软件构造课程实验逐步完善，当前已覆盖实验一到实验六的主要内容：继承建模、单例模式、简单工厂模式、工厂方法模式、策略模式、DAO 模式、JUnit 单元测试、Swing 界面、多线程音频与道具效果、观察者模式和模板方法模式。

## 当前功能

- 英雄机由鼠标控制移动，自动射击。
- 敌机包含普通敌机、精英敌机、精锐敌机、王牌敌机和 Boss 敌机。
- 道具包含加血、火力、超级火力、炸弹和冰冻五种。
- 支持简单、普通、困难三种难度，难度不同会影响敌机数量、生成周期、敌机概率、射击周期、Boss 生成和难度递进。
- 支持背景音乐、Boss 音乐、子弹命中、道具获取、炸弹爆炸和游戏结束音效。
- 游戏结束后弹出排行榜窗口，按难度保存和展示分数记录。
- 提供 JUnit 测试和 Maven 配置，便于在 VS Code 中运行测试。
- 提供 PlantUML 源文件和 PNG 类图，用于实验报告和验收。

## 运行环境

- 操作系统：macOS、Windows 或 Linux
- Java：JDK 11 及以上，项目当前也可在 OpenJDK 25 环境下编译运行
- 开发工具：Visual Studio Code 或 IntelliJ IDEA
- 构建和测试：Maven
- UML：PlantUML

## 运行方式

### VS Code 运行

建议使用 VS Code 打开项目根目录：

```bash
Aircraftwar
```

安装 Java Extension Pack 后，在 Java Projects 或 Run Java 中运行主类：

```text
edu.hitsz.application.Main
```

注意不要把 `src/edu/hitsz/application/Main.java` 当作无包名的单文件脚本运行。本项目的主类声明了包名 `edu.hitsz.application`，并且图片、音频资源使用相对路径 `src/images` 和 `src/videos`，因此运行工作目录应保持为项目根目录。

### 命令行运行

在项目根目录执行：

```bash
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out edu.hitsz.application.Main
```

Windows PowerShell 可使用等价的 Java 编译命令，运行时类路径仍需指向编译输出目录。

### Maven 测试

项目使用 Maven 管理 JUnit 5 测试依赖。运行测试：

```bash
mvn test
```

测试类位于：

```text
test/edu/hitsz/aircraft/HeroAircraftTest.java
```

测试内容覆盖英雄机单例、扣血死亡、射击策略和继承位置方法等基础行为。

## 项目结构

```text
Aircraftwar/
├── pom.xml                         # Maven 配置，管理 JUnit 测试
├── README.md                       # 项目说明
├── data/                           # 排行榜数据，运行时按难度生成 CSV
├── src/
│   ├── images/                     # 图片资源
│   ├── videos/                     # 音频资源
│   └── edu/hitsz/
│       ├── aircraft/               # 英雄机、敌机及飞机抽象类
│       ├── application/            # 主程序、游戏面板、难度、界面、音频线程
│       ├── basic/                  # 飞行对象抽象父类
│       ├── bullet/                 # 子弹类
│       ├── factory/                # 简单工厂和工厂方法相关类
│       ├── observer/               # 道具观察者接口
│       ├── prop/                   # 道具类
│       ├── score/                  # 排行榜 DAO 和分数记录
│       └── strategy/               # 射击策略
├── test/                           # JUnit 测试
└── uml/                            # PlantUML 源文件和生成图片
```

## 主要模块

### application

- `Main`：程序入口，显示难度选择界面并创建对应游戏对象。
- `StartMenuPanel`：Swing 难度选择界面。
- `Game`：游戏主面板和模板方法抽象类，`action()` 定义固定主循环。
- `EasyGame`、`NormalGame`、`HardGame`：三种难度的具体游戏类。
- `ScoreBoardFrame`：游戏结束后的排行榜窗口，支持保存、展示和删除记录。
- `AudioManager`、`MusicThread`：音频播放管理和播放线程。
- `FirePowerTask`：火力道具持续时间结束后的恢复任务。
- `HeroController`：鼠标控制英雄机移动。
- `ImageManager`：集中加载和提供图片资源。

### aircraft

- `AbstractAircraft`：飞机抽象父类，维护血量和射击策略。
- `AbstractEnemy`：敌机抽象父类，维护击毁得分并实现道具观察者接口。
- `HeroAircraft`：英雄机，采用单例模式。
- `MobEnemy`：普通敌机，不射击，冰冻后永久静止。
- `EliteEnemy`：精英敌机，直射。
- `ElitePlusEnemy`：精锐敌机，双发直射。
- `EliteProEnemy`：王牌敌机，散射，炸弹只造成伤害，冰冻会减速。
- `BossEnemy`：Boss 敌机，环射，只左右移动，不受炸弹和冰冻影响。

### prop

- `AbstractProp`：道具抽象父类，维护观察者列表并提供通知方法。
- `BloodProp`：恢复英雄机生命值。
- `FireProp`：临时切换英雄机为散射策略。
- `FirePlusProp`：临时切换英雄机为环射策略。
- `BombProp`：通知观察者执行炸弹效果。
- `FreezeProp`：通知观察者执行冰冻效果。

### score

- `ScoreRecord`：排行榜记录对象。
- `ScoreDao`：排行榜数据访问接口。
- `ScoreDaoImpl`：基于 CSV 文件的 DAO 实现。

排行榜按难度分别保存：

```text
data/easy.csv
data/normal.csv
data/hard.csv
```

这些文件是运行时数据，不是核心源码。

## 设计模式对应关系

### 单例模式

应用位置：`HeroAircraft`

英雄机是玩家唯一操控对象，使用单例模式保证游戏过程中只有一个英雄机实例。`getInstance()` 负责获取唯一实例，`resetInstance()` 用于重新开始或切换难度前重置英雄机状态。

对应 UML：

```text
uml/Singleton.puml
uml/Singleton.png
```

### 简单工厂模式

应用位置：`PropFactory`

道具创建由 `PropFactory.createProp()` 统一完成，`PropType` 枚举表示血量、火力、超级火力、炸弹和冰冻五种道具。Game 只决定可掉落道具范围，不直接依赖每个具体道具构造方法。

对应 UML：

```text
uml/SimpleFactory.puml
uml/SimpleFactory.png
```

### 工厂方法模式

应用位置：`EnemyFactory` 及具体敌机工厂

不同敌机由不同工厂创建，包括 `MobEnemyFactory`、`EliteEnemyFactory`、`ElitePlusEnemyFactory`、`EliteProEnemyFactory` 和 `BossEnemyFactory`。Game 通过 `EnemyFactory` 抽象接口创建敌机，降低主流程和具体敌机类型的耦合。

对应 UML：

```text
uml/FactoryMethod.puml
uml/FactoryMethod.png
```

### 策略模式

应用位置：`ShootStrategy` 及具体射击策略

射击方式被封装为策略对象。英雄机和不同敌机通过组合不同 `ShootStrategy` 实现直射、双发、散射、环射或不射击。火力道具通过动态替换英雄机策略实现弹道变化。

对应 UML：

```text
uml/Strategy.puml
uml/Strategy.png
```

### DAO 模式

应用位置：`score` 包

排行榜数据访问由 `ScoreDao` 抽象，`ScoreDaoImpl` 负责读写 CSV 文件，`ScoreBoardFrame` 只依赖 DAO 接口完成增删查和展示。这样界面逻辑和文件持久化逻辑保持分离。

对应 UML：

```text
uml/DAO.puml
```

### 观察者模式

应用位置：炸弹和冰冻道具

`AbstractProp` 作为观察目标维护 `PropObserver` 列表。英雄机拾取炸弹或冰冻道具时，Game 将当前敌机和敌机子弹注册为观察者，随后 `BombProp` 或 `FreezeProp` 通知所有观察者执行对应效果。

不同对象响应不同：

- 普通敌机：炸弹坠毁，冰冻后永久静止。
- 精英敌机：炸弹坠毁，冰冻 4 秒后恢复。
- 精锐敌机：炸弹坠毁，冰冻 3 秒后恢复。
- 王牌敌机：炸弹造成伤害，冰冻后减速 5 秒。
- Boss 敌机：不受炸弹和冰冻影响。
- 敌机子弹：炸弹后消失，冰冻后静止 5 秒。

对应 UML：

```text
uml/Observer.puml
uml/Observer.png
```

### 模板方法模式

应用位置：`Game`、`EasyGame`、`NormalGame`、`HardGame`

`Game.action()` 是模板方法，声明为 `final`，固定游戏主循环步骤：敌机生成、Boss 生成、难度递进、射击、移动、碰撞检测、后处理、重绘和结束检查。

可变步骤由子类通过钩子方法定制：

- `EasyGame`：节奏较慢，不生成 Boss，难度不随时间递进。
- `NormalGame`：生成 Boss，Boss 血量固定，敌机生成周期、速度和血量随时间提升。
- `HardGame`：节奏更快，敌机更强，英雄机射击周期变慢，敌机射击周期变快，Boss 每次生成都会增加血量。

对应 UML：

```text
uml/Template.puml
uml/Template.png
```

## 游戏机制

### 难度选择

启动游戏后先进入 `StartMenuPanel`，玩家选择简单、普通或困难难度。`Main` 根据选择创建对应的 `EasyGame`、`NormalGame` 或 `HardGame`。

三种难度会影响：

- 屏幕中敌机最大数量。
- 敌机生成周期。
- 不同敌机出现概率。
- 英雄机和敌机射击周期。
- Boss 生成阈值和 Boss 血量变化。
- 是否随游戏时间提高难度。

### 敌机生成

普通敌机、精英敌机、精锐敌机和王牌敌机由概率控制生成。Boss 由分数阈值触发生成：

- 简单难度不生成 Boss。
- 普通难度生成固定血量 Boss。
- 困难难度每次生成 Boss 都会额外提高血量。

### 道具掉落

- 普通敌机不掉落道具。
- 精英敌机按概率掉落基础道具。
- 精锐敌机按概率掉落四种道具，不含冰冻。
- 王牌敌机按概率掉落全部五种道具。
- Boss 被击毁后必定掉落三个道具，且从全部五种道具中随机选择。
- 炸弹道具造成的敌机坠毁会计分，但不会触发敌机掉落道具。

### 排行榜

游戏结束后会打开排行榜窗口，弹出玩家姓名输入框，保存当前难度、本局分数和记录时间。排行榜按分数降序排列，并支持删除选中记录。

如果取消姓名输入，则本局记录不会保存；如果输入为空字符串，则保存为默认玩家名 `Player`。

## 实验内容完成情况

- 实验一：完成系统分析、继承关系建模和英雄机单例模式。
- 实验二：完成道具简单工厂和敌机工厂方法模式。
- 实验三：完成射击策略模式和多种敌机/道具行为。
- 实验四：完成 DAO 排行榜、CSV 持久化和 JUnit 单元测试。
- 实验五：完成 Swing 难度选择、排行榜界面、音频线程和火力恢复线程。
- 实验六：完成炸弹/冰冻观察者模式和三种难度模板方法模式。

## UML 文件

```text
uml/Inheritence.puml
uml/Singleton.puml
uml/SimpleFactory.puml
uml/FactoryMethod.puml
uml/Strategy.puml
uml/DAO.puml
uml/Observer.puml
uml/Template.puml
```

除 `DAO.puml` 外，当前目录中也保留了对应 PNG 图片。`DAO.puml` 可使用 PlantUML 生成图片。

## 注意事项

- 运行游戏时请保证工作目录是项目根目录，否则 `src/images` 和 `src/videos` 下的资源可能加载失败。
- `out/`、`target/`、`.DS_Store` 和 `data/*.csv` 都属于本地生成内容或运行数据，一般不需要作为实验源码提交。
- 项目保留了 IDEA 工程文件，也提供了 Maven 配置；在 VS Code 中建议优先使用 Maven/JUnit 工作流。

## 学习用途

本项目仅用于软件构造课程实验与设计模式学习。
