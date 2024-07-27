CREATE TABLE WATERING_SCHEDULE (
    id SERIAL PRIMARY KEY,
    owner_plant_id BIGINT NOT NULL,
    frequency_days INT NOT NULL
);

ALTER TABLE WATERING_SCHEDULE ADD CONSTRAINT fk_watering_schedule_owner_plant
    FOREIGN KEY (owner_plant_id) REFERENCES OWNER_PLANT(id);
