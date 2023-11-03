package nl.enjarai.clientpaintings.util;

import java.util.Objects;

public class Vec2i {
    public int x;
    public int y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i() {
        this(0, 0);
    }

    public Vec2i(Vec2i other) {
        this(other.x, other.y);
    }

    public Vec2i add(Vec2i other) {
        return new Vec2i(this.x + other.x, this.y + other.y);
    }

    public Vec2i subtract(Vec2i other) {
        return new Vec2i(this.x - other.x, this.y - other.y);
    }

    public Vec2i multiply(int scalar) {
        return new Vec2i(this.x * scalar, this.y * scalar);
    }

    public Vec2i divide(int scalar) {
        return new Vec2i(this.x / scalar, this.y / scalar);
    }

    public Vec2i negate() {
        return new Vec2i(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var vec2i = (Vec2i) o;
        return this.x == vec2i.x && this.y == vec2i.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}