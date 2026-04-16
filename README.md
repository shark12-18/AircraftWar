# AircraftWar - 飞机大战游戏

## 项目简介

AircraftWar 是一个基于Java Swing开发的2D飞机射击游戏，玩家控制一架英雄飞机与各种类型的敌机进行战斗。游戏具有丰富的敌机类型、道具系统和战斗机制。

## 功能特性

### 🎮 游戏玩法
- **英雄飞机控制**：使用鼠标控制英雄飞机移动和射击
- **多种敌机类型**：普通敌机、精英敌机、精锐敌机、王牌敌机、Boss敌机
- **道具系统**：血量道具、炸弹道具、火力增强道具、超级火力道具、冰冻道具
- **分数系统**：击落敌机获得分数，Boss敌机按分数阈值触发
- **音效系统**：背景音乐、爆炸音效、子弹命中等音效

### ✈️ 飞机类型
- **英雄飞机 (HeroAircraft)**：玩家控制的飞机，使用单例模式确保唯一实例
- **普通敌机 (MobEnemy)**：基础敌机，移动速度较慢
- **精英敌机 (EliteEnemy)**：中等难度，有概率掉落道具
- **精锐敌机 (ElitePlusEnemy)**：高级敌机，更强的战斗力
- **王牌敌机 (EliteProEnemy)**：精英中的精英，高概率掉落道具
- **Boss敌机 (BossEnemy)**：最终Boss，拥有强大的生命值和攻击力，必定掉落多个道具

### 🎯 道具系统
- **血量道具 (BloodProp)**：恢复英雄飞机生命值
- **炸弹道具 (BombProp)**：清除屏幕上的所有敌机
- **火力增强道具 (FireProp)**：切换英雄机弹道为散射策略
- **超级火力道具 (FirePlusProp)**：切换英雄机弹道为环射策略
- **冰冻道具 (FreezeProp)**：暂时冻结所有敌机

### 🎵 音效系统
- 背景音乐 (BGM)
- Boss战音乐
- 爆炸音效
- 子弹命中音效
- 游戏结束音效
- 道具获取音效

## 运行环境

- **Java版本**：JDK 8 或更高版本
- **操作系统**：Windows / macOS / Linux
- **开发环境**：IntelliJ IDEA（推荐）或任何支持Java的IDE

## 快速开始

### 1. 使用IDE运行（推荐）
- 使用IntelliJ IDEA打开项目根目录（包含`AircraftWar-base.iml`文件）
- 等待项目索引完成
- 运行 `src/edu/hitsz/application/Main.java`

### 2. 命令行编译运行
```bash
# 进入项目根目录
cd AircraftWar

# 编译所有Java源文件（需要确保images和videos目录在classpath中）
find src -name "*.java" > sources.txt
javac -d out @sources.txt
rm sources.txt

# 运行游戏
java -cp "out:src/images:src/videos" edu.hitsz.application.Main
```
*注意：Windows用户请将classpath分隔符`:`替换为`;`*

### 3. 游戏控制
- **移动控制**：使用鼠标移动控制英雄飞机位置
- **自动射击**：英雄飞机会自动发射子弹
- **道具收集**：控制飞机接触掉落道具来获得能力增强

## 项目结构

