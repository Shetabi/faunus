ALTER TABLE plant ADD watering_method_id BIGINT NULL;

ALTER TABLE plant ADD CONSTRAINT fk_plant_wateringmethods
    FOREIGN KEY (watering_method_id) REFERENCES WATERING_METHOD(id);
