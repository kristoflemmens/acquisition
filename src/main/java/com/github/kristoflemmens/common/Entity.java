package com.github.kristoflemmens.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class Entity<ID> {

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Entity that = (Entity) obj;

        return new EqualsBuilder()
                .append(this.id(), that.id())
                .isEquals();
    }

    public abstract ID id();

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(id())
                .toString();
    }
}
