<p align="center">
  <img src="banner.svg" alt="Tangled Lantern" width="900"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-1.21.1-7c3aed?style=for-the-badge&logo=creativecommons&logoColor=white" alt="Minecraft 1.21.1"/>
  <img src="https://img.shields.io/badge/Fabric-Mod-9333ea?style=for-the-badge" alt="Fabric"/>
  <img src="https://img.shields.io/badge/Java-21+-6d28d9?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/License-MIT-fbbf24?style=for-the-badge" alt="MIT License"/>
</p>

<p align="center">
  <em>Release sky lanterns into the night and watch them drift among the stars.</em>
</p>

<br/>

---

## ✨ Overview

**Tangled Lantern** adds glowing sky lanterns to Minecraft Java Edition, inspired by the iconic lantern scene from *Tangled*. Craft one, hold it in your hand, right-click — and watch it float gently upward, drifting and slowly rotating as it climbs toward the stars.

> No equivalent mod existed for Java Edition. Now there is one.

---

## 🌟 Features

| | |
|---|---|
| 🏮 **Sky Lanterns** | Floating entities that slowly rise and drift with a gentle sine-wave sway |
| 🔥 **Particle Flames** | Small flame particles flicker from the base of each lantern |
| 💫 **Always Glowing** | Renders at full brightness — visible even in complete darkness |
| 🌀 **Soft Rotation** | Slowly spins on its Y-axis as it floats upward |
| 🎇 **No Collision** | Passes freely through blocks and entities |
| ⏳ **Auto-Despawn** | Fades away after 5 minutes (6 000 ticks) |
| 🎮 **Survival Ready** | Craftable recipe, stackable to 16, works in Creative mode too |

---

## 🔮 Crafting Recipe

<p align="center">

| &nbsp; | ① | ② | ③ |
|:------:|:---:|:---:|:---:|
| **A** | | 🧵 String | |
| **B** | 📄 Paper | 🔦 Torch | 📄 Paper |
| **C** | | 📄 Paper | |

</p>

<p align="center"><b>Yields: 4 × Sky Lanterns</b></p>

---

## 📦 Installation

1. Install [**Fabric Loader**](https://fabricmc.net/use/) for Minecraft **1.21.1**
2. Download [**Fabric API**](https://modrinth.com/mod/fabric-api) and place it in your `mods/` folder
3. Download `tangled-lantern-1.0.0.jar` from the [**Releases**](../../releases) tab
4. Drop the `.jar` into `.minecraft/mods/`
5. Launch — the **Sky Lantern** appears in the **Tools** creative tab

---

## 🛠️ Building from Source

**Requirements:** Java 21, Git

```bash
git clone https://github.com/JackStar6677-1/tangled-lantern
cd tangled-lantern
gradle wrapper
./gradlew build
```

Output: `build/libs/tangled-lantern-1.0.0.jar`

To regenerate textures (requires Python 3 + Pillow):

```bash
pip install Pillow
python3 generate_textures.py
```

---

## 🎮 Usage

| Action | Result |
|--------|--------|
| **Right-click** holding a Sky Lantern | Spawns a lantern just in front of you |
| **Creative mode** | Item is not consumed |
| **Survival mode** | Consumes 1 lantern per release |
| **Wait** | Lantern floats up and disappears after 5 min |

---

## 📁 Project Structure

```
src/main/
├── java/com/tangledlantern/
│   ├── TangledLantern.java           ← mod entrypoint
│   ├── entity/
│   │   └── FloatingLanternEntity.java
│   ├── item/
│   │   └── SkyLanternItem.java
│   ├── registry/
│   │   ├── ModEntities.java
│   │   └── ModItems.java
│   └── client/
│       ├── TangledLanternClient.java ← client entrypoint
│       ├── ModModelLayers.java
│       └── entity/
│           ├── FloatingLanternEntityModel.java
│           └── FloatingLanternEntityRenderer.java
└── resources/
    ├── assets/tangledlantern/
    │   ├── lang/          en_us.json, es_es.json
    │   ├── textures/      entity + item PNGs
    │   └── models/item/   sky_lantern.json
    └── data/tangledlantern/
        └── recipe/        sky_lantern.json
```

---

## 📜 License

**MIT** — free to use, modify, fork, and redistribute.