```
AircraftWar/
├── src/                          # 源代码目录
│   └── edu/hitsz/
│       ├── application/          # 应用程序层
│       │   ├── Main.java        # 程序入口
│       │   ├── Game.java        # 游戏主逻辑
│       │   ├── HeroController.java # 英雄控制器（鼠标控制）
│       │   └── ImageManager.java # 图片管理器（单例）
│       ├── aircraft/            # 飞机相关类
│       │   ├── AbstractAircraft.java # 抽象飞机类
│       │   ├── AbstractEnemy.java    # 抽象敌机类
│       │   ├── HeroAircraft.java    # 英雄飞机（单例模式）
│       │   ├── MobEnemy.java        # 普通敌机
│       │   ├── EliteEnemy.java      # 精英敌机
│       │   ├── ElitePlusEnemy.java  # 精锐敌机
│       │   ├── EliteProEnemy.java   # 王牌敌机
│       │   └── BossEnemy.java       # Boss敌机
│       ├── basic/               # 基础类
│       │   └── AbstractFlyingObject.java # 抽象飞行物体
│       ├── bullet/              # 子弹相关类
│       │   ├── BaseBullet.java      # 基础子弹类
│       │   ├── HeroBullet.java      # 英雄子弹
│       │   └── EnemyBullet.java     # 敌机子弹
│       ├── factory/             # 工厂模式相关
│       │   ├── EnemyFactory.java    # 敌机工厂接口（工厂方法模式）
│       │   ├── MobEnemyFactory.java # 普通敌机工厂
│       │   ├── EliteEnemyFactory.java # 精英敌机工厂
│       │   ├── ElitePlusEnemyFactory.java # 精锐敌机工厂
│       │   ├── EliteProEnemyFactory.java  # 王牌敌机工厂
│       │   ├── BossEnemyFactory.java # Boss敌机工厂
│       │   └── PropFactory.java     # 道具简单工厂（简单工厂模式）
│       ├── prop/                # 道具相关类
│       │   ├── AbstractProp.java   # 抽象道具类
│       │   ├── BloodProp.java      # 血量道具
│       │   ├── BombProp.java       # 炸弹道具
│       │   ├── FireProp.java       # 火力道具
│       │   ├── FirePlusProp.java   # 超级火力道具
│       │   └── FreezeProp.java     # 冰冻道具
│       └── strategy/            # 策略模式相关
│           ├── ShootStrategy.java  # 射击策略接口
│           ├── DirectShootStrategy.java # 单排直射策略
│           ├── NoShootStrategy.java    # 不射击策略
│           ├── DoubleDirectShootStrategy.java # 双排直射策略
│           ├── SpreadShootStrategy.java # 散射策略
│           └── CircleShootStrategy.java # 环射策略
├── images/                      # 图片资源
│   ├── bg.jpg - bg5.jpg         # 背景图片（滚动效果）
│   ├── hero.png                 # 英雄飞机图片
│   ├── mob.png                  # 普通敌机图片
│   ├── elite.png                # 精英敌机图片
│   ├── elitePlus.png            # 精锐敌机图片
│   ├── elitePro.png             # 王牌敌机图片
│   ├── boss.png                 # Boss敌机图片
│   ├── bullet_hero.png          # 英雄子弹图片
│   ├── bullet_enemy.png         # 敌机子弹图片
│   └── prop_*.png               # 各种道具图片
├── videos/                      # 音效资源
│   ├── bgm.wav                  # 背景音乐
│   ├── bgm_boss.wav             # Boss战音乐
│   ├── bomb_explosion.wav       # 爆炸音效
│   ├── bullet_hit.wav           # 子弹命中音效
│   ├── game_over.wav            # 游戏结束音效
│   └── get_supply.wav           # 道具获取音效
├── uml/                         # UML设计图（课程实验要求）
│   ├── Inheritence.puml         # 继承关系图
│   ├── SimpleFactory.puml       # 简单工厂模式图
│   ├── FactoryMethod.puml       # 工厂方法模式图
│   ├── Strategy.puml            # 策略模式图
│   ├── Singleton.puml           # 单例模式图
│   └── *.png                    # 生成的UML图片
├── .gitignore                   # Git忽略文件配置
├── AircraftWar-base.iml         # IntelliJ IDEA模块文件
└── README.md                    # 项目说明文档
```

## 设计模式应用

本项目是面向对象设计模式的实践案例，主要应用了以下设计模式：

### 1. 单例模式 (Singleton)
- **应用位置**：`HeroAircraft` 类
- **实现方式**：私有构造函数 + 静态getInstance()方法
- **目的**：确保游戏中只有一个英雄飞机实例，全局可访问

