package br.com.fiap.epictask.user;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Entity
@Data
@Table(name = "epicUser")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String avatarUrl;

    private int score = 0;

    public User(OAuth2User principal){
        this.email = principal.getAttributes().get("email").toString();
        this.name = principal.getAttributes().get("name").toString();
        var avatarUrl = principal.getAttributes().get("email") !=null ?
                principal.getAttributes().get("picture") :
                principal.getAttributes().get("avatar_url");
        this.avatarUrl = avatarUrl.toString();
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
