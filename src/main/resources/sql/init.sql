CREATE TABLE IF NOT EXISTS reserves (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- Уникальный идентификатор брони
    start_time DATETIME NOT NULL,                  -- Время начала брони
    end_time DATETIME NOT NULL,                    -- Время окончания брони
    parking_spots_id BIGINT NOT NULL,              -- ID парковочного места
    spot_number VARCHAR(255),
    price INT NOT NULL,                            -- Цена брони
    reserve_type VARCHAR(255),
    FOREIGN KEY (parking_spots_id) REFERENCES parking_spots(parking_id) ON DELETE CASCADE
);