### 2. 工厂方法模式 (Factory Method)
- **应用位置**：`EnemyFactory` 接口及其具体实现类
- **实现方式**：定义创建敌机的抽象接口，由具体工厂子类决定实例化哪种敌机
- **目的**：将敌机的创建与使用分离，支持扩展新的敌机类型而不修改客户端代码

### 3. 简单工厂模式 (Simple Factory)
- **应用位置**：`PropFactory` 类
- **实现方式**：静态方法根据传入类型创建对应的道具实例
- **目的**：集中管理道具对象的创建逻辑，简化客户端代码

### 4. 策略模式 (Strategy)
- **应用位置**：`ShootStrategy` 接口及其具体实现类
- **实现方式**：定义一系列射击算法，封装在独立的策略类中，使它们可以相互替换
- **目的**：实现英雄飞机和不同敌机类型的多样化射击行为，支持运行时动态切换射击策略

### 5. 模板方法模式 (Template Method)
- **应用位置**：`AbstractFlyingObject` 和 `AbstractAircraft` 抽象类
- **实现方式**：在抽象类中定义算法骨架，将某些步骤延迟到子类实现
- **目的**：提供飞行对象和飞机的通用行为框架，确保子类遵循统一的接口

## 游戏机制详解

### 敌机生成逻辑
1. **普通敌机生成**：每隔固定周期随机生成一种敌机（Boss除外）
2. **Boss敌机生成**：玩家分数达到阈值（默认500分）时触发生成
3. **敌机类型概率**：
   - 普通敌机：40%
   - 精英敌机：25%
   - 精锐敌机：20%
   - 王牌敌机：15%

### 道具掉落机制
1. **精英敌机**：30%概率掉落基础道具（血量、火力、超级火力）
2. **精锐敌机**：50%概率掉落4种道具（不含冰冻）
3. **王牌敌机**：70%概率掉落全部5种道具
4. **Boss敌机**：100%掉落3个道具
5. **普通敌机**：不掉落道具

### 射击策略系统
- **英雄飞机默认**：单排直射策略
- **拾取火力道具**：切换为散射策略
- **拾取超级火力道具**：切换为环射策略
- **不同敌机类型**：使用不同的射击策略（部分敌机使用不射击策略）

## 构建与开发

### 开发环境设置
1. 安装JDK 8或更高版本
2. 安装IntelliJ IDEA（社区版即可）
3. 克隆或下载本项目代码
4. 使用IntelliJ IDEA打开项目目录

### 代码规范
- 遵循Java命名规范（类名大写驼峰，方法名小写驼峰）
- 关键类和方法添加Javadoc注释
- 设计模式应用处添加模式说明注释

### 扩展指南
如需扩展本项目，建议遵循以下原则：
1. 添加新敌机类型：实现新的`EnemyFactory`子类
2. 添加新道具类型：扩展`PropFactory.PropType`枚举和对应道具类
3. 添加新射击策略：实现新的`ShootStrategy`子类
4. 保持设计模式的一致性，避免破坏现有架构

## 许可证

本项目仅供学习用途

## 贡献指南

欢迎提交Issue和Pull Request来改进项目。如需贡献代码，请确保：
1. 遵循现有代码风格和设计模式
2. 添加适当的测试（如有）
3. 更新相关文档

## 更新日志

### v1.0.0（当前版本）
- 基础游戏框架搭建
- 实现英雄飞机和5种敌机类型
- 添加5种道具系统和音效系统
- 完成游戏核心逻辑（生成、移动、碰撞、分数）
- 应用4种设计模式（单例、工厂方法、简单工厂、策略）

### 历史版本
- **Lab4提交**：添加单例模式，完善英雄飞机唯一实例管理
- **Lab3提交**：应用策略模式，实现多样化射击行为
- **Lab2提交**：应用简单工厂和工厂方法模式，改进敌机和道具创建
- **Lab1提交**：基础游戏框架，继承与多态应用

---

**开始游戏，享受射击的乐趣吧！** 🚀
