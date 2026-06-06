#!/usr/bin/env python3
"""
Generates texture PNGs for the Tangled Lantern mod.
Run: python3 generate_textures.py
Requires: pip install Pillow
"""

import os
from PIL import Image

ENTITY_DIR = "src/main/resources/assets/tangledlantern/textures/entity"
ITEM_DIR   = "src/main/resources/assets/tangledlantern/textures/item"

# ── Colours ──────────────────────────────────────────────────────────────────
AMBER      = (255, 185,  55, 220)  # warm translucent body
AMBER_EDGE = (190, 110,  15, 255)  # dark border
GLOW       = (255, 240, 110, 240)  # front-face inner glow
CREAM      = (255, 230, 160, 200)  # subtle inside highlight


def put(img: Image.Image, x1, y1, x2, y2, fill, border=None):
    """Fill rect; optional 1-px border."""
    for x in range(x1, x2):
        for y in range(y1, y2):
            on_edge = (border is not None and
                       (x == x1 or x == x2 - 1 or y == y1 or y == y2 - 1))
            img.putpixel((x, y), border if on_edge else fill)


def entity_texture() -> Image.Image:
    """
    64x64 texture for the FloatingLanternEntity model.
    Cuboid (-5,-8,-5 | 10×16×10) at UV(0,0):
      TOP   (10, 0)→(20,10)   BOT  (20, 0)→(30,10)
      WEST  ( 0,10)→(10,26)   NORTH(10,10)→(20,26)
      EAST  (20,10)→(30,26)   SOUTH(30,10)→(40,26)
    """
    img = Image.new("RGBA", (64, 64), (0, 0, 0, 0))

    # Top face (seen from above — darker cap)
    put(img, 10, 0, 20, 10, AMBER_EDGE)

    # Bottom face
    put(img, 20, 0, 30, 10, AMBER, AMBER_EDGE)

    # West / East side faces
    put(img,  0, 10, 10, 26, AMBER, AMBER_EDGE)
    put(img, 20, 10, 30, 26, AMBER, AMBER_EDGE)

    # North (front) face — glowing
    put(img, 10, 10, 20, 26, GLOW, AMBER_EDGE)
    # centre highlight
    for x in range(13, 17):
        for y in range(14, 22):
            img.putpixel((x, y), CREAM)

    # South (back) face
    put(img, 30, 10, 40, 26, AMBER, AMBER_EDGE)

    return img


def item_texture() -> Image.Image:
    """Simple 16×16 sky-lantern icon."""
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))

    BORDER = AMBER_EDGE
    FILL   = AMBER
    BRIGHT = GLOW

    # Hanging string
    img.putpixel((8, 1), BORDER)
    img.putpixel((8, 2), BORDER)

    # Top cap
    for x in range(6, 10):
        img.putpixel((x, 3), BORDER)
    for x in range(7, 9):
        img.putpixel((x, 2), BORDER)

    # Main body 6×8 centred
    for x in range(5, 11):
        for y in range(4, 12):
            on_edge = (x == 5 or x == 10 or y == 4 or y == 11)
            img.putpixel((x, y), BORDER if on_edge else FILL)

    # Inner glow cluster
    for x, y in [(8, 7), (7, 8), (8, 8), (9, 8), (8, 9)]:
        img.putpixel((x, y), BRIGHT)

    # Bottom cap
    for x in range(6, 10):
        img.putpixel((x, 12), BORDER)

    return img


if __name__ == "__main__":
    os.makedirs(ENTITY_DIR, exist_ok=True)
    os.makedirs(ITEM_DIR,   exist_ok=True)

    entity_texture().save(os.path.join(ENTITY_DIR, "floating_lantern.png"))
    print("✓ textures/entity/floating_lantern.png")

    item_texture().save(os.path.join(ITEM_DIR, "sky_lantern.png"))
    print("✓ textures/item/sky_lantern.png")

    print("Done.")
