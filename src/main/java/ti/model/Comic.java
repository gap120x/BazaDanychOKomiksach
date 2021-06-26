package ti.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "comic")

public class Comic implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    @Type(type="text")
    private String category;

    @Column(name = "language")
    private String language;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "cover")
    @Type(type="text")
    private String cover;

    @Column(name = "issue_date")
    private Integer issueDate;

    @Column(name = "publisher")
    @Type(type="text")
    private String publisher;

    @Column(name = "description")
    @Type(type="text")
    private String description;


    @Column(name = "image")
    @Type(type="text")
    private String image;

    @Column(name = "title")
    @Type(type="text")
    private String title;

    @OneToMany(mappedBy = "comic", fetch = FetchType.LAZY,
           cascade = CascadeType.ALL)
    private Set<Favorites> favorites = new HashSet<Favorites>();


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Integer issueDate) {
        this.issueDate = issueDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorites> favorites) {
        this.favorites = favorites;
    }
}
