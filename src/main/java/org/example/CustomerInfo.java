package org.example;

class CustomerInfo {
    int id;
    String username;
    String userLevel;
    String registrationDate;
    double totalSpent;
    String phone;
    String email;

    public CustomerInfo(int id, String username, String userLevel, String registrationDate, double totalSpent, String phone, String email) {
        this.id = id;
        this.username = username;
        this.userLevel = userLevel;
        this.registrationDate = registrationDate;
        this.totalSpent = totalSpent;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", totalSpent=" + totalSpent +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
