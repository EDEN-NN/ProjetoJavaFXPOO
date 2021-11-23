package entities;

import java.time.LocalDate;

public abstract class AbstractObjectModel<TId> {
    private TId id;
    private LocalDate inserted;
    private LocalDate updated;
    private LocalDate deleted;

    public TId getId() {
        return id;
    }

    public void setId(TId id) {
        this.id = id;
    }

    public LocalDate getInserted() {
        return inserted;
    }

    public void setInserted(LocalDate inserted) {
        this.inserted = inserted;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public LocalDate getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDate deleted) {
        this.deleted = deleted;
    }
}
