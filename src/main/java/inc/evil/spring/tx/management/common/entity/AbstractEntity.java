package inc.evil.spring.tx.management.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid-generator")
    protected String id;

    protected AbstractEntity() {
    }

    public String getId() {
        return id;
    }

    public boolean equals(Object other) {
        if (!(other instanceof AbstractEntity otherEntity))
            return false;
        return Objects.equals(id, otherEntity.getId());
    }

    public int hashCode() {
        return getClass().hashCode();
    }
}
