# AircraftWar - 飞机大战游戏

## 项目简介

AircraftWar 是一个基于Java Swing开发的2D飞机射击游戏，玩家控制一架英雄飞机与各种类型的敌机进行战斗。游戏具有丰富的敌机类型、道具系统和战斗机制。

## 功能特性

### 🎮 游戏玩法
- **英雄飞机控制**：使用鼠标控制英雄飞机移动和射击
- **多种敌机类型**：普通敌机、精英敌机、精锐敌机、王牌敌机、Boss敌机
- **道具系统**：血量道具、炸弹道具、火力增强道具、冰冻道具
- **分数系统**：击落敌机获得分数

### ✈️ 飞机类型
- **英雄飞机 (HeroAircraft)**：玩家控制的飞机，可发射子弹
- **普通敌机 (MobEnemy)**：基础敌机，移动速度较慢
- **精英敌机 (EliteEnemy)**：中等难度，有概率掉落道具
- **精锐敌机 (ElitePlusEnemy)**：高级敌机，更强的战斗力
- **王牌敌机 (EliteProEnemy)**：精英中的精英
- **Boss敌机 (BossEnemy)**：最终Boss，拥有强大的生命值和攻击力

### 🎯 道具系统
- **血量道具 (BloodProp)**：恢复英雄飞机生命值
- **炸弹道具 (BombProp)**：清除屏幕上的所有敌机
- **火力增强道具 (FireProp/FirePlusProp)**：增强英雄飞机的火力
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

## 快速开始

### 1. 编译项目
```bash
# 进入项目根目录
cd AircraftWar-base1.0

# 编译Java源代码
javac -d out/production/AircraftWar-base src/edu/hitsz/**/*.java
```

### 2. 运行游戏
```bash
# 运行游戏
java -cp out/production/AircraftWar-base edu.hitsz.application.Main
```

### 3. 使用IDE运行
- 使用IntelliJ IDEA、Eclipse等Java IDE导入项目
- 直接运行 `Main.java` 文件

## 游戏控制

- **移动控制**：使用鼠标移动控制英雄飞机位置
- **自动射击**：英雄飞机会自动发射子弹
- **道具收集**：控制飞机接触掉落道具来获得能力增强

## 项目结构

```
AircraftWar-base1.0/
├── src/                          # 源代码目录
│   └── edu/hitsz/
│       ├── application/          # 应用程序层
│       │   ├── Main.java        # 程序入口
│       │   ├── Game.java        # 游戏主逻辑
│       │   ├── HeroController.java # 英雄控制器
│       │   └── ImageManager.java # 图片管理器
│       ├── aircraft/            # 飞机相关类
│       │   ├── AbstractAircraft.java # 抽象飞机类
│       │   ├── HeroAircraft.java    # 英雄飞机
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
│       └── prop/                # 道具相关类
│           ├── AbstractProp.java   # 抽象道具类
│           ├── BloodProp.java      # 血量道具
│           ├── BombProp.java        # 炸弹道具
│           ├── FireProp.java       # 火力道具
│           ├── FirePlusProp.java    # 增强火力道具
│           └── FreezeProp.java      # 冰冻道具
├── images/                      # 图片资源
│   ├── bg.jpg - bg5.jpg         # 背景图片
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
├── uml/                         # UML设计图
│   ├── Demo.puml                # 示例UML
│   ├── Inheritence.puml         # 继承关系图
│   └── test.puml                # 测试UML
└── README.md                    # 项目说明文档
```

## 设计模式

项目采用了面向对象的设计原则，主要使用了以下设计模式：

- **单例模式 (Singleton)**：HeroAircraft 使用单例模式确保只有一个英雄飞机实例
- **工厂模式 (Factory)**：通过抽象类和接口实现飞机、子弹、道具的创建
- **观察者模式 (Observer)**：游戏事件监听和处理
- **策略模式 (Strategy)**：不同的敌机和道具具有不同的行为策略

## 开发团队

- **哈尔滨工业大学 (HIT)** 软件学院项目
- 基于面向对象程序设计课程要求开发

## 许可证

本项目仅供学习和教育用途。

## 更新日志

### v1.0.0
- 基础游戏框架搭建
- 实现英雄飞机和多种敌机类型
- 添加道具系统和音效系统
- 完成游戏核心逻辑

## 贡献指南

欢迎提交Issue和Pull Request来改进项目。

---

**开始游戏，享受射击的乐趣吧！** 🚀