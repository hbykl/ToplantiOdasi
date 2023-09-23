package com.example.toplantiOdasi.Classes;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Room")
public class Room {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "room_name")
    @NotNull(message = "*İsim alanı boş bırakılamaz")
    private String roomName;
    @Column(name = "situation")
    @NotNull(message = "*Odanın durumunu belirtiniz")
    private boolean situation;

    @Column(name = "user_id")
    private long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    public Room(){}

    public Room(Long id, String roomName, boolean situation){
        this.id=id;
        this.roomName=roomName;
        this.situation=situation;
    }

    public Room(String roomName, boolean situation){
        this.roomName=roomName;
        this.situation=situation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isSituation() {
        return situation;
    }

    public void setSituation(boolean situation) {
        this.situation = situation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
