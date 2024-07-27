CREATE TABLE WATERING_LOG (
    id SERIAL PRIMARY KEY,
    owner_plant_id SERIAL NOT NULL,
    type VARCHAR(128) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    payload TEXT
);

ALTER TABLE WATERING_LOG ADD CONSTRAINT fk_watering_log_owner_plant
    FOREIGN KEY (owner_plant_id) REFERENCES OWNER_PLANT(id);
