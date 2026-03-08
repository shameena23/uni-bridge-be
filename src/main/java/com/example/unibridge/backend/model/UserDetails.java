package com.example.unibridge.backend.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;  // reference to User table

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "department", length = 100)
    private String department;

    @Column(name = "year", length = 50)
    private String year;

    @Column(name = "profile_image", length = 500)
    private String profileImage;

    // =====================
    // Constructors
    // =====================
    public UserDetails() {}

    public UserDetails(Long userId, String name, String department, String year, String profileImage) {
        this.userId = userId;
        this.name = name;
        this.department = department;
        this.year = year;
        this.profileImage = profileImage;
    }

    // =====================
    // Getters & Setters
    // =====================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getProfileImage() { return profileImage; }
    public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
}