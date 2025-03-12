package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private char gender;  // 'M' = 男, 'F' = 女, 'O' = 其他
    private String bio;   // 會員個人簡介

    // 無參數建構子（預設值）
    public User() {}

    // 有參數建構子
    public User(int id, String name, String email, String password, char gender, String bio) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.bio = bio;
    }

    // Getter & Setter 方法
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public char getGender() { return gender; }
    public void setGender(char gender) {
        if (gender == 'M' || gender == 'F' || gender == 'O') {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("性別只能是 'M', 'F', 'O'");
        }
    }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
