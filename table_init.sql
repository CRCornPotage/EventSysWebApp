-- 既存のデータベースを削除
DROP DATABASE IF EXISTS eventsys;

-- EventSysデータベースの作成
CREATE DATABASE eventsys;

-- 新しいデータベースに移動
USE eventsys;



-- Userテーブルの作成
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL
);

-- Eventテーブルの作成
CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(255),
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    organizer_id BIGINT,
    FOREIGN KEY (organizer_id) REFERENCES users(id)
);

-- Participantテーブルの作成
CREATE TABLE participants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT,
    user_id BIGINT,
    registration_time TIMESTAMP NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Notificationテーブルの作成
CREATE TABLE notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipient_id BIGINT,
    message TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    status VARCHAR(20),
    FOREIGN KEY (recipient_id) REFERENCES users(id)
);

-- 初期ユーザーの挿入
-- パスワードは 'adminpassword', 'user1password', 'user2password' をBCryptPasswordEncoderでエンコードしたものです
INSERT INTO users (username, password, email, role) VALUES ('admin', '$2a$10$2iDTs4wNWGkkY.QzqbU1ru88Lbt.50O2jivlsG/RvuGUylZdYuv9a', 'admin@example.com', 'ADMIN');
INSERT INTO users (username, password, email, role) VALUES ('user1', '$2a$10$Yb2/a4WDgOwRvsqYQDh0m.8Ef9ur0Qqcsxmln1qgy4qFzC09RCj4u', 'user1@example.com', 'USER');
INSERT INTO users (username, password, email, role) VALUES ('user2', '$2a$10$TnGMqL6qiwkgDsC/hBOkf..zhtcexy/fDDVKNIWguShjqwOT4VTpq', 'user2@example.com', 'USER');

-- 初期イベントの挿入
INSERT INTO events (name, description, location, start_time, end_time, organizer_id) VALUES ('Spring Boot Workshop', 'A workshop on Spring Boot basics', 'Conference Room A', '2024-07-01 10:00:00', '2024-07-01 12:00:00', 1);
INSERT INTO events (name, description, location, start_time, end_time, organizer_id) VALUES ('Java Conference', 'Annual conference for Java enthusiasts', 'Main Hall', '2024-08-15 09:00:00', '2024-08-15 17:00:00', 2);

-- 初期参加者の挿入
INSERT INTO participants (event_id, user_id, registration_time) VALUES (1, 2, '2024-06-01 08:00:00');
INSERT INTO participants (event_id, user_id, registration_time) VALUES (2, 3, '2024-06-15 10:30:00');

-- 初期通知の挿入
INSERT INTO notifications (recipient_id, message, timestamp, status) VALUES (2, 'You have been registered for the Spring Boot Workshop', '2024-06-01 08:05:00', 'SENT');
INSERT INTO notifications (recipient_id, message, timestamp, status) VALUES (3, 'You have been registered for the Java Conference', '2024-06-15 10:35:00', 'SENT');
