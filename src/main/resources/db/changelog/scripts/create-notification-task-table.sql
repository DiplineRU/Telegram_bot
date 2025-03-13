-- liquibase formatted sql

-- changeset agulyan:1

CREATE TABLE notification_task
(
    id                     BIGSERIAL PRIMARY KEY,          -- Первичный ключ, автоинкремент
    chat_id                BIGINT                NOT NULL, -- Идентификатор чата
    message                VARCHAR(255)          NOT NULL, -- Текст уведомления
    notification_date_time TIMESTAMP             NOT NULL, -- Дата и время отправки уведомления
    is_sent                BOOLEAN DEFAULT FALSE NOT NULL  -- Флаг отправки уведомления
);