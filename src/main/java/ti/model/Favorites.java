package ti.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "favorites")
public class Favorites implements Serializable {
    private static final long serialVersionUID = 3L;
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comics_id", nullable = false)
    private Comic comic;

    public User getUser() {
        return user;
    }

    public Comic getComic() {
        return comic;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
}
