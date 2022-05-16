package inc.evil.spring.tx.management.domain;

import inc.evil.spring.tx.management.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie extends AbstractEntity {
    private String name;

    protected Movie() {
    }

    private Movie(MovieBuilder builder) {
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static MovieBuilder builder() {
        return new MovieBuilder();
    }

    public static class MovieBuilder {
        private String name;

        public MovieBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
